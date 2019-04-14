package dynamic.v2;

import java.util.Arrays;
import java.util.OptionalInt;

/*
 * Given two strings ‘s1’ and ‘s2’, find the length of the longest substring which is common in both the strings
 */
public class LongestCommonSubstring {

	public static void main(String[] args) {
		System.out.println(getLCS("abcd","pqr"));
		System.out.println(getLCS("abcd","b"));
		System.out.println(getLCS("abcd","acdhbc"));
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
		c2 = getLCS(text1, i, text2, j+1,0); 
		c3 =getLCS(text1, i+1, text2, j,0); 
		return Math.max(c1, Math.max(c2, c3)); 
	}
    
	
	/*
	 * There is a small problem with this implementation. this may be suitable for Longest Common Subsequence
	 * since this return 3 for (abcd, acdhbc)
	 */
	/*
	 * private static int getLCS(String text1, int i, String text2, int j) {
	 * if(i==text1.length() || j == text2.length()) return 0;
	 * 
	 * int c1 = 0, c2 = 0, c3 = 0; if(text1.charAt(i) == text2.charAt(j)) { c1 = 1 +
	 * getLCS(text1,i+1, text2, j+1); } c2 = getLCS(text1, i, text2, j+1); c3 =
	 * getLCS(text1, i+1, text2, j); return Math.max(c1, Math.max(c2, c3)); }
	 */
	public static int getLCSMemo(String text1, String text2) {
		Integer[][] dp = new Integer[text1.length()][text2.length()];
		return getLCSMemo(text1,0,text2,0,0,dp);
	}

	private static int getLCSMemo(String text1, int i, String text2, int j, int count, Integer[][] dp) {
		if (i == text1.length() || j == text2.length())
			return count;
		if(dp[i][j] !=null) {
			return dp[i][j];
		}
		int c1 = 0, c2 = 0, c3 = 0;
		if (text1.charAt(i) == text2.charAt(j)) {
			c1 = getLCSMemo(text1, i + 1, text2, j + 1, count + 1, dp);
		}
		c2 = getLCSMemo(text1, i, text2, j + 1, 0, dp);
		c3 = getLCSMemo(text1, i + 1, text2, j, 0, dp);
		dp[i][j] = Math.max(c1, Math.max(c2, c3));
		
		return dp[i][j];
	}
	
	public static int getLCSDP(String text1, String text2) {
		if(text1.length() == 0 || text2.length() == 0) {
			return 0;
		}
		int[][] dp = new int[text1.length()][text2.length()];
		int max = 0;
		for(int i=0;i<text1.length();i++) {
			for(int j=0; j<text2.length(); j++) {
				if(text1.charAt(i) == text2.charAt(j)) {
					if(i>0 && j>0)
						dp[i][j] = 1 + dp[i-1][j-1];
					else
						dp[i][j] = 1;
					
					if(dp[i][j] > max) {
						max = dp[i][j];
					}
				}
			}
		}
		
		StringBuilder sb;
		for(int i=text1.length()-1;i>=0;i--) {
			for(int j= text2.length()-1;j>=0;j--) {
				if(dp[i][j] == max) {
					int i1 = i, j1 = j;
					sb  = new StringBuilder();
					while(i1>=0 && j1>=0 && dp[i1][j1]!=0) {
						sb.append(text1.charAt(i1));
						i1--;
						j1--;
					}
					sb.reverse();
					System.out.println(sb.toString());
				}
			}
		}
		
		return max;
	}
	
	public static int getLCSDP2(String text1, String text2) {
		if(text1.length() == 0 || text2.length() == 0) {
			return 0;
		}
		int[] dp = new int[text2.length()];
		
		for(int i=0;i<text1.length();i++) {
			for(int j=text2.length()-1;j>=0;j--) {
				if(text1.charAt(i) == text2.charAt(j)) {
					if(i>0 &&j>0 && text1.charAt(i-1) == text2.charAt(j-1))
						dp[j] = 1 + dp[j-1];
					else
						dp[j] = 1;
				}
			}
		}
		
		OptionalInt max = Arrays.stream(dp).max();
		return max.getAsInt();
	}
	
}
