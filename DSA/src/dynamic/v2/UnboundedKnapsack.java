package dynamic.v2;

public class UnboundedKnapsack {

	public static void main(String[] args) {
		UnboundedKnapsack ks = new UnboundedKnapsack();
	    int[] profits = { 15, 50, 60, 90 };
	    int[] weights = { 1, 3, 4, 5 };
	    int maxProfit = ks.getMaxProfitBotUpDP(weights,profits, 8);
	    System.out.println(maxProfit);
	}
	
	int getMaxProfitBF(int weights[], int profits[], int capacity) {
		return getMaxProfitBF(weights,profits,0,capacity);
	}

	private int getMaxProfitBF(int[] weights, int[] profits, int index, int capacity) {
		if(index>=weights.length || capacity<=0 || weights.length != profits.length)
			return 0;
		
		int profit1 = 0;
		if(weights[index] <= capacity)
			profit1 = profits[index] + getMaxProfitBF(weights, profits, index, capacity-weights[index]);
		
		int profit2 = getMaxProfitBF(weights, profits, index+1, capacity);
		// TODO Auto-generated method stub
		return Math.max(profit1, profit2);
	}

	int getMaxProfitTopDownDP(int weights[], int profits[], int capacity) {
		Integer[][] dp = new Integer[weights.length][capacity+1];
		
		return getMaxProfitTopDownDP(weights,profits,0,capacity,dp);
	}

	private int getMaxProfitTopDownDP(int[] weights, int[] profits, int index, int capacity,Integer[][] dp) {
		if(index>=weights.length || capacity<=0 || weights.length != profits.length)
			return 0;
		
		if(dp[index][capacity] == null) {
			int profit1 = 0;
			if(weights[index] <= capacity)
				profit1 = profits[index] + getMaxProfitBF(weights, profits, index, capacity-weights[index]);
			
			int profit2 = getMaxProfitBF(weights, profits, index+1, capacity);
			
			dp[index][capacity] = Math.max(profit1, profit2);
		}
		// TODO Auto-generated method stub
		return dp[index][capacity];
	}
	
	int getMaxProfitBotUpDP(int weights[], int profits[], int capacity) {
		int[][] dp = new int[weights.length][capacity+1];
		
		// this is not a set, we are discussing profits here, so we initialize to 0
		for(int i=0;i<weights.length;i++) {
			dp[i][0] = 0;
		}
		
		//this is incorrect for this problem. because we can use the same weight again and again
		//and not only once as it was in earlier problems 
//		for(int i=1;i<=capacity;i++) {
//			dp[0][i] = i == weights[0] ? 1 : 0;
//		}
		
		for(int i=0;i<weights.length;i++) {
			for(int j=1;j<=capacity;j++) {
				int p1 = 0;
				if(weights[i]<=j) {
					p1 = profits[i] + dp[i][j-weights[i]];
				}
				int p2 = 0;
				if(i>0) {
					p2 = dp[i-1][j];
				}
				
				dp[i][j] = Math.max(p1, p2);
			}
		}
		
		System.out.println("printing selected items");
		for(int i=weights.length-1, j=capacity ; i>=0 && j>0; ) {
			int value = dp[i][j];
			if(value == dp[i-1][j]) {
				i--;
			}else {
				System.out.println(weights[i]+" : "+profits[i]);
				j = j - weights[i];
			}
		}
		
		return dp[weights.length-1][capacity];
		
	}
}
