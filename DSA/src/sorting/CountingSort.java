package sorting;

import java.util.Arrays;

public class CountingSort {

	public static void main(String[] args) {
		int a[] = new int[] {8,8,1,2,7,3,4,9,3,1,4,6,7,5,4,3,2,6};
		CountingSort sorter = new CountingSort();
		int [] sorted = sorter.sort(a);
		System.out.println(Arrays.toString(sorted));

	}
	
	/**
	 * Counting sort is based on modules k, where k is a small number say 10 or 20 or 100.
	 * Such that all the elements in the unsorted array are positive integers (including 0) less than k.
	 *
	 * @param a
	 * @return
	 */
	public int[] sort(int a[]) {
		int n = a.length;
		int k = 0;
		for(int x : a) { // find modulues k
			k = Math.max(k, x);
		}
		
		int [] aux = new int[k+1]; // initialize auxiliary array to k+1 elements to include 0
		
		for(int x : a) { // for every element x in the unsorted array, just increment the count in the unsorted array
			aux[x]++; // for example, if there are two 6s in the input, then aux[6] = 2. aux[0] = 0 means there are no zeroes in the input.
		}			  // The index of this aux array is the actual input number and value says how many times it is repeated.
		
		int sorted[] = new int[n];
		int j = 0;
		for(int i=0;i<=k;i++) { // iterate over aux
			int count = aux[i];  // get the count in aux[i] this tells how many times i has repeated.
			while(count-->0) {    // that many times, you can add index i to the array
				sorted[j++] = i;  // j is not of any relevance here. you can replace array with arraylist for simplicity.
			}
		}
		return sorted;
		
	}

}
