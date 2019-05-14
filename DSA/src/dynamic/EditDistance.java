package dynamic;

public class EditDistance {

	public static void main(String[] args) {
		EditDistance editDisatnce = new EditDistance();
	    System.out.println(editDisatnce.findChangesCountDP("bat", "but"));
	    System.out.println(editDisatnce.findChangesCountDP("abdca", "cbda"));
	    System.out.println(editDisatnce.findChangesCountDP("passpot", "ppsspqrt"));

	}
	
	public int findChangesCount(String text1, String text2) {
		return findChangesCount(text1,0,text2,0);
	}
	/*
	 * we have to transform text1 into text2. We are findig out the min number of operations to do so.
	 * I am not going to write a top down approach with memoization. Just use a 2D array for that since we have 2 variables here - i and j
	 * With memoization, T(n) = O(m*n) and Space = O(m*n) + O(max(m,n)) for recursive stack.
	 */
	private int findChangesCount(String text1, int i, String text2, int j) {
		if(i == text1.length())return text2.length() - j;
		if(j == text2.length())return text1.length() - i;
		
		if(text1.charAt(i) == text2.charAt(j)) {
			return findChangesCount(text1,i+1,text2,j+1);
		}
		//deletion in text1. skipping 1 char in i,is equivalent of deleting 
		int c1 = 1+ findChangesCount(text1,i+1,text2,j);
		//perform insertion. we add 1 char to text1, so we don't increment i, but increment j
		int c2 = 1+ findChangesCount(text1,i,text2,j+1);
		//perform updation. Same as when both chars match above. 
		int c3 = 1+ findChangesCount(text1,i+1,text2,j+1);
		
		return Math.min(c1, Math.min(c2, c3));
	}
	
	public int findChangesCountDP(String text1, String text2) {
		// if you see the top down approach, the base conditions don't return 0. its a variable.
		// it returns the number of chars left in the other string.
		//|||ly in bottom up approach, if text1 is null or empty, text1 has to be inserted all the chars in text2 
		int m = text1.length();
		int n = text2.length();
		
		int[][] dp = new int[m+1][n+1];
		for(int i=0;i<n;i++) {
			dp[0][i] = i;
		}
		for(int i=0;i<n;i++) {
			dp[i][0] = i;
		}
		
		for(int i=1;i<=m;i++) {
			for(int j=1;j<=n;j++) {
				if(text1.charAt(i-1) == text2.charAt(j-1)) {				
					dp[i][j] = dp[i-1][j-1];				
				}else {
					int c1 = 1 + dp[i-1][j]; //deletion. we skip the ith char and take the dp value for i-1th row/char
					int c2 = 1 + dp[i][j-1]; //insertion. ith char is a new insertion, so we compare with j-1 instead of j.
					int c3 = 1 + dp[i-1][j-1]; //transform the char being compared.
					
					dp[i][j] = Math.min(c1, Math.min(c2, c3));
				}
			}
		}
		
		return dp[m][n];
	}

}
