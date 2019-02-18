package dynamic.v2;

/*
 *  Given a set of positive numbers and a target sum ‘S’. Each number should be assigned either a ‘+’ or ‘-’ sign. We need to find out total ways to assign symbols to make the sum of numbers equal to target ‘S’.
	Example 1:
	
	Input: {1, 1, 2, 3}, S=1
	
	Output: 3
	
	Explanation: The given set has '3' ways to make a sum of '1': {+1-1-2+3} & {-1+1-2+3} & {+1+1+2-3}
	
	Now, let’s say ‘Sum(s1)’ denotes the total sum of set ‘s1’, and ‘Sum(s2)’ denotes the total sum of set ‘s2’. So the required equation is:

    Sum(s1) - Sum(s2) = S

	This equation can be reduced to the subset sum problem. Let’s assume that ‘Sum(num)’ denotes the total sum of all the numbers, therefore:

    Sum(s1) + Sum(s2) = Sum(num)

	Let’s add the above tow equations:

    => Sum(s1) - Sum(s2) + Sum(s1) + Sum(s2) = S + Sum(num)

    => 2 * Sum(s1) =  S + Sum(num)

    => Sum(s1) = (S + Sum(num)) / 2

	This essentially converts our problem to: “Find count of subsets of the given numbers whose sum is equal to”,

    => (S + Sum(num)) / 2
    
	
	https://www.educative.io/collection/page/5668639101419520/5633779737559040/5679413765079040
 */
public class TargetSum {

	public static void main(String[] args) {
		TargetSum ts = new TargetSum();
	    int[] num = {1, 1, 2, 3};
	    System.out.println(ts.findTargetSubsets(num, 1));
	    num = new int[]{1, 2, 7, 1};
	    System.out.println(ts.findTargetSubsets(num, 9));
	}
	
	public int findTargetSubsets(int[] num, int s) {
	    int totalSum = 0;
	    for (int n : num)
	        totalSum += n;

	    // if 's + totalSum' is odd, we can't find a subset with sum equal to '(s + totalSum) / 2'
	    if(totalSum < s || (s + totalSum) % 2 == 1)
	        return 0;

	    return getSetCountBotUpDP(num, (s + totalSum) / 2);
	  }
	
	public int getSetCountBotUpDP(int []numbers, int sum) {
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
