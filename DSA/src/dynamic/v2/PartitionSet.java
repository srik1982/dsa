package dynamic.v2;

public class PartitionSet {

	public static void main(String[] args) {
		 PartitionSet ps = new PartitionSet();
	    int[] num = {1, 2, 3, 4};
	    System.out.println(ps.canPartitionDp(num));
	    num = new int[]{1, 1, 3, 4, 7};
	    System.out.println(ps.canPartitionDp(num));
	    num = new int[]{2, 3, 4, 6};
	    System.out.println(ps.canPartitionDp(num));

	}
	
	boolean canPartition(int[] numbers) {
		int sum = 0;
		
		for(int n : numbers) {
			sum += n;
		}
		
		if(sum%2 != 0)return false;
		
		return canPartition(numbers, sum/2, 0);
	}

	private boolean canPartition(int[] numbers, int  sum, int i) {
		
		if(sum==0) return true;
		
		if(i<0 || i>=numbers.length || sum<0) return false;
		
		boolean retVal = false;
		if(numbers[i] <= sum) {
			retVal = canPartition(numbers, sum - numbers[i], i+1);
		}
		if(!retVal) {
			retVal = canPartition(numbers,sum,i+1);
		}
		
		return retVal;
	}
	
	boolean canPartitionDp(int[] numbers) {
		int sum = 0;
		
		for(int n : numbers) {
			sum += n;
		}
		
		if(sum%2 != 0)return false;
		
		int[][] dp = new int[numbers.length][sum/2+1];
		
		for(int i=0;i<numbers.length;i++)
			for(int j=0;j<(sum/2+1);j++)
				dp[i][j] = -1;
		
		return canPartitionTopDownDP(numbers, sum/2, 0,dp);
	}

	private boolean canPartitionTopDownDP(int[] numbers, int sum, int i, int[][] dp) {
		if(sum==0) return true;
		
		if(i<0 || i>=numbers.length || sum<0) return false;
		
		if(dp[i][sum] != -1) {
			return dp[i][sum] == 1 ? true : false;
		}
		
		boolean retVal = false;
		if(numbers[i] <= sum) {
			retVal = canPartition(numbers, sum - numbers[i], i+1);
		}
		if(!retVal) {
			retVal = canPartition(numbers,sum,i+1);
		}
		
		if(retVal)
			dp[i][sum] = 1;
		else
			dp[i][sum] = 0;
		
		return retVal;
	}


}
