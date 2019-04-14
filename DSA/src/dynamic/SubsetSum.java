package dynamic;

import java.util.Arrays;

/*
 * https://www.educative.io/collection/page/5668639101419520/5633779737559040/5646239437684736
 * Given a set of positive numbers, determine if there exists a subset whose sum is equal to a given number ‘S’.
 */
public class SubsetSum {

	public static void main(String[] args) {
		SubsetSum ss = new SubsetSum();
//		int[] num = { 1, 2, 3, 7 };
//		System.out.println(ss.canPartition(num, 6));
		int []num = new int[] { 1, 2, 7, 1, 5 };
		System.out.println(ss.canPartition(num, 10));
//		num = new int[] { 1, 3, 4, 8 };
//		System.out.println(ss.canPartition(num, 6));

	}

	public boolean canPartition(int[] num, int sum) {
		Arrays.sort(num);
		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;
		for (int i = 1; i <= sum; i++)
			dp[i] = num[0] == i;

		for (int i = 1; i < num.length; i++) {
			for (int j = sum; j > 0; j--) {
				if (num[i] <= j) {
					dp[j] = dp[j - num[i]];
				}
			}
		}
		return dp[sum];
	}
	
	public boolean canPartitionDP(int[] num, int sum) {
	    int n = num.length;
	    boolean[][] dp = new boolean[n][sum + 1];

	    // populate the sum=0 columns, as we can always form '0' sum with an empty set
	    for (int i = 0; i < n; i++)
	      dp[i][0] = true;

	    // with only one number, we can form a subset only when the required sum is
	    // equal to its value
	    for (int s = 1; s <= sum; s++) {
	      dp[0][s] = (num[0] == s ? true : false);
	    }

	    // process all subsets for all sums
	    for (int i = 1; i < num.length; i++) {
	      for (int s = 1; s <= sum; s++) {
	        // if we can get the sum 's' without the number at index 'i'
	        if (dp[i - 1][s]) {
	          dp[i][s] = dp[i - 1][s];
	        } else if (s >= num[i]) {
	          // else include the number and see if we can find a subset to get the remaining
	          // sum
	          dp[i][s] = dp[i - 1][s - num[i]];
	        }
	      }
	    }

	    // the bottom-right corner will have our answer.
	    return dp[num.length - 1][sum];
	  }
}
