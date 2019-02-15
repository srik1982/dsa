package dynamic.v2;

import java.util.Arrays;

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
}
