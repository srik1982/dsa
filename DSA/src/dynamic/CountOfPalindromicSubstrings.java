package dynamic;

/*
 * Extension of Palindromic SubString, just need to count the substrings.
 * So i will copy and modify PalindromicSubstring
 */
public class CountOfPalindromicSubstrings {

	public static void main(String[] args) {
		CountOfPalindromicSubstrings lps = new CountOfPalindromicSubstrings();
		System.out.println(lps.palindromeCount("abdbca"));
		System.out.println(lps.palindromeCount("cddpd"));
		System.out.println(lps.palindromeCount("pqr"));
		System.out.println(lps.palindromeCount("abcbda"));
		System.out.println(lps.palindromeCount("adhbcbha"));
	}
	
	public int palindromeCount(String text) {
		int n = text.length();
		int[][] dp = new int[n][n];
		int count=text.length(); // at least as many palindromes as the chars in the string.
		
		for(int i=0;i<n;i++) {
			dp[i][i] = 1;
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
