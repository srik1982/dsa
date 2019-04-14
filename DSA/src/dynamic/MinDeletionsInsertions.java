package dynamic;

/*
 * Given strings s1 and s2, we need to transform s1 into s2 by deleting and inserting characters. 
 * Write a function to calculate the count of the minimum number of deletion and insertion operations.
 */
public class MinDeletionsInsertions {

	public static void main(String[] args) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		int lcs1 = lcs.getLCSDP("abc", "fbc");
		System.out.println("Number of deletions for 1 = "+("abc".length()-lcs1));
		System.out.println("Number of insertions for 1 = "+("fbc".length()-lcs1));
		int lcs2 = lcs.getLCSDP("abdca", "cbda");
		System.out.println("Number of deletions for 2 = "+("abdca".length()-lcs2));
		System.out.println("Number of insertions for 2 = "+("cbda".length()-lcs2));
		int lcs3 = lcs.getLCSDP("passport", "ppsspt");
		System.out.println("Number of deletions for 3 = "+("passport".length()-lcs3));
		System.out.println("Number of insertions for 3 = "+("ppsspt".length()-lcs3));
		
	  }
}
