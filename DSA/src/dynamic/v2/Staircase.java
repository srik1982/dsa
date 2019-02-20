package dynamic.v2;

/*
 *  Given a stair with ‘n’ steps, implement a method to count how many possible ways are there to reach the top of the staircase, given that, at every step you can either take 1 step, 2 steps, or 3 steps.

	Example 1:
	
	Number of stairs (n) : 3
	
	Number of ways = 4
	
	Explanation: Following are the four ways we can climb : {1,1,1}, {1,2}, {2,1}, {3} 
	
	Example 2:
	
	Number of stairs (n) : 4
	
	Number of ways = 7
	
	Explanation: Following are the seven ways we can climb : {1,1,1,1}, {1,1,2}, {1,2,1}, {2,1,1}, 
	
	{2,2}, {1,3}, {3,1}
	
	Solution: This follows the fibonacci pattern.
	For n = 0, ways = 1 -> {}
	for n=1, ways = 1 -> {1}
	for n=2, ways = 2 -> {1,1}, {2}
	for n=3, ways = 4 -> {1,1,1}, {1,2}, {2,1}, {3}
	By observation, T(n) = T(n-1) + T(n-2) + T(n-3) for all n>=3
	and T(0) = 1, T(1) = 1, T(2) = 2.
	
	With this we should start implementing a fibonacci code.
 */
public class Staircase {

	public static void main(String[] args) {
		Staircase o = new Staircase();
		System.out.println(o.getWaysCountOptimal(3));
		System.out.println(o.getWaysCountOptimal(4));
		System.out.println(o.getWaysCountOptimal(5));

	}
	
	public int getWaysCountBF(int n) {
		if(n==0) return 1;
		if(n==1) return 1;
		if(n==2) return 2;
		
		return getWaysCountBF(n-1)+getWaysCountBF(n-2)+getWaysCountBF(n-3); 
	}
	
	public int getWaysCountTopDown(int n) {
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		return getWaysCountTopDown(n,dp);
	}

	private int getWaysCountTopDown(int n, int[] dp) {
		if(dp[n] !=0) {
			return dp[n];
		}
		
		int x = getWaysCountTopDown(n-1,dp) + getWaysCountTopDown(n-2,dp) + getWaysCountTopDown(n-3,dp);
		dp[n] = x;
		
		return x;
	}
	
	/*
	 * You only need last 3 counts. so use 3 variables instead
	 */
	private int getWaysCountOptimal(int n) {
		int n0 = 1, n1 = 1, n2 = 2;
		
		if(n<3) {
			switch(n) {
			case 0 : return n0;
			case 1: return n1;
			case 2: return n2;
			}
		}
		int result = 0;
		for(int i=3;i<=n;i++) {
			result = n0 + n1 + n2;
			n0 = n1;
			n1 = n2;
			n2 = result;
		}
		
		return result;
	}

}
