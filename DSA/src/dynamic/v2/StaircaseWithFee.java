package dynamic.v2;

/*
 *  * Problem Statement
 * 
 * Given a staircase with ‘n’ steps and an array of ‘n’ numbers representing the fee that you have to pay if you take the step. Implement a method to calculate the minimum fee required to reach the top of the staircase. At every step, you have an option to take either 1 step, 2 steps, or 3 steps. You should assume that you are standing at the first step.
 * At each step, we can jump 1 or 2 or 3 jumps
 * Example 1:
 * Number of stairs (n) : 6
 * Fee: {1,2,5,2,1,2}
 * Output: 3
 * Explanation: Starting from index '0', we can reach the last index through: 0->3->top
 * The total fee we have to pay will be (1+2).

 * Example 2:
 * Number of stairs (n): 4
 * Fee: {2,3,4,5}
 * Output: 5
 * Explanation: Starting from index '0', we can reach the last index through: 0->1->top
 * The total fee we have to pay will be (2+3).


 * Consider example 2, we need to reach 4th index ( which doesn't exist)

 * Fees:  2    3    4    5   
 * Index: 0 -> 1 -> 2 -> 3 -> 4(end)

 * Lets construct the tree:
 * 0 Min(3,4,5)+2 = 5
 * |->1 return Min(9,5,0) + 3 = 3
 *    |->2 return Min(0,5)+4 = 9
 *       |->3 return (0+5)
 *          |->4(end) - return 0
 *       |->4 - return 0
 *    |->3 return (0+5) = 5
 *       |->4(end) - return 0
 *    |->4(end) - return 0 
 * |->2 => min (5,0) + 4 => return 4
 *    |->3 return (0+5)
 *       |->4(end) - return 0 
 *    |->4(end) - return 0 
 * |->3 return (0+5) = 5
 *    |->4(end) - return 0 


 */
public class StaircaseWithFee {

	public static void main(String[] args) {
		StaircaseWithFee sc = new StaircaseWithFee();
	    int[] fee = {1,2,5,2,1,2};
	    System.out.println(sc.findJumpsWithMinFee(fee));
	    fee = new int[]{2,3,4,5};
	    System.out.println(sc.findJumpsWithMinFee(fee));
	}
	
	public int findJumpsWithMinFee(int fees[]) {
		return findJumpsWithMinFee(fees,0);
	}
	
	/*
	 * Since it is straight forward to convert this to top down DP, i am not going to write it.
	 * lets write the bottom up one.
	 */
	private int findJumpsWithMinFee(int[] fees, int index) {
		if(index>=fees.length)return 0;
		
		int fee1 = findJumpsWithMinFee(fees,index+1);
		int fee2 = findJumpsWithMinFee(fees,index+2);
		int fee3 = findJumpsWithMinFee(fees,index+3);
		
		int minFee = Math.min(Math.min(fee1, fee2), fee3);
		
		return minFee+fees[index];
	}
	
}
