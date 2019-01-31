package divideandconquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This works. This uses 2 sorts which is expensive and then takes O(nlogn) and goes through each building in O(n).
 * So effectively this is a O(nlogn) algorithm.
 * 
 * But we are relying on java's sorting capabilities here.
 * 
 * I am pretty sure most interviews expect our own sorting code. And hence lets write version 2 which uses merge sort logic.
 * @author srika
 *
 */
public class SkylineProblem {

	public static void main(String[] args) {
		SkylineProblem obj = new SkylineProblem();
		ArrayList<Building> list = new ArrayList<SkylineProblem.Building>();
		
		Building b = obj.new Building(1,2,20);
		list.add(b);
		
		 b = obj.new Building(1,5,25);
		list.add(b);
		
		 b = obj.new Building(3,7,40);
		list.add(b);
		
	     b = obj.new Building(4,5,50);
		list.add(b);
			
		 b = obj.new Building(2,8,90);
		list.add(b);


		int[] skyline = obj.mergeSkylineSimple(list);
		
		for(int i=0;i<skyline.length;i++) {
			System.out.print("|");
			for(int j=0;j<skyline[i];j++) {
				System.out.print(" ");
			}
			System.out.println("] "+skyline[i]);
		}
	}

	class Building{
		int xStart;
		int height;
		int xEnd;
		
		Building(int xStart, int xEnd, int height){
			this.xStart = xStart;
			this.xEnd = xEnd;
			this.height = height;
		}
		public String toString() {
			return "[ ("+xStart+" , "+xEnd+") ==>"+height+" ]";
		}
	}
	
	class SortByStart implements Comparator<Building>{

		@Override
		public int compare(Building o1, Building o2) {
			return o1.xStart < o2.xStart ? -1 : (o1.xStart > o2.xStart ? 1: 0);
		}
	}
	
	class SortByEnd implements Comparator<Building>{

		@Override
		public int compare(Building o1, Building o2) {
			return o1.xEnd < o2.xEnd ? -1 : (o1.xEnd > o2.xEnd ? 1: 0);
		}
	}
	
	
	/**
	 * Assumption is more than 2 buildings DO NOT overlap
	 * If we have more than 2 overlapping buildings, this algorithm will fail.
	 * 
	 * First lets see if this works, and then create an improved version.
	 * @param buildings
	 * @return
	 */
	public int[] mergeSkylineSimple(List<Building> buildings){
		Collections.sort(buildings,new SortByEnd());
		int endIndex = buildings.get(buildings.size()-1).xEnd;
		
		Collections.sort(buildings, new SortByStart());
		
		int[] skylineArray = new int[endIndex+1];
		Building prev = null;
		for(Building b : buildings) {
			boolean overlap = false;
			
			if(prev!=null) {
				if(prev.xEnd > b.xStart) {
					overlap = true;
				}
			}
			
			boolean updatePrev = true;
			if(!overlap) {
				for(int i=b.xStart;i<=b.xEnd;i++) {
					skylineArray[i] = b.height;
				}
			}else {
				if(prev.xEnd < b.xEnd) {
					for(int i=b.xStart;i<=prev.xEnd;i++) {
						skylineArray[i] = Math.max(prev.height, b.height);
					}
					for(int i=prev.xEnd+1;i<=b.xEnd;i++) {
						skylineArray[i] = b.height;
					}
				}else {
					updatePrev = false;
					for(int i=b.xStart;i<=b.xEnd;i++) {
						skylineArray[i] = Math.max(prev.height, b.height);
					}
				}
			}
			if(updatePrev) {
				prev = b;
			}
		}
		return skylineArray;
	}
}
