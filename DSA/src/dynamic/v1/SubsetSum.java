package dynamic;

import java.util.Arrays;

/*
 * Date 09/23/2014
 * @author Tushar Roy
 *
 * Given an array of non negative numbers and a total, is there subset of numbers in this array which adds up
 * to given total. Another variation is given an array is it possible to split it up into 2 equal
 * sum partitions. Partition need not be equal sized. Just equal sum.
 *
 * Time complexity - O(input.size * total_sum)
 * Space complexity - O(input.size*total_sum)
 *
 * Youtube video - https://youtu.be/s6FhG--P7z0
 */
public class SubsetSum {

	public static void main(String[] args) {
		SubsetSum obj = new SubsetSum();
		int arr1[] = {2, 3, 7, 8, 5, 6 , 1, 9, 4, 12 , 11};
	    System.out.print(obj.isMatchingSubsetFound(arr1, 15));
	}
	/*
	 * Recursive Equation:
		Base Cases:
		SubsetSum(arrA, n, S)= false, if sum > 0 and n == 0 
		SubsetSum(arrA, n, S)= true, if sum == 0 (return empty set)
		
		Rest Cases
		SubsetSum(arrA, n, S) = SubsetSum(arrA, n-1, S)|| SubsetSum(arrA, n-1, S-arrA[n-1])
	 */
	public boolean isMatchingSubsetFound(int[] set, int sum) {
		boolean [][] table = new boolean[set.length+1][sum+1];
		
		//when sum is 0, we can return an empty set. So we set matching found to true.
		for(int i=0;i<=set.length;i++) {
			table[i][0] = true;
		}
		
		//if the set contains 0 hypothetically ( 0th index here represents set contains only 0), then except zero sum, which needs an empty set,
		// none of the sum values can be met. Hence initializing to false;
		for(int j=1;j<=sum;j++) {
			table[0][j] = false;
		}
		
		for(int i=1; i<=set.length;i++) {
			for(int j=1;j<=sum;j++) {
				//note that we start from i=1 and j=1. so when we consider set[] array , its always i-1 w.r.t to the table
				if(set[i-1] <= j) { //if the number is less than or equal to sum, we consider this set[i-1]
					//if we select set[i-1] , then we will be left with j-set[i-1]
					//so we check if the previous element is true for j-set[i-1] => table[i-1][j-set[i-1]]
					//if not we let this element skip and select the result for the previous element table[i-1][j]
					boolean selecting =  table[i-1][j-set[i-1]];
					boolean notSelecting = table[i-1][j];
					table[i][j] =notSelecting || selecting ; 
				}else {
					table[i][j] = table[i-1][j];
				}
			}
		}
		
		if(table[set.length][sum]) {
			int i=set.length, j=sum;
			while(i>0 && j>0) {
				if(table[i][j] == table[i-1][j]) {
					i--;
				}else {
					System.out.println(set[i-1]);
					j = j - set[i-1];
					i--;
				}
			}
		}
		
		return table[set.length][sum];
	}
	
	public boolean partition(int[] set) {
		int sum = 0;
		for(int s : set)
			sum+= s;
		
		if(sum%2 == 0)return false;
		
		return isMatchingSubsetFound(set, sum/2);
	}

}
