package dynamic.v1;

public class CoinCountingMinCount {

	public static void main(String[] args) {
		int coins[] = {7,2,3,6};
		System.out.println(getMinCoinsCount(coins, 13));
	}
	
	static int getMinCoinsCount(int[] coins, int sum) {
		int[] T = new int[sum+1];
		int[] R = new int[sum+1];
		
		for(int i=1;i<=sum;i++) {
			T[i] = Integer.MAX_VALUE - 1;
			R[i] = -1;
		}
		
		for(int i=0;i<coins.length;i++) {
			for(int j=1;j<=sum;j++) {
				if(j>=coins[i]) {
					if(T[j-coins[i]]+1 < T[j]) {
						T[j] = 1+ T[j-coins[i]];
						R[j] = i;
					}
				}
			}
		}
		
		printCoins(R,coins);
		
		return T[sum];
	}

	private static void printCoins(int[] r, int[] coins) {
		if(r[r.length-1] == -1) {
			System.out.println("No Solution");
		}
		
		System.out.println("printing denominations: ");
		int start = r.length-1;
		while(start>0) {
			int denomination = coins[r[start]];
			System.out.print(denomination+" ");
			start -= denomination;
		}
		System.out.println();
	}

}

