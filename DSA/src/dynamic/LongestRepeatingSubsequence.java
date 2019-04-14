package dynamic;

/*
 * https://www.educative.io/collection/page/5668639101419520/5633779737559040/5653294995210240
* Problem Statement
* Given a sequence, find the length of its longest repeating subsequence (LRS). A repeating subsequence will be the one that appears at least twice in the original sequence and is not overlapping (i.e. none of * the  corresponding characters in the repeating subsequences have the same index).

* Example 1:

* Input: "t o m o r r o w"
* Output: 2
* Explanation: The longest repeating subsequence is “or” {tomorrow}.

* Example 2:

* Input: "a a b d b c e c"
* Output: 3
* Explanation: The longest repeating subsequence is “a b c” {a a b d b c e c}.
* 
* Very similar to finding length of Longest Common subsequence.
* Here, instead of comparing 2 strings, we compare 1 strings with two indexes and the indexes should not be equal when the chars are equal
* Just remember this funda.
*/
public class LongestRepeatingSubsequence {

	public static void main(String[] args) {
		LongestRepeatingSubsequence lrs = new LongestRepeatingSubsequence();
	    System.out.println(lrs.findLRS("tomorrow"));
	    System.out.println(lrs.findLRS("aabdbcec"));
	    System.out.println(lrs.findLRS("fmff"));
	}
	
	public int findLRS(String seq) {
		return findLRS(seq,0,0);
	}

	private int findLRS(String seq, int i, int j) {
		if(i==seq.length() || j==seq.length()) {
			return 0;
		}
		int c1 = 0, c2 = 0, c3 = 0;
		if(i!=j && seq.charAt(i) == seq.charAt(j)) {
			c1 = 1 + findLRS(seq,i+1,j+1);
			return c1;
		}
		c2 = findLRS(seq,i,j+1);
		c3 = findLRS(seq,i+1,j);
		return Math.max(c2, c3);
	}
	
	public int findLRS_DP(String seq) {
		int n = seq.length();
		int[][] dp = new int[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i!=j && seq.charAt(i) == seq.charAt(j)) {
					if(i>0 && j>0) {
						dp[i][j] = dp[i-1][j-1]+1;
					}else {
						dp[i][j] = 1;
					}
				}else {
					if(i>0 && j>0) {
						dp[i][j]= Math.max(dp[i-1][j], dp[i][j-1]);
					}
				}
			}
		}
		return dp[n-1][n-1];
	}

}
