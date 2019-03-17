package dynamic.v2;

public class MinLengthSuperSequence {

	public static void main(String[] args) {
		MinLengthSuperSequence scs = new MinLengthSuperSequence();
	    System.out.println(scs.getMLSS_DP("abcf", "bdcf"));
	    System.out.println(scs.getMLSS_DP("dynamic", "programming"));

	}
	
	public int getMLSS(String text1, String text2) {
		return getMLSS(text1, 0, text2, 0);
	}

	private int getMLSS(String text1, int i, String text2, int j) {
		if(i==text1.length())return text2.length()-j;
		if(j==text2.length())return text1.length()-i;
		
		int l1=0,l2=0,l3=0;
		if(text1.charAt(i) == text2.charAt(j)) {
			l1 = 1 + getMLSS(text1,i+1,text2,j+1);
			return l1;
		}
		l2 = 1 + getMLSS(text1,i+1,text2,j);
		l3 = 1 + getMLSS(text1,i,text2,j+1);
		return Math.min(l2, l3);
	}
	
	public int getMLSS_DP(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();
		int[][] dp = new int[m+1][n+1];
		//if one of t he strings is null, then the length of super sequence is same as 
		//the length of the other string
		for(int i=0;i<=m;i++) {
			dp[i][0] = i;
		}
		for(int i=0;i<=n;i++) {
			dp[0][i] = i;
		}
		
		for(int i=1;i<=m;i++) {
			for(int j=1;j<=n;j++) {
				if(text1.charAt(i-1) == text2.charAt(j-1)) {
					dp[i][j] = 1 + dp[i-1][j-1];
				}else {
					dp[i][j] = 1+Math.min(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp[m][n];
	}
}
