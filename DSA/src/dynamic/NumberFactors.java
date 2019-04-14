package dynamic;

import java.util.Arrays;

/*
 * Given a number ‘n’, implement a method to count how many possible ways there are to express ‘n’ as the sum of 1, 3, or 4.
 */
public class NumberFactors {

	public static void main(String[] args) {
		System.out.println(getFactorsSetsCountDP(new int[] {3, 5, 10}, 13));

	}
	
	/*
	 * I am considering a complex scenario here. {1,3,4} and 5 is pretty easy with base cases of 
	 * 1,2,3,4. But what if we are given {3,5,10}, 13. we need base cases of 0 to 13.
	 * So this creates base cases for that.
	 * 
	 * There is an alternative solution in WaysToScore.java
	 * 
	 * Since factors is usually a fewer elements, the time complexity of this method is not huge. 
	 * Technically it is |Factors| * |last number| . Since |Factors| < 4 we can consider it a constant.
	 */
	public static int getFactorsSets(int[] factors, int sum) {
		Arrays.parallelSort(factors);
		int last = factors[factors.length-1];
		int[] baseCases = new int[last+1];
		
		//since we are considering the number of sets, initialize to 1.
		//Another way to look at it is, if we write the tree, once the sum becomes 0, we have a set and return 1
		baseCases[0] = 1;
		
		//initialize to 1 all the numbers whose factors are present.
		//if 3,5,10 are in factors array, at least each of these have 1 factors each. 
		for(int j=0;j<factors.length;j++) {
			baseCases[factors[j]]++;
		}
		
		// for each number that is already present in base cases for example 3,5,10
		// if 3 has 1 factor, then 6 will also get that 1 factor
		// if 3 has 1 factor, and we are considering 5, 3+5 = 8 will have all the factors that 3 has
		// and vice versa, when we are considering 3 and 5 has some factors, those factors get added to 8
		
		//Also we are ignoring the last value. because baseCases it only till last value, so if you add last value it will be out of bounds
		for(int i=0;i<factors.length-1;i++) {
			int x = factors[i];
			for(int j=1;j<baseCases.length;j++) {
				if(baseCases[j] !=0 && (j+x) < baseCases.length) {			 		
					baseCases[j+x]+= baseCases[j];
				}
			}
		}
		
		return getFactorsSetsCount(factors, sum, baseCases);
	}
	/*
	 * This is standard fibonacci fare, except we are using array instead of hardcoded values
	 */
	private static int getFactorsSetsCount(int[] factors, int sum, int[] baseCases) {
		
		if(sum < baseCases.length) {
			return baseCases[sum];
		}
		
		int total = 0;
		for(int i=0;i<factors.length;i++) {
			int t = getFactorsSetsCount(factors, sum - factors[i], baseCases);
			total += t;
		}
		
		return total;
	}
	
	public static int getFactorsSetsCountDP(int[] factors, int sum) {
		int[] dp = new int[sum+1];
		
		dp[0] = 1;
		
		for(int i=1;i<=sum;i++) {
			for(int j=0;j<factors.length;j++) {
				if(factors[j] <= i) {
					dp[i] += dp[i-factors[j]];
				}
			}
		}
		
		return dp[sum];
	}

}
