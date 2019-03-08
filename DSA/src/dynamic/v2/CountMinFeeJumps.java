package dynamic.v2;

import java.util.Arrays;

/*
 * At every step you have the option to take 1,2 or 3 steps. And there is a fee/price to pay if you land at a particular step. Find minimum fees to pay.
 */
public class CountMinFeeJumps {

	public static void main(String[] args) {
		int[] fee = {1,2,5,2,1,2};
		System.out.println("Min fee for "+Arrays.toString(fee)+" = "+getMinStepsWithDP(fee));
		fee = new int[]{2,3,4,5};
		System.out.println("Min fee for "+Arrays.toString(fee)+" = "+getMinStepsWithDP(fee));
	}
	
	public static int getMinStepsFee(int[] fee) {
		return getMinStepsFee(fee,0);
	}

	/*
	 * 3 power n
	 */
	private static int getMinStepsFee(int[] fee, int i) {
		if(i == fee.length - 1)return 0;
		
		if(i > fee.length-1) return Integer.MAX_VALUE;
		
		int f1,  f2, f3;
		f1=f2=f3= Integer.MAX_VALUE;
		
		f1 = getMinStepsFee(fee, i+1);
		f2 = getMinStepsFee(fee, i+2);
		f3 = getMinStepsFee(fee, i+3);
		
		return Math.min(Math.min(f1, f2), f3) + fee[i];
	}
	
	private static int getMinStepsWithMemo(int[] fee) {
		int[] dp = new int[fee.length];
		for(int i=1;i<dp.length;i++)
			dp[i] = Integer.MAX_VALUE;
		
		return getMinStepsWithMemo(fee,0,dp);
	}
	
	private static int getMinStepsWithMemo(int[] fee, int i, int[] dp) {
		if(dp[i] != Integer.MAX_VALUE) {
			return dp[i];
		}
		if(i == fee.length - 1)return 0;
		
		if(i > fee.length-1) return Integer.MAX_VALUE;
		
		int f1,  f2, f3;
		f1=f2=f3= Integer.MAX_VALUE;
		
		f1 = getMinStepsFee(fee, i+1);
		f2 = getMinStepsFee(fee, i+2);
		f3 = getMinStepsFee(fee, i+3);
		
		dp[i] = Math.min(Math.min(f1, f2), f3) + fee[i];
		return dp[i];
	}
	
	/*
	 * n * 3 => O(n)
	 */
	public static int getMinStepsWithDP(int[] fee) {
		int[] dp = new int[fee.length];
		for(int i=1;i<dp.length;i++)
			dp[i] = Integer.MAX_VALUE;
		
		for(int i=0;i<dp.length-1;i++) {
			for(int j=1;j<4;j++) {
				if(i+j < dp.length)
				dp[i+j] = Math.min(dp[i+j], dp[i]+fee[i]); 
			}
		}
		return dp[dp.length-1];
	}

}
