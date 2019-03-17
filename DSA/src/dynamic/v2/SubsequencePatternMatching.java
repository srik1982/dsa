package dynamic.v2;

/*
 * Given a string and a pattern, write a method to count the number of times the pattern appears in the string as a subsequence.
 */
public class SubsequencePatternMatching {

	public static void main(String[] args) {
		SubsequencePatternMatching spm = new SubsequencePatternMatching();
	    System.out.println(spm.findSPMCountDP("baxmx", "ax"));
	    System.out.println(spm.findSPMCountDP("tttoomrrow", "tor"));
	}
	
	/*
	 * What we do is, match the chars and increment pattern index in one branch so that we can match the first string.
	 * in the second one, we just increment the string and not pattern
	 * And since it is the number of times, we return the sum
	 */
	public int findSPMCount(String str, String pat) {
		return findSPMCount(str,0,pat,0);
	}

	private int findSPMCount(String str, int i, String pat, int j) {
		if(pat.length() == j) {//this mean we have matched all chars of pattern, return 1 to indicate there is 1 matching string
			return 1;
		}
		if(str.length() == i) {//string gets over before the pattern chars indicates we could not find matching subsequences
			return 0;
		}
		int c1 = 0, c2 = 0;
		if(str.charAt(i) == pat.charAt(j)) {
			c1 = findSPMCount(str, i+1, pat, j+1);
		}
		//just go to the next char and start matching.
		// this allows us to find multiple matching subsequences 
		c2 = findSPMCount(str, i+1, pat, j);
		return c1+c2;
	}
	
	public int findSPMCountDP(String str, String pat) {
		int m = str.length();
		int n = pat.length();
		int[][] dp = new int[m+1][n+1];
		for(int i=0;i<=m;i++) {
			dp[i][0] = 1;
		}
		
		for(int i=1;i<=m;i++) {
			for(int j=1;j<=n;j++) {
				int c1 = 0, c2 = 0;
				if(str.charAt(i-1) == pat.charAt(j-1)) {
						c1 = dp[i-1][j-1];
				}
				c2 = dp[i-1][j];
				
				dp[i][j] = c1+c2;
			}
		}
		return dp[m][n];
	}

}
