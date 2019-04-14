package dynamic;

/*
 * Given a string, find the minimum number of characters that we can remove to make it a palindrome.
 */
public class MinDeletePalindrome {

	public static void main(String[] args) {
		String text = "adhbcbhac";
		PalindromeSubsequence lcs = new PalindromeSubsequence();
		int mindeletions = text.length() - lcs.findSubseqLengthBotUp(text);
		System.out.println("Min Deletions = "+mindeletions);

	}

}
