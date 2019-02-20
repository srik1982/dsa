package dynamic.v2;

/*
 * Fibonacci numbers are a series of numbers in which each number is the sum of the two preceding numbers. First few Fibonacci numbers are: 0, 1, 1, 2, 3, 5, 8, …

	Mathematically we can define the Fibonacci numbers as:

    Fib(n) = Fib(n-1) + Fib(n-2), for n > 1

    Given that: Fib(0) = 0, and Fib(1) = 1
 */
public class Fibonacci {

	public static void main(String[] args) {
		Fibonacci fib = new Fibonacci();
	    System.out.println("5th Fibonacci is ---> " + fib.CalculateFibonacciOptimal(5));
	    System.out.println("6th Fibonacci is ---> " + fib.CalculateFibonacciOptimal(6));
	    System.out.println("7th Fibonacci is ---> " + fib.CalculateFibonacciOptimal(7));
	}

	public int CalculateFibonacciBF(int n) {
		if (n < 2)
			return n;
		return CalculateFibonacciBF(n - 1) + CalculateFibonacciBF(n - 2);
	}

	public int CalculateFibonacciTopDown(int n) {
		int dp[] = new int[n + 1];
		return CalculateFibonacciTopDown(dp, n);
	}

	public int CalculateFibonacciTopDown(int[] dp, int n) {
		if (n < 2)
			return n;
		if (dp[n] == 0)
			dp[n] = CalculateFibonacciTopDown(dp, n - 1) + CalculateFibonacciTopDown(dp, n - 2);
		return dp[n];
	}
	
	public int CalculateFibonacciBotUp(int n) {
	    int dp[] = new int[n + 1];
	    dp[0] = 0;
	    dp[1] = 1;
	    for (int i = 2; i <= n; i++)
	      dp[i] = dp[i - 1] + dp[i - 2];
	    return dp[n];
	}
	
	public int CalculateFibonacciOptimal(int n) {
		int n0 = 0, n1 = 1, res = 0;
		for(int i=2;i<=n;i++) {
			res = n0 + n1;
			n0 = n1;
			n1 = res;
			
		}
		return res;
	}
}
