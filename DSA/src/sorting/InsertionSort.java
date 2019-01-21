package sorting;

import java.util.Arrays;
import java.util.List;

public class InsertionSort {

	public static void main(String[] args) {
		
		int a[] = new int[] {6,8,1,3,2,5,4,9,7,6};
		a = InsertionSort.sort(a, a.length);
		System.out.println(Arrays.toString(a));

	}
	/**
	 * |<-- sorted (0,j) -->| <-- unsorted ( i, n-1) -->| 
	 * @param a
	 * @param n
	 * @return
	 */
	public static int[] sort(int a[], int n) {
		for(int i=1;i<n;i++) {
			int key = a[i]; // all elements from 0 to i-1 are sorted
			int j = i-1;    // we consider ith element, i.e., the first unsorted element and compare with the sorted part
			while(j>=0) {
				if(a[j] < key) { // when we encounter first element which is less than the key from unsorted part, we stop
					break;       // Because all elements to the left of this a[j] are lesser than a[j] , so stop comparison
				}else {            // if key < a[j], then we keep moving a[j] to a[j+1] until we find a a[j]<key
					a[j+1] = a[j];
					j--;
				}
			}
			a[j+1] = key;
		}
		return a;
	}
	
	public static void sort(List<Integer> a) {
		int count = 0;
		for(int i=1;i<a.size();i++) {
			int key = a.get(i); // all elements from 0 to i-1 are sorted
			int j = i-1;    // we consider ith element, i.e., the first unsorted element and compare with the sorted part
			while(j>=0) {
				count++;
				if(a.get(j) < key) { // when we encounter first element which is less than the key from unsorted part, we stop
					break;       // Because all elements to the left of this a[j] are lesser than a[j] , so stop comparison
				}else {            // if key < a[j], then we keep moving a[j] to a[j+1] until we find a a[j]<key
					a.set(j+1, a.get(j));
					j--;
				}
			}
			a.set(j+1,key);
		}
		System.out.println("Sort loop ran "+count+" times");
	}

}
