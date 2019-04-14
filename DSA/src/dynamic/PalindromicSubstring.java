package dynamic;

/*
 * Refer Before: PalindromeSubsequence.java
 * asasssssssssssssssssssa
 * https://www.educative.io/collection/page/5668639101419520/5633779737559040/5661601461960704
 * Problem Statement
 * Given a string, find the length of its Longest Palindromic Substring (LPS). In a palindromic string, elements read the same backward and forward.
 
 * Example 1:
 * Input: "abdbca"
 * Output: 3
 * Explanation: LPS is "bdb".

 * Example 2:
 * Input: = "cddpd"
 * Output: 3
 * Explanation: LPS is "dpd".
 * 
 * 
 */
public class PalindromicSubstring {

	public static void main(String[] args) {
		PalindromicSubstring lps = new PalindromicSubstring();
		System.out.println(lps.palindromeLengthDP("abdbca"));
		System.out.println(lps.palindromeLengthDP("cddpd"));
		System.out.println(lps.palindromeLengthDP("pqr"));
		System.out.println(lps.palindromeLengthDP("abcbda"));
		System.out.println(lps.palindromeLengthDP("adhbcbha"));
		System.out.println();
		System.out.println(lps.palindromeLengthDP2("abdbca"));
		System.out.println(lps.palindromeLengthDP2("cddpd"));
		System.out.println(lps.palindromeLengthDP2("pqr"));
		System.out.println(lps.palindromeLengthDP2("abcbda"));
		System.out.println(lps.palindromeLengthDP2("adhbcbha"));
	}

	public int palindromeLength(String text) {
		return palindromeLength(text, 0, text.length() - 1);
	}

	private int palindromeLength(String text, int start, int end) {

		if (start > end)
			return 0;

		if (start == end)
			return 1;

		int p1 = 0, p2 = 0, p3 = 0;

		if (text.charAt(start) == text.charAt(end)) {
			int length = end - start - 1;
			if (length == palindromeLength(text, start + 1, end - 1)) {
				p1 = length + 2;
			}
		}

		p2 = palindromeLength(text, start + 1, end);
		p3 = palindromeLength(text, start, end - 1);

		return Math.max(Math.max(p1, p2), p3);
	}
	/*
	 * There are problems with this.
	 * Also not intuitive because Palindromic subsequence uses a int[][] dp array and this uses a boolean[][]
	 */
	public int palindromeLengthDP(String text) {
		boolean[][] dp = new boolean[text.length()][text.length()];

		for (int i = 0; i < text.length(); i++)
			dp[i][i] = true;

		int maxLength = 1;
		int startIndex = -1, endIndex = -1;

		for (int start = text.length() - 2; start >= 0; start--) {
			for (int end = start + 1; end < text.length(); end++) {
				if (text.charAt(start) == text.charAt(end)) {
					if (end - start == 1 || dp[start + 1][end - 1]) {
						if (maxLength < end - start + 1) {
							startIndex = start;
							endIndex = end;
							maxLength = endIndex - startIndex + 1;
						}
					}
				}
			}
		}
//		if (maxLength > 1)
//			System.out.println(" palindrome = " + text.substring(startIndex, endIndex + 1));

		return maxLength;

	}
	
	/*
	 * Learn and remember this. Intuitive and easy to remember.
	 */
	public int palindromeLengthDP2(String text) {
		int n = text.length();
		int[][] dp = new int[n][n];
		
		for(int i=0;i<n;i++) {
			dp[i][i] = 1;
		}
		
		for(int i=n-2;i>=0;i--) {
			for(int j=i+1;j<n;j++) {
				int len = j-i+1;
				//the second condition maps directly to the Top Down solution.
				if(text.charAt(i) == text.charAt(j) && dp[i+1][j-1] == len-2) {
					dp[i][j] = dp[i+1][j-1]+2;
				}else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
				}
			}
		}
		
		return dp[0][n-1];
	}
}
