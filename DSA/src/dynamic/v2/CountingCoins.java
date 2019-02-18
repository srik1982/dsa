package dynamic.v2;

public class CountingCoins {

	public static void main(String[] args) {
		CountingCoins cc = new CountingCoins();
	    int[] denominations = {1, 2, 3};
	    System.out.println(cc.getNumberOfSetsWithBotUpDP(denominations, 5));
	}
	
	public int getNumberOfSetsWithSumBF(int[] coinVals, int sum) {
		return getNumberOfSetsWithSumBF(coinVals,0,sum);
	}

	/*
	 * 2 power n
	 */
	private int getNumberOfSetsWithSumBF(int[] coinVals, int index, int sum) {
		
		if(sum==0)return 1;
		
		if(index==coinVals.length || sum<0) return 0;
		
		int sum1 = 0, sum2 = 0;
		if(coinVals[index] <= sum) {
			 sum1 = getNumberOfSetsWithSumBF(coinVals, index, sum - coinVals[index]);
		}
		
		sum2 = getNumberOfSetsWithSumBF(coinVals, index+1, sum);
		
		return sum1+sum2;
	}
	
	/*
	 * Time complexity of N * S where N is the number of coins and S is the total
	 * Space complexity is N*S + N for recursion
	 */
	public int getNumberOfSetsWithSumTopDownDP(int[] coinVals, int sum) {
		Integer[][] dp = new Integer[coinVals.length][sum+1];
		return getNumberOfSetsWithSumTopDownDP(coinVals,0,sum,dp);
	}

	private int getNumberOfSetsWithSumTopDownDP(int[] coinVals, int index, int sum,Integer[][] dp) {
		
		if(sum==0)return 1;
		
		if(index==coinVals.length || sum<0) return 0;
		
		if(dp[index][sum]!=null) {
			return dp[index][sum];
		}
		
		int sum1 = 0, sum2 = 0;
		if(coinVals[index] <= sum) {
			 sum1 = getNumberOfSetsWithSumBF(coinVals, index, sum - coinVals[index]);
		}
		
		sum2 = getNumberOfSetsWithSumBF(coinVals, index+1, sum);
		
		return sum1+sum2;
	}
	
	public int getNumberOfSetsWithBotUpDP(int[] coinVals, int sum) {
		int[][] dp = new int[coinVals.length][sum+1];
		
		//we are looking for sets here. And empty sets can return 0 sum. Hence 1
		for(int i=0;i<coinVals.length;i++) {
			dp[i][0] = 1;
		}
		
		for(int i=0;i<coinVals.length;i++) {
			for(int j=1;j<=sum;j++) {
				int sum1 = 0, sum2 = 0;
				if(coinVals[i]<=j) {
					sum1 = dp[i][j-coinVals[i]];
				}
				if(i>0)
					sum2 = dp[i-1][j];
				dp[i][j] = sum1+sum2;
			}
		}
		
		return dp[coinVals.length-1][sum];
	}

}
