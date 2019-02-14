package dynamic.v1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 0/1 Knapsack Problem - Given items of certain weights/values and maximum allowed weight
 * how to pick items to pick items from this set to maximize sum of value of items such that
 * sum of weights is less than or equal to maximum allowed weight.
 *
 * Time complexity - O(W*total items)
 *
 * Youtube link
 * Topdown DP - https://youtu.be/149WSzQ4E1g
 * Bottomup DP - https://youtu.be/8LusJS5-AGo
 *
 * References -
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 * https://en.wikipedia.org/wiki/Knapsack_problem
 * 
 * We also need to find out and prints the weights and values of the items that make up knapsack.
 * But i wanted to move on to next problem. So lets revisit this problem in the second iteration.
 */
public class Knapsack01 {

	public static void main(String[] args) {
		Knapsack01 k = new Knapsack01();
        int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
        System.out.println(Arrays.toString(val));
        System.out.println(Arrays.toString(wt));
        int r = k.bottomUpDp(wt,val, 30);
        System.out.println(r);
//        int r = k.topDownDp(wt, val, 30);
//        System.out.println(r);
	}
	
	class Index{
		int remainingWeight;
		int remainingItems;
		
		Index(int remainingWeight, int remainingItems){
			this.remainingWeight = remainingWeight;
			this.remainingItems = remainingItems;
		}
		
		public int hashcode() {
			int retVal = remainingWeight;
			retVal = 31*retVal + remainingItems;
			return retVal;
		}
		
		public boolean equals(Object o) {
			if(o == this) return true;
			
			if(o == null || !(o instanceof Index)){
				return false;
			}
			
			Index i = (Index) o;
			
			return i.remainingItems == remainingItems && i.remainingWeight == remainingWeight;
		}
	}
	
	public int topDownDp(int wts[], int vals[], int totalWt) {
		Map<Index,Integer> map = new HashMap<Index,Integer>();
		return topDownDp(wts, vals, totalWt, totalWt,0, map);
	}
	
	
	private int topDownDp(int[] wts, int[] vals, int remWt, int totalWt, int currentItem, Map<Index, Integer> map) {
		if(remWt <= 0 || currentItem >= wts.length) {
			return 0;
		}
		
		Index index = new Index(remWt,wts.length-currentItem);
		
		if(map.containsKey(index)) {
			return map.get(index);
		}
		
		int maxValue;
		if(remWt < wts[currentItem]) {
			maxValue = topDownDp(wts,vals,remWt,totalWt,currentItem+1,map);
		}else {
			int right = topDownDp(wts,vals,remWt,totalWt,currentItem+1,map);
			int left = vals[currentItem]+ topDownDp(wts,vals,remWt - wts[currentItem], totalWt,currentItem+1,map);
			maxValue = Math.max(left, right);
		}
		map.put(index, maxValue);
		return maxValue;
	}


	public int bottomUpDp(int [] wts, int []vals, int totalWt) {
		int cache[][] = new int[wts.length+1][totalWt+1];
		for(int i=0;i<=wts.length;i++) { // rows represent the different weights and values
			for(int j=0;j<=totalWt;j++) { // this represents all weights from 1 to totalWt
				if(i==0 || j==0) {
					cache[i][j] = 0;
					continue;
				}
				if(j >= wts[i-1]) { // compare with wts[i-1] because we have left first row and columns zeroes so really its the row/col 1 that matters
					//we compare
					// 1. what if we don't pick this weight? Then we have values for previous row same col => cache[i-1][j]
					//2. if we choose this element i.e., vals[i-1], then we are left with some weight which is equal to cache[i-1][j-wts[i-1]]
					// j is also the total weight so, j-wts[i-1] is the remaining difference in weight
					// so we check the previous row for the remaining weight
					// this is because in DP, we use already computed values and in this case, cache[i-1][j-wts[i-1]]
					//why previous row? not sure from the video. may be watch another video.
					cache[i][j] = Math.max(vals[i-1]+ cache[i-1][j - wts[i-1]], cache[i-1][j]);
				}else { // j < wts[i-1] => wts are small, so the values are same as for previous row same column. Don't ask me why.
					cache[i][j] = cache[i-1][j];
				}
			}
		}
		
		for(int i=0;i<cache.length;i++) {
			StringBuilder sb = new StringBuilder(i+" : ");
			for(int j=0;j<cache[i].length;j++) {
				sb.append(cache[i][j]).append("  ");
			}
			System.out.println(sb.toString() );
		}
		System.out.println("Returning "+cache[wts.length][totalWt]);
		return cache[wts.length][totalWt];
	}
	
	

}
