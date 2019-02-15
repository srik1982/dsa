package dynamic.v2;

/**
 * https://www.educative.io/collection/page/5668639101419520/5633779737559040/5712536552865792
 * 
 *
 */
public class TargetSum {

	public static void main(String[] args) {
		TargetSum ss = new TargetSum();
	    int[] num = {1, 1, 2, 3};
	    System.out.println(ss.getSetCountBotUpDP(num, 4));
	    num = new int[]{1, 2, 7, 1, 5};
	    System.out.println(ss.getSetCountBotUpDP(num, 9));

	}
	
	int getSetCountBF(int numbers[], int sum) {
		return getSetCountBF(numbers,0,sum);
	}

	private int getSetCountBF(int[] numbers, int index, int sum) {
		if(sum==0) return 1;
		
		if(index<0 || index>=numbers.length || sum<0) return 0;
		
		int count1 = 0;
		if(numbers[index] <= sum)
		 count1 = getSetCountBF(numbers,index+1,sum-numbers[index]);
		
		int count2 = getSetCountBF(numbers, index+1, sum);
		
		return count1+count2;
	}
	
	private int getSetCountTopDownDP(int []numbers, int sum) {
		Integer[][] dp = new Integer[numbers.length][sum+1];
		return getSetCountTopDownDP(numbers,0,dp,sum);
	}

	private int getSetCountTopDownDP(int[] numbers, int index, Integer[][] dp, int sum) {
		if(sum==0) return 1;
		
		if(index<0 || index>=numbers.length || sum<0) return 0;
		
		int count1 = 0, count2=0;
		if(dp[index][sum] == null) {
		
		if(numbers[index] <= sum)
			count1 = getSetCountBF(numbers,index+1,sum-numbers[index]);
			
			count2 = getSetCountBF(numbers, index+1, sum);
			dp[index][sum] = count1+count2;
			
		}
	
		
		return dp[index][sum];
	}
	
	private int getSetCountBotUpDP(int []numbers, int sum) {
		int[][] dp = new int[numbers.length][sum+1];
		
		for(int i=0;i<numbers.length;i++)
			dp[i][0] = 1;
		
		for(int i=1;i<=sum;i++)
			dp[0][i] = numbers[0] == i ? 1 : 0;
		
		for(int i=1;i<numbers.length;i++) {
			for(int j=1;j<=sum;j++) {
				int count1=0,count2=0;
				if(numbers[i]<=j)
					count1 = dp[i-1][j-numbers[i]];
				
				count2 = dp[i-1][j];
				
				dp[i][j] = count1+count2;
			}
		}
		
		return dp[numbers.length-1][sum];
	}


}
