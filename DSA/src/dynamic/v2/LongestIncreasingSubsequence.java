package dynamic.v2;

/*
 * Given a number sequence, find the length of its Longest Increasing Subsequence (LIS). 
 * In an increasing subsequence, all the elements are in increasing order (from lowest to highest).
 */
public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		int[] sequence = new int[]{4,2,3,6,10,1,12};
		System.out.println("Longest Increasing sequence = "+lisDP(sequence));
		int[] nums = new int[]{-4,10,3,7,15};
		System.out.println("Longest Increasing sequence = "+lisDP(nums));
	}
	
	public static int lis(int[] sequence) {
		return lis(sequence,-1,0);
	}

	private static int lis(int[] sequence,int prev, int curr) {
		if(curr == sequence.length) return 0;
		
		int len1 = 0, len2 = 0;
		if(prev == -1 || sequence[prev] <= sequence[curr]) {
			len1 = 1+ lis(sequence, curr, curr+1);
		}
		len2 = lis(sequence,prev, curr+1);
		return Math.max(len1, len2);
	}
	
	public static int lisTopDown(int[] sequence) {
		Integer[][] dp = new Integer[sequence.length][sequence.length];
		return lisTopDown(sequence,-1,0,dp);
	}

	private static int lisTopDown(int[] sequence,int prev, int curr,Integer[][] dp) {
		if(curr == sequence.length) return 0;
		if(dp[prev+1][curr] !=null) {
			return dp[prev+1][curr];
		}
		
		int len1 = 0, len2 = 0;
		if(prev == -1 || sequence[prev] <= sequence[curr]) {
			len1 = 1+ lisTopDown(sequence, curr, curr+1,dp);
		}
		len2 = lisTopDown(sequence,prev, curr+1,dp);
		dp[prev+1][curr] = Math.max(len1, len2);
		return dp[prev+1][curr];
	}
	
	public static int lisDP(int[] seq) {
		int[] dp = new int[seq.length];
		for(int i=0;i<dp.length;i++) {
			dp[i] = 1;
		}
		int max = 0;
		for(int i=1;i<dp.length;i++) {
			for(int j=0;j<i;j++) {
				if(seq[j] < seq[i]) {
					dp[i] = dp[j]+1;
					max = Math.max(dp[i], max);
				}
				//i don't know why we don't have an else case here.
				//ideally we should have. but when i think of it, i am not sure what to write inside else.
			}
		}
		return max;
	}
	/*
	 * for (int i=1; i<nums.length; i++) {
      dp[i] = 1;
      for (int j=0; j<i; j++)
        if (nums[i] > nums[j] && dp[i] <= dp[j] ) {
          dp[i] = dp[j]+1;
          maxLength = Math.max(maxLength, dp[i]);
        }
    }
	 */
}
