package dynamic.v2;

public class Knapsack {

 static int solveKnapsack(int[] profits, int[] weights, int capacity) {
    //TODO: Write - Your - Code
    int[][]dp = new int[2][capacity+1];
    
    for(int i=1;i<=capacity;i++)
    {
        dp[0][i] = profits[0];
        dp[1][i] = profits[0];
    }
    
    for(int i=1;i<profits.length;i++){
      for(int j=1;j<=capacity;j++){
    	  if(j>=weights[i]) {
	           int p1 = dp[(i-1)%2][j];
	           int p2 = profits[i] + dp[(i-1)%2][j-weights[i]];
	           dp[i%2][j] = Math.max(p1,p2);
    	  }else {
    		  dp[i%2][j] = dp[(i-1)%2][j];
    	  }
      }
    }
    int n = profits.length;
    return dp[(n-1)%2][capacity];
  }
 
 public static void main(String[] args) {
		System.out.println(Knapsack.solveKnapsack(new int[]{1,6,10,16}, new int[]{1,2,3,5}, 7));
	}
}

