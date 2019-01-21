package searching;

import sorting.MergeSort;

public class FirstPositiveInteger {

	public static void main(String[] args) {
		int a[] = new int[1000];
		for(int i=-500,j=0;i<500;i++) {
			a[j++] = i;
		}
		MergeSort mergeSort = new MergeSort();
		//O(nlogn)
		mergeSort.mergeSort(a, 0, a.length-1);
		FirstPositiveInteger obj = new FirstPositiveInteger();
		System.out.println(obj.findFirstPositiveInteger(a));
		
	}
	
	public int findFirstPositiveInteger(int[] a) {
		return modifiedBinarySearch(a, 0, a.length-1);
	}
	/**
	 * Use a modified binary search.
	 * We are not searching for a particular value, but for a interval or index.
	 * 1. If we can find 0, that is the best case, the next number is the positive integer ( in a sorted array)
	 * 2. If not, identify the smallest interval before the low < high becomes false. This is typically small value, a constant almost.
	 * within this small interval find out the first positive integer in a linear fashion.
	 * @param a
	 * @param low
	 * @param high
	 * @return
	 */
	public int modifiedBinarySearch(int []a, int low, int high) {
		int intStart = 0;
		int intEnd = 0;
		int found = 0;
		int positive = Integer.MAX_VALUE, negative = Integer.MIN_VALUE;
		while(low<high) {
			int mid = low + (high-low)/2;
			if(a[mid] > 0) {
				if(a[mid] < positive) {
					positive = a[mid];
					intEnd = mid;
				}
				high = mid - 1;
			}else if(a[mid] < 0) {
				if(a[mid] > negative) {
					negative = a[mid];
					intStart = mid;
				}
				low = mid+1;
			}else {
				found = mid+1;
				break;
			}
		}
		if(found==0) {
			for(int i=intStart;i<=intEnd;i++) {
				if(a[i]>0) {
					found = i;
					break;
				}
			}
		}
		return found;
	}

}
