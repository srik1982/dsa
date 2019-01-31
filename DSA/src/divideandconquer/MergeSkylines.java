package divideandconquer;

import java.util.ArrayList;
import java.util.List;

/**
 * nlogn algorithm using merge sort. It Works! Finally
 * @author srika
 *
 */
public class MergeSkylines {

	public static void main(String[] args) {
		MergeSkylines obj = new MergeSkylines();
		List<Building> buildings = new ArrayList<MergeSkylines.Building>();
		
		Building b = obj.new Building(1,7,14);
		buildings.add(b);

		b = obj.new Building(3,10,10);
		buildings.add(b);
		
		b = obj.new Building(5,12,17);
		buildings.add(b);
		
		b = obj.new Building(14,18,11);
		buildings.add(b);
		
		b = obj.new Building(15,27,6);
		buildings.add(b);
		
		b = obj.new Building(1,7,14);
		buildings.add(b);
		
		b = obj.new Building(20,22,19);
		buildings.add(b);
		
		b = obj.new Building(23,30,15);
		buildings.add(b);
		
		b = obj.new Building(26,29,14);
		buildings.add(b);
		
		List<Silouette> silouettes = obj.findSkylines(buildings);
		
		silouettes.forEach(s -> System.out.println(s));
	}
	
	class Building{
		int start;
		int end;
		int height;
		
		Building(int start, int end, int height){
			this.start = start;
			this.end = end;
			this.height = height;
		}
		
		public String toString() {
			return "[ "+start+" , "+end+"  > "+height+" ]";
		}
	}
	
	class Silouette{
		int x;
		int height;
		Silouette(int x, int height){
			this.x = x;
			this.height = height;
		}
		
		public String toString() {
			return x+"=>"+height;
		}
		
		public int hashCode() {
			return x;
		}
		public boolean equals(Object o) {
			Silouette s = (Silouette) o;
			return s.x == x && s.height == height;
		}
	}
	
	public List<Silouette> findSkylines(List<Building> buildings) {		
		return findSkylines(buildings,0,buildings.size()-1);
	}

	private List<Silouette> findSkylines(List<Building> buildings, int low, int high) {
		if(low == high) { // means only 1 building. So the height is same as the building height
			Building b = buildings.get(low);
			List<Silouette> l = new ArrayList<MergeSkylines.Silouette>(b.end - b.start + 1);
			for(int i=b.start; i<=b.end;i++) {
				Silouette s = new Silouette(i,b.height);
				l.add(s);
			}
			return l;
		}else {
			int mid = (low+high)/2;
			
			List<Silouette> left = findSkylines(buildings,low,mid);
			List<Silouette> right = findSkylines(buildings,mid+1,high);
			return mergeSkylines(left,right,low,mid,high);
		}
	}

	private List<Silouette> mergeSkylines(List<Silouette> left, List<Silouette> right, int low, int mid, int high) {
		List<Silouette> mergedList = new ArrayList<Silouette>();
		int i=0,j=0;
		for(;i<left.size() && j<right.size();) {
			Silouette _left = left.get(i);
			Silouette _right = right.get(j);
			
			if(_left.x < _right.x) {
				mergedList.add(_left);
				i++;
			}else if(_left.x > _right.x) {
				mergedList.add(_right);
				j++;
			}else {
				if(_left.height >= _right.height) {
					mergedList.add(_left);
				}else {
					mergedList.add(_right);
				}
				i++;
				j++;
			}
		}
		
		while(i<left.size()) {
			mergedList.add(left.get(i++));
		}
		
		while(j<right.size()) {
			mergedList.add(right.get(j++));
		}
		
		return mergedList;
	}

}
