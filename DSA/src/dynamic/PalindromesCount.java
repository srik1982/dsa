package dynamic;

/*
 * Problem Statement
 * Given a string, find the total number of palindromic substrings in it. Please note we need to find the total number of substrings and not subsequences.
 
 * Example 1:
 * Input: "abdbca"
 * Output: 7
 * Explanation: Here are the palindromic substrings, "a", "b", "d", "b", "c", "a", "bdb".

 * Example 2:
 * Input: = "cddpd"
 * Output: 7
 * Explanation: Here are the palindromic substrings, "c", "d", "d", "p", "d", "dd", "dpd".

 * Example 3:
 * Input: = "pqr"
 * Output: 3
 * Explanation: Here are the palindromic substrings,"p", "q", "r".
 */
public class PalindromesCount {

	public static void main(String[] args) {
		CPS cps = new CPS();
		System.out.println(cps.findCPS("abdbca"));
		System.out.println(cps.findCPS("cdpdd"));
		System.out.println(cps.findCPS("pqr"));
		System.out.println();
		System.out.println(cps.findCPS2("abdbca"));
		System.out.println(cps.findCPS2("cdpdd"));
		System.out.println(cps.findCPS2("pqr"));
	}

}


class CPS {

	public int findCPS(String st) {
		// dp[i][j] will be 'true' if the string from index 'i' to index 'j' is a
		// palindrome
		boolean[][] dp = new boolean[st.length()][st.length()];
		int count = 0;

		// every string with one character is a palindrome
		for (int i = 0; i < st.length(); i++) {
			dp[i][i] = true;
			count++;
		}

		for (int startIndex = st.length() - 1; startIndex >= 0; startIndex--) {
			for (int endIndex = startIndex + 1; endIndex < st.length(); endIndex++) {
				if (st.charAt(startIndex) == st.charAt(endIndex)) {
					// if it's a two character string or if the remaining string is a palindrome too
					if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
						dp[startIndex][endIndex] = true;
						count++;
					}
				}
			}
		}

		return count;
	}
	
	/*
	 * This one is directly based on palindromic substring. So easy to remember.
	 */
	public int findCPS2(String text) {
		int n = text.length();
		int[][] dp = new int[n][n];
		int count = 0;
		
		for(int i=0;i<n;i++) {
			dp[i][i] = 1;
			count++;
		}
		
		for(int i=n-2;i>=0;i--) {
			for(int j=i+1;j<n;j++) {
				int len = j-i+1;
				//the second condition maps directly to the Top Down solution.
				if(text.charAt(i) == text.charAt(j) && dp[i+1][j-1] == len-2) {
					dp[i][j] = dp[i+1][j-1]+2;
					count++;
				}else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
				}
			}
		}
		
		return count;
	}
}
