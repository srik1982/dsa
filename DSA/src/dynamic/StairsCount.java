package dynamic;

/*
 * Given a stair with ‘n’ steps, implement a method to count how many possible ways are there to reach the top of the staircase, given that, at every step you can either take 1 step, 2 steps, or 3 steps.

	Example 1:
	
	Number of stairs (n) : 3
	
	Number of ways = 4
	
	Explanation: Following are the four ways we can climb : {1,1,1}, {1,2}, {2,1}, {3} 
	
	Example 2:
	
	Number of stairs (n) : 4
	
	Number of ways = 7
	
	Explanation: Following are the seven ways we can climb : {1,1,1,1}, {1,1,2}, {1,2,1}, {2,1,1}, 
	
	{2,2}, {1,3}, {3,1}
	
	In this particular class, I will try to solve the problem using counting the subsets approach 
	Cannot solve with this kind of approach. I will still keep this class to remind myself not to use this approach.
 */
public class StairsCount {

	public static void main(String[] args) {
		StairsCount obj = new StairsCount();
		int ct = obj.getMaxWaysBF(new int[]{1,2,3}, 5);
		System.out.println("Number of ways : "+ct);

	}
	public int getMaxWaysBF(int [] steps,int total) {
		return getMaxWaysBF(steps, 0, total);
	}
	public int getMaxWaysBF(int [] steps, int index, int total) {
		//since we are talking about sets here. we can return a empty set when total is 0 and it would still count as 1
		if(total==0)return 0;
		
		if(total<0 || index== steps.length) return Integer.MIN_VALUE;
		
		int sum1 = Integer.MIN_VALUE, sum2 = Integer.MIN_VALUE;
		//select
		if(steps[index] <= total) {
			sum1 = getMaxWaysBF(steps, index, total-steps[index]);
			if(sum1 != Integer.MIN_VALUE)
			{
				sum1 += 1;
				System.out.println("Selecting "+steps[index]);
			}
			
		}
		//don't select
		sum2 = getMaxWaysBF(steps,index+1,total);
		
		if(sum1<0 && sum2<0)
			return Integer.MIN_VALUE;
		else if(sum1>0 && sum2<0)
			return sum1;
		else if(sum1<0 && sum2>0)
			return sum2;
		else
			return sum1+sum2;
	}

}
