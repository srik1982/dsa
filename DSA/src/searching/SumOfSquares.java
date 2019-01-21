package searching;

import java.util.HashMap;
import java.util.HashSet;

import sorting.MergeSort;

public class SumOfSquares {

	public static void main(String[] args) {
		int a[] = {11,10,9,8,7,6,5,4,3,2,1};
		MergeSort mergeSort = new MergeSort();
		//O(nlogn)
		mergeSort.mergeSort(a, 0, a.length-1);
		HashMap<Integer,Integer> map = new HashMap<>();
		//O(n2)
		for(int x : a) {
			int xsquare = (int)Math.pow(x, 2);
			if(!map.containsKey(xsquare)) {
				for(int sq : map.keySet()) {
					int rem = xsquare - sq;
					if(map.containsKey(rem)) {
						System.out.println("Sum of squares of "+map.get(sq) + " and "+map.get(rem)+" = "+x);
					}
				}
				map.put(xsquare,x);
			}
		}

	}

}
