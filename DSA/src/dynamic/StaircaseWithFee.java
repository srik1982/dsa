package dynamic;

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
	    System.out.println(sc.findJumpsWithMinFeeDP(fee));
	    fee = new int[]{2,3,4,5};
	    System.out.println(sc.findJumpsWithMinFeeDP(fee));
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
	
	public int findJumpsWithMinFeeDP(int[] fees) {
		int[] minFees = new int[fees.length+1];
		
		minFees[0] = 0;// if there are no steps, we dont have to pay any fee
		minFees[1] = minFees[2] = minFees[3] = fees[0]; //because we can take upto 3 steps with fees paid in fees[0];
		
		for(int i=4;i<=fees.length;i++) {
			//jump from i-3 to i using fees[i-3]. because we can take up to 3 steps.
			//for i=4, we have already reached index 1 with fees[0] true, but from there to index =4, we need fees[1]
			int x1 = minFees[i-3]+fees[i-3];
			//similarly from i-2 to i, we can jump 2 steps with fees[i-2];
			int x2 = minFees[i-2]+fees[i-2];
			//and of course i-1 to i, 1 step jump
			int x3 = minFees[i-1]+fees[i-1];
			minFees[i] = Math.min(Math.min(x1,x2), x3);
		}
		return minFees[fees.length]; 
	}
}
