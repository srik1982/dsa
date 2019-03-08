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
