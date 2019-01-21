package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * We are given a Set of intervals. Some of the intervals are overlapping.
 * We need to find a subset of non-overlapping intervals such that maximum interval is covered.
 * 
 * This is one of the approaches. It gives one of the solutions, not the optimal.
 * The reason is, when there is more than 1 set of intervals, this doesn't always return the optimal ( less number of intervals covering max time intervals)
 * @author srikanthrao
 *
 */
public class SelectingIntervalsSubset {

	public static void main(String[] args) {
		
		SelectingIntervalsSubset obj = new SelectingIntervalsSubset();
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
		
		@Override
		public int compareTo(Interval o) {
			// TODO Auto-generated method stub
			return endTime.compareTo(o.endTime);
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
