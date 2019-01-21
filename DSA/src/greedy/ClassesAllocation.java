package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Very similar to Selecting intervals. But here, we cannot just ignore some interval.
 * All intervals(classes) must be assigned to rooms.
 * Goal is to minimize the number of rooms.
 * 
 * Approach:
 * 1. Sort classes by Start time
 * 2. If class timings conflicts with existing room ( if any) , create a new room
 * 3. Otherwise assign to an existing room.
 * @author srikanthrao
 *
 */
public class ClassesAllocation {

	public static void main(String[] args) {
		ClassesAllocation obj = new ClassesAllocation();
		Class i1 = obj.new Class(10,11);
		Class i2 = obj.new Class(11,12);
		Class i3 = obj.new Class(10,12);
		Class i4 = obj.new Class(12,13);
		Class i5 = obj.new Class(11,13);
		Class i6 = obj.new Class(13,14);
		Class i7 = obj.new Class(14,15);
		Class i8 = obj.new Class(13,15);
		Class i9 = obj.new Class(15,16);
		Class i10 = obj.new Class(16,17);
		
		List<Class> intervals = new ArrayList<>();
		intervals.add(i1);
		intervals.add(i10);
		intervals.add(i6);
		intervals.add(i2);
		intervals.add(i8);
		intervals.add(i4);
		intervals.add(i9);
		intervals.add(i5);
		intervals.add(i3);
		intervals.add(i7);
		
		List<Room> rooms = obj.allocateClasses(intervals);
		rooms.forEach(i -> System.out.println(i));
	}
	
	class Class implements Comparable<Class>{
		Integer startTime;
		Integer endTime;
		
		Class(int start, int end){
			startTime = start;
			endTime = end;
		}

		@Override
		public int compareTo(Class o) {
			int val = startTime.compareTo(o.startTime);
			return val;
		}
		
		public int hashCode() {
			return (int) (startTime*31+endTime*Math.pow(31,2));
		}
		
		public boolean equals(Object o) {
			Class i = (Class) o;
			return startTime == i.startTime && endTime == i.endTime;
		}
		
		public String toString() {
			return "[ "+startTime+" , "+endTime+" ]";
		}
	}
	
	class Room{
		List<Class> classes = new ArrayList<>();
		String id = null;
		
		void addClass(Class c) {
			classes.add(c);
		}
		
		boolean isConflicting(Class c) {
			
			if(classes.isEmpty()) return false;
			
			Class lastClass = classes.get(classes.size()-1);
			return c.startTime < lastClass.endTime;
		}
		
		public String toString() {
			return id + " : "+classes.toString();
		}
	}
	
	List<Room> allocateClasses(List<Class> classes){
		Collections.sort(classes);
		List<Room> rooms = new ArrayList<Room>();
		
		for(Class c : classes) {
			Room r = null;
			for(Room _r : rooms) {
				if(!_r.isConflicting(c)) {
					r = _r;
					break;
				}
			}
			
			if(r == null) {
				r = new Room();
				rooms.add(r);
			}
			
			r.addClass(c);
		}
		
		return rooms;
	}
	
}
