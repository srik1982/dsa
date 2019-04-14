package dynamic;

public class MinCoinsCount {

	public static void main(String[] args) {
		MinCoinsCount cc = new MinCoinsCount();
		int[] denominations = {1, 2, 3};
		System.out.println(cc.getMinCoinsCountTopDown(denominations, 5));
		System.out.println(cc.getMinCoinsCountTopDown(denominations, 11));
		System.out.println(cc.getMinCoinsCountTopDown(denominations, 7));
		denominations = new int[]{3, 5};
		System.out.println(cc.getMinCoinsCountTopDown(denominations, 7));
	}
	
	int getMinCoinsCountBF(int[] denom, int total) {
		return getMinCoinsCountBF(denom,0,total);
	}

	private int getMinCoinsCountBF(int[] denom, int index, int total) {
		
		//its count again, just like profit. so return 0
		if(total==0)return 0;
		
		//since we are looking for min count, assign max value
		if(total<0 || index==denom.length) return Integer.MAX_VALUE;
		
		int sum1 = Integer.MAX_VALUE, sum2 = Integer.MAX_VALUE;
		
		if(denom[index] <= total) {
			sum1 = getMinCoinsCountBF(denom, index, total - denom[index]);
			//since we didn't get max value, it means we found some solution. Add 1
			if(sum1!=Integer.MAX_VALUE) {
				sum1 += 1;
			}
		}
		//since this item is not selected, we are not incrementing count. 
		sum2 = getMinCoinsCountBF(denom, index+1, total);
		
		return Math.min(sum1, sum2);
	}

	int getMinCoinsCountTopDown(int[] denom, int total) {
		Integer[][] dp = new Integer[denom.length][total+1];
		return getMinCoinsCountTopDown(denom,0,dp, total);
	}

	private int getMinCoinsCountTopDown(int[] denom, int index,Integer[][] dp, int total) {
		
		//its count again, just like profit. so return 0
		if(total==0)return 0;
		
		//since we are looking for min count, assign max value
		if(total<0 || index==denom.length) return Integer.MAX_VALUE;
		
		if(dp[index][total] !=null) return dp[index][total];
		
		int sum1 = Integer.MAX_VALUE, sum2 = Integer.MAX_VALUE;
		
		if(denom[index] <= total) {
			sum1 = getMinCoinsCountBF(denom, index, total - denom[index]);
			//since we didn't get max value, it means we found some solution. Add 1
			if(sum1!=Integer.MAX_VALUE) {
				sum1 += 1;
			}
		}
		//since this item is not selected, we are not incrementing count. 
		sum2 = getMinCoinsCountBF(denom, index+1, total);
		
		dp[index][total] = Math.min(sum1, sum2);
		return dp[index][total];
	}
	public int getMinCoinsCountBotUp(int[] denominations, int total)
	  {
	    int n = denominations.length;
	    int[][] dp = new int[n][total + 1];

	    for(int i=0; i < n; i++)
	      for(int j=0; j <= total; j++)
	        dp[i][j] = Integer.MAX_VALUE;

	    // populate the total=0 columns, as we don't need any coin to make zero total
	    for(int i=0; i < n; i++)
	      dp[i][0] = 0;

	    for(int i=0; i < n; i++) {
	      for(int j=1; j <= total; j++) {
	        if(i > 0)
	          dp[i][j] = dp[i-1][j]; //exclude the coin
	        if(denominations[i] <= j) {
	          if(dp[i][j-denominations[i]] != Integer.MAX_VALUE)
	            dp[i][j] = Math.min(dp[i][j], dp[i][j-denominations[i]]+1); // include the coin
	        }
	      }
	    }

	    // total combinations will be at the bottom-right corner.
	    return (dp[n-1][total] == Integer.MAX_VALUE ? -1 : dp[n-1][total]);
	  }
}
