package sorting;

import java.util.Arrays;

public class QuickSort {
	
	public static void main(String args[]) {
		int a[] = new int[] {6,-8,1,3,-2,5,4,9,77,6};
		QuickSort sorter = new QuickSort();
		sorter.sort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
	}
	
	public void sort(int a[]) {
		sort(a,0,a.length-1);
	}
	
	public void sort(int a[], int low, int high) {
		if(low < high) {
			int pivot = partition(a,low,high);//O(n)
			sort(a,low,pivot-1); // T(n) = 2T(n/2) + a*n + b
			sort(a,pivot+1,high); // reduces to O(n*logn) using master theorem. Learn master theorem.
		}
	}
	
	// See geeks for geeks for more info
	// O(n)
	private int partition(int a[], int low, int high) {
		int pivotIndex = randomNumber(low,high);
		int pivot = a[pivotIndex]; // consider last element as pivot
		swap(a,high,pivotIndex);
		int i = low; // initialize index to low. this would indicate the pivot's position
		
		for(int j=low;j<high;j++) {
			if(a[j] < pivot) { // if any element less than pivot's value should be to the left of pivot
				swap(a,i++,j); // Since i would indicate the pivot's position, move the smaller element
			}                  // to i's place while incrementing i
		}
		swap(a,i,high);	//now just swap ith element ( i was incremented last) and pivot
		return i;       // return i;
	}
	
	private int randomNumber(int left, int right) {
		return left + (int) Math.floor(Math.random() * (right - left + 1));
	}

	private void swap(int []a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
	}
}
