package dynamic.v2;

/*
 * https://www.educative.io/collection/page/5668639101419520/5633779737559040/5729201025974272
 * Given two integer arrays to represent weights and profits of ‘N’ items, 
 * we need to find a subset of these items which will give us maximum profit 
 * such that their cumulative weight is not more than a given number ‘C’. 
 * Each item can only be selected once, which means either we put an item in the knapsack or we skip it.
 * 
 */
public class Knapsack {

	public int solveKnapsackBF(int[] profits, int[] weights, int capacity) {
		return this.knapsackRecursiveBF(profits, weights, capacity, 0);
	}

	private int knapsackRecursiveBF(int[] profits, int[] weights, int capacity, int currentIndex) {
		// base checks
		if (capacity <= 0 || currentIndex >= profits.length)
			return 0;

		// recursive call after choosing the element at the currentIndex
		// if the weight of the element at currentIndex exceeds the capacity, we
		// shouldn't process this
		int profit1 = 0;
		if (weights[currentIndex] <= capacity)
			profit1 = profits[currentIndex]
					+ knapsackRecursiveBF(profits, weights, capacity - weights[currentIndex], currentIndex + 1);

		// recursive call after excluding the element at the currentIndex
		int profit2 = knapsackRecursiveBF(profits, weights, capacity, currentIndex + 1);

		return Math.max(profit1, profit2);
	}
	
	public int solveKnapsackTopdown(int[] profits, int[] weights, int capacity) {
	    Integer[][] dp = new Integer[profits.length][capacity + 1];
	    return this.knapsackRecursiveTopDown(dp, profits, weights, capacity, 0);
	  }

	  private int knapsackRecursiveTopDown(Integer[][] dp, int[] profits, int[] weights, int capacity,
	      int currentIndex) {

	    // base checks
	    if (capacity <= 0 || currentIndex >= profits.length)
	      return 0;

	    // if we have already solved a similar problem, return the result from memory
	    if(dp[currentIndex][capacity] != null)
	      return dp[currentIndex][capacity];

	    // recursive call after choosing the element at the currentIndex
	    // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
	    int profit1 = 0;
	    if( weights[currentIndex] <= capacity )
	        profit1 = profits[currentIndex] + knapsackRecursiveTopDown(dp, profits, weights,
	                capacity - weights[currentIndex], currentIndex + 1);

	    // recursive call after excluding the element at the currentIndex
	    int profit2 = knapsackRecursiveTopDown(dp, profits, weights, capacity, currentIndex + 1);

	    dp[currentIndex][capacity] = Math.max(profit1, profit2);
	    return dp[currentIndex][capacity];
	  }
	  
	  public int solveKnapsackBotUp(int[] profits, int[] weights, int capacity) {
		    // basic checks
		    if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
		      return 0;

		    int n = profits.length;
		    int[][] dp = new int[n][capacity + 1];

		    // populate the capacity=0 columns, with '0' capacity we have '0' profit
		    for(int i=0; i < n; i++)
		      dp[i][0] = 0;
		    
		    //this initial condition is more important than the entire program
		    // if we have only one weight, we will take it if it is not more than the capacity
		    for(int c=0; c <= capacity; c++) {
		      if(weights[0] <= c)
		        dp[0][c] = profits[0];
		    }

		    // process all sub-arrays for all the capacities
		    for(int i=1; i < n; i++) {
		      for(int c=1; c <= capacity; c++) {
		        int profit1= 0, profit2 = 0;
		        // include the item, if it is not more than the capacity
		        if(weights[i] <= c)
		          profit1 = profits[i] + dp[i-1][c-weights[i]];
		        // exclude the item
		        profit2 = dp[i-1][c];
		        // take maximum
		        dp[i][c] = Math.max(profit1, profit2);
		      }
		    }

		    // maximum profit will be at the bottom-right corner.
		    return dp[n-1][capacity];
		  }

	static int solveKnapsack(int[] profits, int[] weights, int capacity) {
		// TODO: Write - Your - Code
		int[][] dp = new int[2][capacity + 1];

		for (int i = 1; i <= capacity; i++) {
			dp[0][i] = profits[0];
			dp[1][i] = profits[0];
		}

		for (int i = 1; i < profits.length; i++) {
			for (int j = 1; j <= capacity; j++) {
				if (j >= weights[i]) {
					int p1 = dp[(i - 1) % 2][j];
					int p2 = profits[i] + dp[(i - 1) % 2][j - weights[i]];
					dp[i % 2][j] = Math.max(p1, p2);
				} else {
					dp[i % 2][j] = dp[(i - 1) % 2][j];
				}
			}
		}
		int n = profits.length;
		return dp[(n - 1) % 2][capacity];
	}

	public static void main(String[] args) {
		System.out.println(Knapsack.solveKnapsack(new int[] { 1, 6, 10, 16 }, new int[] { 1, 2, 3, 5 }, 7));
	}
}
