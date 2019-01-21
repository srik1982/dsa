package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import greedy.SelectingIntervals1.Interval;

public class SelectingIntervals2 {

	public static void main(String[] args) {
		SelectingIntervals2 obj = new SelectingIntervals2();
		Interval i1 = obj.new Interval(2,5);
		Interval i2 = obj.new Interval(3,6);
		Interval i3 = obj.new Interval(6,12);
		Interval i4 = obj.new Interval(6,9);
		Interval i5 = obj.new Interval(7,12);
		Interval i6 = obj.new Interval(10,15);
		Interval i7 = obj.new Interval(13,21);
		Interval i8 = obj.new Interval(16,21);
		Interval i9 = obj.new Interval(22,25);
		Interval i10 = obj.new Interval(13,25);
		
		List<Interval> intervals = new ArrayList<>();
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
		
		List<Interval> subSet = obj.selectMaxinterval(intervals);
		subSet.forEach(i -> System.out.println(i));
	}
	class Interval implements Comparable<Interval>{
		Integer startTime;
		Integer endTime;
		
		Interval(int start, int end){
			startTime = start;
			endTime = end;
		}
		
		/**
		 * Compare To is different now.
		 * Instead of sorting by endTimes, now we try to sort by startTimes.
		 * Additionally, if startTimes are same, then the interval with higher endTime goes earlier.
		 * This way we get to choose bigger interval instead of many smaller intervals
		 * Is this optimal? Well, for this input set yes. I don't know, may be we can find a counter example.
		 */
		@Override
		public int compareTo(Interval o) {
			int val = startTime.compareTo(o.startTime);
			if(val == 0) {
				return o.endTime.compareTo(endTime);
			}
			return val;
		}
		
		public int hashCode() {
			return (int) (startTime*31+endTime*Math.pow(31,2));
		}
		
		public boolean equals(Object o) {
			Interval i = (Interval) o;
			return startTime == i.startTime && endTime == i.endTime;
		}
		
		public String toString() {
			return "[ "+startTime+" , "+endTime+" ]";
		}
	}
	
	public List<Interval> selectMaxinterval(List<Interval> superSet){
		Collections.sort(superSet);
		List<Interval> subSet = new ArrayList<>();
		
		Interval prevInterval = null;
		for(Interval i : superSet) {
			if(prevInterval == null || i.startTime >= prevInterval.endTime) {
				prevInterval = i;
				subSet.add(i);
			}
		}
		
		return subSet;
	}
}
