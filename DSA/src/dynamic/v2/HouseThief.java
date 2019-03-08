package dynamic.v2;

/*
  * There are ‘n’ houses built in a line. A thief wants to steal maximum possible money from these houses. 
 * The only restriction the thief has is that he can’t steal from two consecutive houses, as that would alert the security system. 
 * How should the thief maximize his stealing?

 * Problem Statement
 * Given a number array representing the wealth of ‘n’ houses, determine the maximum amount of money the thief can steal without alerting the security system.
 */
public class HouseThief {

	public static void main(String[] args) {
		HouseThief thief = new HouseThief();
		System.out.println("Loot = "+thief.findMaxLootDP(new int[] {2, 5, 1, 3, 6, 2, 4}));
		System.out.println("Loot = "+thief.findMaxLootDP(new int[] {2, 10, 14, 8, 1}));
		
		System.out.println("Loot = "+thief.findMaxLootDP2(new int[] {2, 5, 1, 3, 6, 2, 4}));
		System.out.println("Loot = "+thief.findMaxLootDP2(new int[] {2, 10, 14, 8, 1}));
	}
	
	public int findMaxLoot(int [] wealth) {
		return findMaxLoot(wealth,0);
	}
	
	/*
	 * Use a simple 1d array for memoization, so i am not repeating the top down array.
	 */
	private int findMaxLoot(int[] wealth, int index) {
		if(index==wealth.length) return wealth[index];
		if(index>wealth.length)return 0;
		
		int select = wealth[index]+findMaxLoot(wealth,index+2);
		int dontSelect = findMaxLoot(wealth,index+1);
		return Math.max(select, dontSelect);
	}
	
	public int findMaxLootDP(int[] wealth) {
		int[] dp = new int[wealth.length+1];
		dp[0] = 0;
		dp[1] = wealth[0];
		
		for(int i=2;i<=wealth.length;i++) {
			dp[i] = Math.max(dp[i-2]+wealth[i-1], dp[i-1]);
		}
		
		return dp[wealth.length];
	}
	
	public int findMaxLootDP2(int[] wealth) {
		if(wealth.length == 0)return 0;
		
		if(wealth.length == 1)return 1;
		
		int n0 = wealth[0];
		int n1 = wealth[1];
		int max = 0;
		for(int i=2;i<wealth.length;i++) {
			max = Math.max(n0+wealth[i], n1);
			n0 = n1;
			n1 = max;
		}
		
		return max;
	}
	
}
