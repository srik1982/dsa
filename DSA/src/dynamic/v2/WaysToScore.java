package dynamic.v2;

/*
 * A player can score in 3,5,10 runs. If the player has to score N runs, in how many possible ways can he do it?
 * This looks like a question from permutations to me. It is also similar to unbounded knapsack.
 * 
 * Remember here, we are talking about a set like {3,5,5} = 13 and {5,3,5}. Both are different. This is one difference from knapsack.
 * 
 */
public class WaysToScore {

	public static void main(String[] args) {
		System.out.println(findWays2ScoreDp2(new int[] {3,5,10},13));

	}
	
	public static int findWays2Score(int total) {
		if(total < 0) return 0;
		
		if(total == 0) return 1;
		
		return findWays2Score(total-3) + findWays2Score(total-5) + findWays2Score(total-10); 
	}
	
	public static int findWays2Score(int[] runs, int total) {
		return findWays2Score(runs,0,total);
	}
	
	/*
	 * This approach returns 2 instead of 5 for total 13.
	 * The two sets being {3,5,5} and {3,10}.
	 * Since the index only increments, once we skip 3 and go to index 1 i.e., select 5, we cannot select 3.
	 * This is a drawback with this approach.
	 * You can say that this returns combinations and not permutations. 
	 */
	private static int findWays2Score(int[] runs, int i, int total) {
		if(total < 0 || i >= runs.length) return 0;
		
		if(total == 0) return 1;
		
		int s1 = 0, s2 = 0;
		if(runs[i] <=total) {
			s1 = findWays2Score(runs, i, total - runs[i]);
		}
		
		s2 = findWays2Score(runs, i+1, total);
		
		return s1+s2;
	}
	
	/*
	 * This is the improvement over the normal unbounded knapsack, in that we don't use index and go through the whole runs[] array at each step.
	 */
	private static int findWays2Score2(int runs[], int total) {
		if(total < 0) return 0;
		
		if(total == 0) return 1;
		
		int sum = 0;
		for(int i=0;i<runs.length;i++) {
			int s = findWays2Score2(runs, total-runs[i]);
			sum+=s;
		}
		return sum;
	}
	
	/*
	 * This Dp solution has the same problem as the line on line #35
	 * Gives 2 instead of 5 because we are running in the loop from i = 0 to runs.length
	 * Lets reverse the loops and see what happens
	 */
	private static int findWays2ScoreDp(int runs[], int total) {
		int [][] dp = new int[runs.length][total+1];
		
		for(int i=0;i<runs.length;i++)
			dp[i][0] = 1;
		
		for(int i=0;i<runs.length;i++) {
			for(int j=1;j<=total;j++) {
				if(runs[i] <= j) {
					dp[i][j] = dp[i][j-runs[i]];
				}
				if(i>0)
					dp[i][j] += dp[i-1][j];
			}
		}
		
		return dp[runs.length-1][total];
	}
	
	/*
	 * Again, i could not use the typical DP logic of 2d array and select vs not select approach.
	 * For each value of sum, we need to consider all 3 run values. so i had to compress the 2d to 1d array.
	 * I hope i can remember this. I will have to write this in flash card.
	 */
	private static int findWays2ScoreDp2(int runs[], int total) {
		int [] dp = new int[total+1];
		
		for(int i=0;i<runs.length;i++)
			dp[0] = 1;
		
		for(int j=1;j<=total;j++) {
			int sum = 0;
			for(int i=0;i<runs.length;i++) {
				if(runs[i] <= j) {
					sum+= dp[j-runs[i]];
				}
			}
			dp[j] = sum;
		}
		return dp[total];
	}
}
