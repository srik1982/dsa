package dynamic.v2;

/*
 * Given an integer array, find the minimum deletions to be made so that the array becomes sorted.
 */
public class MinDeletionsToSort {

	public static void main(String[] args) {
		//Min deletions to sort = Length of the array - Length of longest increasing subsequence
		int[] nums = {4,2,3,6,10,1,12};
	    System.out.println(mdts(nums));
	    nums = new int[]{-4,10,3,7,15};
	    System.out.println(mdts(nums));
	    //Fails for this one. Check LongestIncreasingSubsequence again
	    nums = new int[]{3,2,1,0};
	    System.out.println(mdts(nums));
	}
	
	public static int mdts(int[] arr) {
		return arr.length - LongestIncreasingSubsequence.lisTopDown(arr);
	}

}
