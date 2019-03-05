package dynamic.v2;

/*
 * Given n stations and cost of travel between every pair of stations (i,j) where i<j,
 * we need to find the most economical way to travel from station 0 to n-1.
 */
public class EconomicalTrainRoute {

	public static void main(String[] args) {
		int cost[][] =  new int[][] {
			{0, 5, 12 , 18, 30},
			{-1 , 0, 10, 12, 15},
			{-1, -1, 0, 3, 7},
			{-1, -1, -1 , 0, 5},
			{-1, -1, -1, -1, 0}
		};
		
		EconomicalTrainRoute trainRoute = new EconomicalTrainRoute();
		int minCost = trainRoute.getMinCostDP(cost);
		System.out.println("Min Cost to travel from 0 to 4 is "+minCost);
	}

	public int getMinCost(int[][] cost) {
		Integer[][] dp = new Integer[cost.length][cost[0].length];
		return getMinCost(cost, 0, cost.length - 1,dp);
	}

	private int getMinCost(int[][] cost, int start, int end, Integer[][] dp ) {
		if (start == end)
			return 0;

		if (start + 1 == end) {
			dp[start][end] = cost[start][end];
			return dp[start][end];
		}
		
		if(dp[start][end] != null) {
			return dp[start][end];
		}

		int minCost = cost[start][end];
		for (int i = start + 1; i < end; i++) {
			int c = getMinCost(cost, start, i, dp) + getMinCost(cost, i, end, dp);
			if (c < minCost) {
				minCost = c;
			}

		}
		
		dp[start][end] = minCost;
		
		return minCost;
	}
	
	public int getMinCostDP(int[][] cost) {
		int[] minCost = new int[cost.length];
		minCost[0] = 0;
		minCost[1] = cost[0][1];
		
		for(int i=2;i<cost.length;i++) {
			minCost[i] = cost[0][i];
			for(int j=1;j<i;j++) {
				int c = minCost[j] + cost[j][i];
				if(c < minCost[i]) {
					minCost[i] = c;
				}
			}
		}
		return minCost[cost.length-1];
	}

}
