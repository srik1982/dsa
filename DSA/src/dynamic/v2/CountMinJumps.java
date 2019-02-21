package dynamic.v2;

/*
 * Problem Statement

 * Given an array of positive numbers, where each element represents the max number of jumps that can be made forward from that element, write a program to find the minimum number of jumps needed to reach * the end of the array (starting from the first element). If an element is 0, then we cannot move through that element.
 * Example 1:
 * Input = {2,1,1,1,4}
 * Output = 3
 * Explanation: Starting from index '0', we can reach the last index through: 0->2->3->4

 * Example 2:
 * Input = {1,1,3,6,9,3,0,1,3}
 * Output = 4
 * Explanation: Starting from index '0', we can reach the last index through: 0->1->2->3->8
 * 
 * This is one of t he more difficult problems i have faced. So we may have to revisit this multiple times.
 */
public class CountMinJumps {

	public static void main(String[] args) {
		CountMinJumps aj = new CountMinJumps();
		int[] jumps = { 2, 1, 1, 1, 4 };
		System.out.println(aj.getMinJumpsBotUp(jumps));
		jumps = new int[] { 1, 1, 3, 6, 9, 3, 0, 1, 3 };
		System.out.println(aj.getMinJumpsBotUp(jumps));
	}

	public int getMinJumpsBF(int[] arr) {
		return getMinJumpsBF(arr, 0);
	}

	private int getMinJumpsBF(int[] jumps, int currentIndex) {
		if (currentIndex == jumps.length - 1)
			return 0;

		if (jumps[currentIndex] == 0)
			return Integer.MAX_VALUE;

		int minValue = Integer.MAX_VALUE;
		int start = currentIndex + 1;
		int end = currentIndex + jumps[currentIndex];
		for (int i = start; i <= end && i < jumps.length; i++) {
			int x = getMinJumpsBF(jumps, i);
			if (x != Integer.MAX_VALUE) {
				minValue = Math.min(x + 1, minValue);
			}
		}

		return minValue;
	}
	
	public int getMinJumpsTopDown(int[] jumps) {
		int dp[] = new int[jumps.length];
		return getMinJumpsTopDown(jumps,0,dp);
	}

	private int getMinJumpsTopDown(int[] jumps, int index, int[] dp) {
		if (index == jumps.length - 1)
			return 0;

		if (jumps[index] == 0)
			return Integer.MAX_VALUE;
		
		if(dp[index]!=0)return dp[index];
		
		int minValue = Integer.MAX_VALUE;
		int start = index+1;
		int end = index+jumps[index];
		for(int i=start; i<=end && i<jumps.length;i++) {
			int x = getMinJumpsTopDown(jumps, i, dp);
			if(x != Integer.MAX_VALUE)
				minValue = Math.min(minValue, x+1);
		}
		
		dp[index] = minValue;
		
		return dp[index];
	}
	
	public int getMinJumpsBotUp(int[] jumps) {
		int[] dp = new int[jumps.length];
		
		//initialize with infinity, except the first index which should be zero as we start from there
		for(int i=1;i<jumps.length;i++)
			dp[i] = Integer.MAX_VALUE;
		
		for(int i=0;i<jumps.length-1;i++) { // starting from this index, we are jumping. this is done by recursion in the above methods, here its explicit.
			for(int j=i+1;j<=i+jumps[i] && j<jumps.length; j++) { // for each <starting> index, we iterative till max jump
				// As we know, every index within the range of current index can be reached in one jump. 
				// Therefore, we can say that we can reach every index (within the range of current index) in:
			    // 'jumps to reach current index' + 1
				dp[j] = Math.min(dp[j], 1+dp[i]); 
			}
		}
		return dp[jumps.length-1];
	}
}
