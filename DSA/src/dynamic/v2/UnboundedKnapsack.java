package dynamic.v2;

public class UnboundedKnapsack {

	public static void main(String[] args) {
		UnboundedKnapsack ks = new UnboundedKnapsack();
	    int[] profits = { 15, 50, 60, 90 };
	    int[] weights = { 1, 3, 4, 5 };
	    int maxProfit = ks.getMaxProfitTopDownDP(weights,profits, 8);
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

}
