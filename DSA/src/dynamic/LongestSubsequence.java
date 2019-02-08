package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestSubsequence {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(LongestSubsequence.findLCS("abcadce".toCharArray(), "abdef".toCharArray())));
		
//		System.out.println(LongestSubsequence.findLCS2("abcadce".toCharArray(), "abdef".toCharArray()));
	}
	
	public static char[] findLCS(char[] input1, char[] input2) {
		int[][]table = new int[input2.length+1][input1.length+1];
		
		for(int i=0;i<=input2.length;i++) {
			for(int j=0;j<=input1.length;j++) {
				if(i==0 || j==0) {
					table[i][j] = 0;
					continue;
				}
				if(input2[i-1] == input1[j-1]) {
					table[i][j] = 1 + table[i-1][j-1];
				}else {
					table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
				}
			}
		}
		
		int count = table[input2.length][input1.length];
		System.out.println("Length of the longest common subsequence : "+count);
		
		int row = input2.length, col = input1.length;
		char[] retVal = new char[count];
		int i= count -1;
		
		while(table[row][col] != 0) {
			int val = table[row][col];
			int maxOfLeftAndTop = Math.max(table[row-1][col], table[row][col-1]);
			
			if(val == maxOfLeftAndTop) {
				if(val == table[row-1][col])
					row--;
				else
					col--;
			}else {
				retVal[i--] = input1[col-1];
				row--;
				col--;
			}
		}
		
		return retVal;
		
	}
//	
//	public static List<Character> findLCS2(char[] input1, char[] input2) {
//		int[][]table = new int[input2.length+1][input1.length+1];
//		List<Character> retVal = new ArrayList<Character>();
//		for(int i=0;i<=input2.length;i++) {
//			for(int j=0;j<=input1.length;j++) {
//				if(i==0 || j==0) {
//					table[i][j] = 0;
//					continue;
//				}
//				if(input2[i-1] == input1[j-1]) {
//					table[i][j] = 1 + table[i-1][j-1];
//					retVal.add(input1[j-1]);
//				}else {
//					table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
//				}
//			}
//		}
//		
//		return retVal;
//		
//	}
}
