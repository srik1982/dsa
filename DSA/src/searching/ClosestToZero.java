package searching;

import sorting.MergeSort;

public class ClosestToZero {

	public static void main(String[] args) {
		int a[] = {-17,4,8,1,3,2,5,7,6,9,0,-2,-3};
		MergeSort mergeSort = new MergeSort();
		//O(nlogn)
		mergeSort.mergeSort(a, 0, a.length-1);
		
		int positiveSum = Integer.MAX_VALUE, negativeSum = Integer.MIN_VALUE; 
		
		for(int i=0,j=a.length-1;i<j;) {
			int sum = a[i]+a[j];
			if(sum > 0) {
				if(sum < positiveSum) {
					positiveSum = sum;
				}
				j--;
			}else if(sum < 0){
				if(sum > negativeSum) {
					negativeSum = sum;
				}
				i++;
			}else {
				System.out.println("Sum = 0 . "+a[i]+" , "+a[j]);
				i++;
			}
		}
		
		System.out.println("positiveSum = "+positiveSum);
		System.out.println("negativeSum = "+negativeSum);

	}

}
