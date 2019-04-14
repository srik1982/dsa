package dynamic.v2;

import java.util.HashMap;
import java.util.Map;

public class MaxSumIncreasingSubsequence {

	public static void main(String[] args) {
		MaxSumIncreasingSubsequence msis = new MaxSumIncreasingSubsequence();
	    int[] nums = {4,1,2,6,10,1,12};
	    System.out.println(msis.findMSIS2(nums));
	    nums = new int[]{-4,10,3,7,15};
	    System.out.println(msis.findMSIS2(nums));
	}
	
	public int findMSIS(int[] seq) {
		return findMaxSum(seq,-1,0,0, new HashMap<String,Integer>());
	}

	private int findMaxSum(int[] seq, int prev, int curr, int sum, Map<String,Integer> dp) {
		if(curr == seq.length) return sum;
		String key = prev+"|"+curr+"|"+sum;
		
		if(dp.containsKey(key)) {
			return dp.get(key);
		}
		
		int s1=0,s2=0;
		if(prev==-1 || seq[prev]<= seq[curr]) {
			s1 = findMaxSum(seq, curr, curr+1, sum + seq[curr],dp);
		}
		s2 = findMaxSum(seq,prev,curr+1,sum,dp);
		
		dp.put(key, Math.max(s1, s2));
		return dp.get(key);
	}
	
	public int findMSIS2(int[] seq) {
		return findMaxSum2(seq,-1,0,new HashMap<String,Integer>());
	}

	private int findMaxSum2(int[] seq, int prev, int curr, Map<String,Integer> dp) {
		if(curr == seq.length) return 0;
		String key = prev+"|"+curr;
		
		if(dp.containsKey(key)) {
			return dp.get(key);
		}
		
		int s1=0,s2=0;
		if(prev==-1 || seq[prev]<= seq[curr]) {
			s1 = seq[curr]+findMaxSum2(seq, curr, curr+1,dp);
		}
		s2 = findMaxSum2(seq,prev,curr+1,dp);
		
		dp.put(key, Math.max(s1, s2));
		return dp.get(key);
	}
	
	public int findMSISDP(int[] seq) {
		int[] dp = seq.clone();
		int max = 0;
		
		for(int i=1;i<dp.length;i++) {
			for(int j=0;j<i;j++) {
				if(seq[j] < seq[i]) {
					dp[i] = Math.max(dp[i], dp[j]+seq[i]);
					max = Math.max(max, dp[i]);
				}
			}
		}
		return max;
	}

}
