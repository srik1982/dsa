package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Given is a set of events with
 * 1. Profits of executing each event.
 * 2. Time at which it must start. If the event doesn't start at this time, profit is 0.
 * 
 * There might be more than 1 event for a time. We need to find an efficient way to run the events.
 * @author srika
 *
 */
public class OrganizeEvents {

	public static void main(String[] args) {
		OrganizeEvents obj = new OrganizeEvents();
		List<Event> allEvents = obj.getEvents();
		List<Event> bestEvents = obj.organize(allEvents);
		bestEvents.forEach(e -> System.out.println(e));
	}
	
	List<Event> getEvents(){
		List<Event> allEvents = new ArrayList<Event>();
		
		allEvents.add(new Event(10,9));
		allEvents.add(new Event(5,9));
		allEvents.add(new Event(15,10));
		allEvents.add(new Event(2,10));
		allEvents.add(new Event(12,11));
		allEvents.add(new Event(13,12));
		allEvents.add(new Event(20,12));
		allEvents.add(new Event(7,13));
		allEvents.add(new Event(6,13));
		allEvents.add(new Event(10,14));
		allEvents.add(new Event(11,14));
		allEvents.add(new Event(18,15));
		allEvents.add(new Event(21,15));
		allEvents.add(new Event(10,16));
		allEvents.add(new Event(9,16));
		allEvents.add(new Event(19,17));
		allEvents.add(new Event(13,18));
		allEvents.add(new Event(12,18));
		allEvents.add(new Event(14,18));
		
		return allEvents;
	}
	
	class Event{
		int profit;
		int start;
		
		Event(int profit, int start){
			this.profit = profit;
			this.start = start;
		}
		
		public String toString() {
			return "[ "+profit+" , "+start+" ]";
		}
	}
	
	class TimeComparator implements Comparator<Event>{

		@Override
		public int compare(Event arg0, Event arg1) {
			// TODO Auto-generated method stub
			return arg0.start < arg1.start ? -1 : (arg0.start > arg1.start ? 1 : 0);
		}
		
	}
	
	class ProfitComparator implements Comparator<Event>{

		@Override
		public int compare(Event o1, Event o2) {
			// TODO Auto-generated method stub
			return o1.profit > o2.profit ? -1 : (o1.profit < o2.profit ? 1 : 0);
		}
		
	}
	
	public List<Event> organize(List<Event> allEvents){
		Collections.sort(allEvents, new TimeComparator());
		List<Event> bestEvents = new ArrayList<OrganizeEvents.Event>();
		PriorityQueue<Event> pq = new PriorityQueue<>(new ProfitComparator());
		int prevTime = -1;
		boolean firstEvent = true;
		for(Event e : allEvents) {
			//All events with same timestamp has to be added 
			if(!firstEvent && prevTime != e.start) {
				bestEvents.add(pq.poll());
				pq.clear();
			}
			firstEvent = false;
			prevTime = e.start;
			pq.offer(e);
		}
		bestEvents.add(pq.poll());
		return bestEvents;
	}

}
