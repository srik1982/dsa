package dynamic.v1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * K(i,W) = { K(i-1,W)  if wi > W 
 *          { Max ( K(i-1,W) , vi + K(i-1, W-wi) ) wi <= W
 * W = Total weight of knapsack
 * Vi is the values corresponding to weights Wi 
 */
public class KnapsackRevisited {

	//map should be replaced with something else. it is overwriting the two 5s.
	//But not hyper important to write it now.
	public static void main(String[] args) {
		KnapsackRevisited obj = new KnapsackRevisited();
		int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
        System.out.println(Arrays.toString(val));
        System.out.println(Arrays.toString(wt));
        
        Map<Integer,Integer> map = obj.selectWeights(wt,val,30);
        System.out.println(map.toString());
        
        int totalWeight = 0, totalValue = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
        	totalWeight += entry.getKey();
        	totalValue += entry.getValue();
        }
        
        System.out.println("Total weight : "+totalWeight);
        System.out.println("Total Value : "+totalValue);
	}
	
	public Map<Integer,Integer> selectWeights(int [] weights, int [] values, int totalWeight){
		int[][] table = new int[weights.length+1][totalWeight+1];
		Map<Integer,Integer> selectedWeights = new HashMap<Integer,Integer>();
		
		for(int i=0;i<=weights.length;i++) {
			for(int j=0;j<=totalWeight;j++) {
				if(i==0 || j==0) {
					table[i][j] = 0;
					continue;
				}
				
				if(weights[i-1] > j) { // if this weight is bigger than j which represents weights, then we drop this weight and take the values for the previous weight;
					table[i][j] = table[i-1][j];
				}else {
					int x = values[i-1] + table[i-1][j-weights[i-1]];
					int y = table[i-1][j];
					if(x > y) {
						table[i][j] =  x;
						System.out.println("max is "+x+" weight : "+ weights[i-1]+" , value: "+values[i-1]);
					}else {
						table[i][j] = y;
					}
				}
			}
		}
		
//		for(int i=0;i<table.length;i++) {
//			StringBuilder sb = new StringBuilder(i+" : ");
//			for(int j=0;j<table[i].length;j++) {
//				sb.append(table[i][j]).append("  ");
//			}
//			System.out.println(sb.toString() );
//		}
//		System.out.println("Returning "+table[weights.length][totalWeight]);
		
		int i = weights.length, j = totalWeight;
		
		while(i>0 && j>0) {
			if(table[i][j] == table[i-1][j]) {// this means weights[i-1] was not considered.
				//so just subtract the row
				i--;
			}else { // just like in the previous loop, reverse engineer
				selectedWeights.put(weights[i-1], values[i-1]);
				j = j-weights[i-1];
				i--;
			}
		}
		
		return selectedWeights;
	}

}
