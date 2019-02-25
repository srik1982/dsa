package dynamic.v2;

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

	public int palindromeLengthDP(String text) {
		boolean[][] dp = new boolean[text.length()][text.length()];

		for (int i = 0; i < text.length(); i++)
			dp[i][i] = true;

		int maxLength = 1;

		for (int start = text.length() - 2; start >= 0; start--) {
			for (int end = start + 1; end < text.length(); end++) {
				if (text.charAt(start) == text.charAt(end)) {
					if (end - start == 1 || dp[start + 1][end - 1]) {
						dp[start][end] = true;
						maxLength = Math.max(maxLength, end - start + 1);
					}
				}
			}
		}
		return maxLength;

	}
}
