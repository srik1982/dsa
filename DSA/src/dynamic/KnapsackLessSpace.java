package dynamic;

public class KnapsackLessSpace {
	public static void main(String args[]) {
		System.out.println(solveKnapsack(new int[]{1,6,10,16}, new int[]{1,2,3,5}, 7));
	}
	
	static int solveKnapsack(int[] profits, int[] weights, int capacity) {
	    //TODO: Write - Your - Code
	    int[] dp = new int[capacity+1];
	    
	    for(int i=1;i<=capacity;i++)
	      dp[i] = profits[0];
	    
	    for(int i=1;i<weights.length;i++){
	      for(int j=capacity;j>0;j--){
	    	  System.out.print(i+" , "+j);
	        if(j >= weights[i]){
	          int p1 = dp[j];
	          int p2 = profits[i] + dp[j-weights[i]];
	          dp[j] = Math.max(p1,p2);
	        }
	      }
	    }
	    
	    return dp[capacity];
	  }
}

