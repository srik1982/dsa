package dynamic;

/*
 * Given two strings ‘s1’ and ‘s2’, find the length of the longest subsequence which is common in both the strings
 */
public class LongestCommonSubsequence {

	public static void main(String[] args) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
	    System.out.println(lcs.getLCS("abdca", "cbda"));
	    System.out.println(lcs.getLCS("passport", "ppsspt"));
	    System.out.println(lcs.getLCS("florescense", "florence"));
	}
	
	public int getLCSBF(String text1, String text2) {
		return getLCSBF(text1,0,text2,0);
	}

	private int getLCSBF(String text1, int i, String text2, int j) {
		
		if(i==text1.length() || j==text2.length()) return 0;
		
		int s1 = 0, s2=0, s3=0;
		//you can either return within this block or compare all three s1,s2,s3
		//Same result.
		if(text1.charAt(i) == text2.charAt(j)) {
			s1 = 1 + getLCSBF(text1, i+1, text2, j+1);
		}
		
		s2 = getLCSBF(text1,i+1,text2,j);
		s3 = getLCSBF(text1,i,text2,j+1);
		
		return Math.max(Math.max(s1, s2), s3);
	}
	
	public int getLCSMemo(String text1, String text2) {
		Integer[][] dp = new Integer[text1.length()][text2.length()];
		return getLCSMemo(text1,0,text2,0,dp);
	}

	private int getLCSMemo(String text1, int i, String text2, int j,Integer[][] dp) {
		
		if(i==text1.length() || j==text2.length()) return 0;
		
		if(dp[i][j]!=null) return dp[i][j];
		
		int s1 = 0, s2=0, s3=0;
		
		if(text1.charAt(i) == text2.charAt(j)) {
			s1 = 1 + getLCSMemo(text1, i+1, text2, j+1,dp);
		}
		
		s2 = getLCSMemo(text1,i+1,text2,j,dp);
		s3 = getLCSMemo(text1,i,text2,j+1,dp);
		
		dp[i][j] = Math.max(Math.max(s1, s2), s3);
		
		return dp[i][j];
	}
	
	public int getLCSDP(String text1, String text2) {
		if(text1.length() == 0 || text2.length() == 0)return 0;
		 
		int[][] dp = new int[text1.length()+1][text2.length()+1];
		
		for(int i=1;i<=text1.length();i++) {
			for(int j=1;j<=text2.length();j++) {
				if(text1.charAt(i-1) == text2.charAt(j-1)) {
					dp[i][j] = 1 + dp[i-1][j-1];
				}else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		return dp[text1.length()][text2.length()];
	}
	/*
	 * O(2 ^ m+n )
	 */
	public static int getLCS(String text1, String text2) {
		return getLCS(text1,0,text2,0,0);
	}

	private static int getLCS(String text1, int i, String text2, int j, int count) {
		if(i==text1.length() || j == text2.length()) return count;
		int c1 = 0, c2 = 0, c3 = 0; 
		if(text1.charAt(i) == text2.charAt(j)) { 
			c1 = getLCS(text1,i+1, text2, j+1,count+1); 
		} 
		c2 = getLCS(text1, i, text2, j+1,count); 
		c3 =getLCS(text1, i+1, text2, j,count); 
		return Math.max(c1, Math.max(c2, c3)); 
	}
}
