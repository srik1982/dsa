package dynamic;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a number sequence, find the length of its Longest Bitonic Subsequence (LBS). 
 * A subsequence is considered bitonic if it is monotonically increasing and then monotonically decreasing.
 */
public class LongestBitonicSubsequence {

	public static void main(String[] args) {
		LongestBitonicSubsequence lbs = new LongestBitonicSubsequence();
		int[] nums = { 4, 2, 3, 6, 10, 1, 12 };
		System.out.println(lbs.findLBS_DP(nums));
		nums = new int[] { 4, 2, 5, 9, 7, 6, 10, 3, 1 };
		System.out.println(lbs.findLBS_DP(nums));
	}

	/*
	 * We already know about Longest Increasing Subsequence We write program for
	 * Longest Decreasing Subsequence and find LDS and reverse LDS from every index
	 * i. Then use the maximum value.
	 */
	public int findLBS(int[] seq) {
		int c1 = 0, c2 = 0, max = 0;
		Map<String, Integer> memo = new HashMap<>();
		for (int i = 1; i < seq.length - 1; i++) {
			c1 = getLDS(seq, -1, i, memo);
			c2 = getLIS(seq, -1, i, memo);
			max = Math.max(c1 + c2 - 1, max);
		}
		return max;
	}

	private int getLIS(int[] seq, int prev, int curr, Map<String, Integer> memo) {
		if (curr == -1) {
			return 0;
		}
		String key = prev + "-" + curr;
		if (!memo.containsKey(key)) {
			int c1 = 0, c2 = 0;
			if (prev == -1 || seq[curr] < seq[prev]) {
				c1 = 1 + getLIS(seq, curr, curr - 1, memo);
			}

			c2 = getLIS(seq, prev, curr - 1, memo);
			memo.put(key, Math.max(c1, c2));
		}
		return memo.get(key);
	}

	private int getLDS(int[] seq, int prev, int curr, Map<String, Integer> memo) {
		if (curr == seq.length) {
			return 0;
		}
		String key = prev + "|" + curr;
		if (!memo.containsKey(key)) {
			int c1 = 0, c2 = 0;
			if (prev == -1 || seq[curr] < seq[prev]) {
				c1 = 1 + getLDS(seq, curr, curr + 1, memo);
			}

			c2 = getLDS(seq, prev, curr + 1, memo);
			memo.put(key, Math.max(c1, c2));
		}
		return memo.get(key);
	}
	
	public int findLBS_DP(int[] seq) {
		int n = seq.length;
		if(n==0 || n==1)return n;
		
		int[] lds = new int[n];
		int[] lis = new int[n];
		
		for(int i=n-1;i>=0;i--) {
			lds[i] = 1;
			for(int j=i+1;j<n;j++) {
				if(seq[j] < seq[i]) {
					lds[i] = Math.max(lds[i], lds[j]+1);
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			lis[i] = 1;
			for(int j=i-1;j>=0;j--) {
				if(seq[j] < seq[i]) {
					lis[i] = Math.max(lis[i], lis[j]+1);
				}
			}
		}
		
		int maxLength = 0;
		for(int i=0;i<n;i++) {
			maxLength = Math.max(maxLength, lis[i]+lds[i]-1);
		}
		return maxLength;
	}

}
