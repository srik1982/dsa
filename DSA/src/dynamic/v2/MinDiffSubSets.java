package dynamic.v2;

public class MinDiffSubSets {

	public static void main(String[] args) {
		MinDiffSubSets ps = new MinDiffSubSets();
	    int[] num = {1, 2, 3, 9};
	    System.out.println(ps.bottomUpDp(num));
	    num = new int[]{1, 2, 7, 1, 5};
	    System.out.println(ps.bottomUpDp(num));
	    num = new int[]{1, 3, 100, 4};
	    System.out.println(ps.bottomUpDp(num));
	}
	
	int bruteForce(int []numbers) {
		return bruteForce(numbers,0,0,0);
	}

	private int bruteForce(int[] numbers, int index, int sum1, int sum2) {
		
		if(index == numbers.length-1) {
			return Math.abs(sum2-sum1);
		}
		
		int diff1 = bruteForce(numbers,index+1, sum1+numbers[index],sum2);
		int diff2 = bruteForce(numbers,index+1, sum1,sum2+numbers[index]);
		
		return Math.min(diff1, diff2);
	}
	
	int topDownDp(int[] numbers) {
		int sum=0;
		for(int n : numbers) {
			sum += n;
		}
		Integer [][]dp = new Integer[numbers.length][sum+1];
		return topDownDp(dp,numbers,0,0,0);
	}

	private int topDownDp(Integer[][] dp, int[] numbers, int index, int sum1, int sum2) {
		
		if(index == numbers.length) {
			return Math.abs(sum1-sum2);
		}
		
		if(dp[index][sum2] == null) {
			int diff1 = topDownDp(dp,numbers,index+1, sum1+numbers[index],sum2);
			int diff2 = topDownDp(dp,numbers,index+1, sum1,sum2+numbers[index]);
			
			dp[index][sum2] = Math.min(diff1, diff2);
		}
		return dp[index][sum2];
	}
	
	private int bottomUpDp(int[] numbers) {
		int sum=0;
		for(int n : numbers) {
			sum += n;
		}
		
		int columns = sum/2+1;
		
		boolean[][] dp = new boolean[numbers.length][columns];
		
		// populate the sum=0 columns, as we can always form '0' sum with an empty set
	    for(int i=0; i < numbers.length; i++)
	      dp[i][0] = true;
	    
		for (int i = 1; i < columns; i++)
			dp[0][i] = numbers[0] == i;
		
	    
	    for(int i=1;i<numbers.length;i++) {
	    	for(int j=1;j<columns;j++) {
	    		if(numbers[i] <= j) {
	    			dp[i][j] = dp[i-1][j-numbers[i]];
	    		}else {
	    			dp[i][j] = dp[i-1][j];
	    		}
	    	}
	    }
	    
	    int sum1 = 0;
	    for(int i=columns-1;i>=0;i--) {
	    	if(dp[numbers.length-1][i]) {
	    		sum1 = i;
	    		break;
	    	}
	    }
	    
	    int sum2 = sum - sum1;
	    
	    return Math.abs(sum1-sum2);
	}

}
