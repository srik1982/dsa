package dynamic;

/*
 * We are given a ribbon of length ‘n’ and a set of possible ribbon lengths. Now we need to cut the ribbon into the maximum number of pieces that comply with the above-mentioned possible lengths. Write a method that will return the count of pieces.

	Example 1:	
	n: 5	
	Ribbon Lengths: {2,3,5}	
	Output: 2	
	Explanation: Ribbon pieces will be {2,3}.
	
	Example 2:	
	n: 7	
	Ribbon Lengths: {2,3}	
	Output: 3	
	Explanation: Ribbon pieces will be {2,2,3}.
	
	Example 3:	
	n: 13	
	Ribbon Lengths: {3,5,7}	
	Output: 3
	Explanation: Ribbon pieces will be {3,3,7}.
 */
public class MaxRibbonsCut {

	public static void main(String[] args) {
		MaxRibbonsCut cr = new MaxRibbonsCut();
	    int[] ribbonLengths = {2,3,5};
	    System.out.println(cr.getMaxRibbonsCountBotUp(ribbonLengths, 5));
	    ribbonLengths = new int[]{2,3};
	    System.out.println(cr.getMaxRibbonsCountBotUp(ribbonLengths, 7));
	    ribbonLengths = new int[]{3,5,7};
	    System.out.println(cr.getMaxRibbonsCountBotUp(ribbonLengths, 13));
	    ribbonLengths = new int[]{3,5};
	    System.out.println(cr.getMaxRibbonsCountBotUp(ribbonLengths, 7));

	}
	
	public int getMaxRibbonsCountBF(int[] lengths, int maxLength) {
		return getMaxRibbonsCountBF(lengths,0,maxLength);
	}

	private int getMaxRibbonsCountBF(int[] lengths, int index, int maxLength) {
		if(maxLength==0)return 0;
		
		if(index==lengths.length || maxLength <0) return Integer.MIN_VALUE;
		
		int sum1 = Integer.MIN_VALUE, sum2 = Integer.MIN_VALUE;
		//select the element
		if(lengths[index]<= maxLength) {
			sum1 = getMaxRibbonsCountBF(lengths, index, maxLength-lengths[index]);
			if(sum1 != Integer.MIN_VALUE) { // meaning the element is selected
				sum1 += 1;
			}
		}
		//don't select this element
		sum2 = getMaxRibbonsCountBF(lengths, index+1, maxLength);
		return Math.max(sum1, sum2);
	}
	
	public int getMaxRibbonsCountTopDown(int[] lengths, int maxLength) {
		Integer[][] dp = new Integer[lengths.length][maxLength+1];
		return getMaxRibbonsCountTopDown(lengths,0,maxLength,dp);
	}

	private int getMaxRibbonsCountTopDown(int[] lengths, int index, int maxLength, Integer[][] dp) {
		if(maxLength==0)return 0;
		
		if(index==lengths.length || maxLength <0) return Integer.MIN_VALUE;
		
		if(dp[index][maxLength]!=null) return dp[index][maxLength];
		
		int sum1 = Integer.MIN_VALUE, sum2 = Integer.MIN_VALUE;
		//select the element
		if(lengths[index]<= maxLength) {
			sum1 = getMaxRibbonsCountBF(lengths, index, maxLength-lengths[index]);
			if(sum1 != Integer.MIN_VALUE) { // meaning the element is selected
				sum1 += 1;
			}
		}
		//don't select this element
		sum2 = getMaxRibbonsCountBF(lengths, index+1, maxLength);
		dp[index][maxLength] = Math.max(sum1, sum2);
		return dp[index][maxLength];
	}
	
	public int getMaxRibbonsCountBotUp(int[] lengths, int maxLength) {
		int[][] dp = new int[lengths.length][maxLength+1];
		
		//base conditions. if the max length is 0, then we can't provide any ribbon. so initialize with 0
		// of course 0 is the initial value for int. so no need to do explicitly
		for(int i=0;i<lengths.length;i++)
			dp[i][0] = 0;
		
		for(int i=0;i<lengths.length;i++) {
			for(int j=1;j<=maxLength;j++) {
				int sum1 = Integer.MIN_VALUE, sum2 = Integer.MIN_VALUE;
				
				if(lengths[i] <= j) {
					sum1 = dp[i][j-lengths[i]];
					if(sum1 != Integer.MIN_VALUE)
						sum1 += 1; // plus 1 to indicate this element is selected
				}
				
				if(i>0)
					sum2 = dp[i-1][j];
				
				dp[i][j] = Math.max(sum1, sum2);
			}
		}
		
		return dp[lengths.length-1][maxLength];
	}

}
