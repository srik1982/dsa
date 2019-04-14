package dynamic;

/*
 * https://www.educative.io/collection/page/5668639101419520/5633779737559040/5748119283171328
 * 
 * Problem Statement
 * Given a sequence, find the length of its Longest Palindromic Subsequence (LPS). In a palindromic subsequence, elements read the same backward and forward.
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: "abdbca"
 * Output: 5
 * Explanation: LPS is "abdba".
 *
 * Example 2:
 * Input: = "cddpd"
 * Output: 3
 * Explanation: LPS is "ddd".
 */
public class PalindromeSubsequence {

	public static void main(String[] args) {
		PalindromeSubsequence lps = new PalindromeSubsequence();
	    System.out.println(lps.findSubseqLengthBotUp("abdbca"));
	    System.out.println(lps.findSubseqLengthBotUp("cddpd"));
	    System.out.println(lps.findSubseqLengthBotUp("pqr"));
	}
	
	public int findSubseqLength(String text) {
		return findSubseqLength(text.toCharArray(),0,text.length()-1);
	}
	
	private int findSubseqLength(char[] text, int start, int end) {
		
		if(start == end) {//you have either converged from both ends or there is only 1 char in the text
			//either way, now you are comparing only one char. 1 char is palindrome by default. so return 1;
			return 1;
		}
		if(start > end) {//may be previous char was a match so you incremented start and decremented end and reached here.
			// we should just end the recursion. there is nothing to compare, so return 0
			return 0;
		}
		
		if(text[start] == text[end]) {//match, so we increment start, and decrement end
			int length = 2 + findSubseqLength(text, start+1, end-1);
			return length;
		}else {
			int l1 = 0, l2 = 0;
			l1 = findSubseqLength(text, start+1, end);
			l2 = findSubseqLength(text, start, end-1);
			return Math.max(l1,l2);
		}
		
	}
	
	public int findSubseqLengthTopDown(String text) {
		Integer[][] len = new Integer[text.length()][text.length()];
		return findSubseqLengthTopDown(text.toCharArray(),0,text.length()-1,len);
	}

	private int findSubseqLengthTopDown(char[] text, int start, int end, Integer[][] len) {
		if(start == end) {//you have either converged from both ends or there is only 1 char in the text
			//either way, now you are comparing only one char. 1 char is palindrome by default. so return 1;
			return 1;
		}
		if(start > end) {//may be previous char was a match so you incremented start and decremented end and reached here.
			// we should just end the recursion. there is nothing to compare, so return 0
			return 0;
		}
		
		if(len[start][end]==null) {
			if(text[start] == text[end]) {//match, so we increment start, and decrement end
				len[start][end] = 2 + findSubseqLength(text, start+1, end-1);
			}else {
				int l1 = 0, l2 = 0;
				l1 = findSubseqLength(text, start+1, end);
				l2 = findSubseqLength(text, start, end-1);
				len[start][end] = Math.max(l1,l2);
			}
			
		}
		return len[start][end];
	}
	
	public int findSubseqLengthBotUp(String text) {
		return findSubseqLengthBotUp(text.toCharArray());
	}
	/*
	 * https://www.educative.io/collection/page/5668639101419520/5633779737559040/5748119283171328
	 */
	public int findSubseqLengthBotUp(char[] text) {
		int[][] slen = new int[text.length][text.length]; // the dp table represents the subsequence length
		//slen[1][4] = 3 means there is a subsequence between indexes 1 and 4 with length 3
		
		for(int i=0;i<text.length;i++) {
			slen[i][i] = 1; // because if we have single character, its a palindrome with length 1.
		}
		
		for(int start=text.length-2; start>=0;start--) {
			for(int end=start+1;end<text.length;end++) {
				if(text[start] == text[end]) {//diagonoal + 2
					slen[start][end] = slen[start+1][end-1] + 2;
				}else {
					slen[start][end] = Math.max(slen[start+1][end], slen[start][end-1]);
				}
			}
		}
		return slen[0][text.length-1];
	}
}
