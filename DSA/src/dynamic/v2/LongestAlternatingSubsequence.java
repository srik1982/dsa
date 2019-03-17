package dynamic.v2;

/*
 * Given a number sequence, find the length of its Longest Alternating Subsequence (LAS). 
 * A subsequence is considered alternating if its elements are in alternating order.
 */
public class LongestAlternatingSubsequence {

	public static void main(String[] args) {
		LongestAlternatingSubsequence las = new LongestAlternatingSubsequence();
	    int[] nums = {1,2,3,4};
	    System.out.println(las.findLASLength(nums));
	    nums = new int[]{3,2,1,4};
	    System.out.println(las.findLASLength(nums));
	    nums = new int[]{1,3,2,4};
	    System.out.println(las.findLASLength(nums));

	}
	
	/*
	 * Exponential. 2 power n
	 * Use memoization with 2 separate hashmaps/2d arrays
	 */
	public int findLASLength(int[] seq) {
		return Math.max(findLAS(seq,-1,0,true),findLAS(seq,-1,0,false)); 
	}

	private int findLAS(int[] seq, int prev, int curr, boolean isAsc) {
		if(curr == seq.length)return 0;
		
		int c1 = 0, c2 = 0;
		if(isAsc && (prev==-1 || seq[prev] < seq[curr])) {
			c1 = 1+findLAS(seq, curr,curr+1,false);
		}else if(!isAsc && (prev == -1 || seq[prev] > seq[curr])) {
			c1 = 1+findLAS(seq, curr,curr+1,true);
		}
		c2 = findLAS(seq,prev,curr+1,isAsc);
		return Math.max(c1, c2);
	}

}
