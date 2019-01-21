package sorting;

import java.util.Arrays;

public class DutchNationalFlag {

	public static void main(String[] args) {
		int []a = {2,2,1,1,0,0,1,2,1,2,0,0,1,2,2,1,0,0};
		DutchNationalFlag sorter = new DutchNationalFlag();
		sorter.dnfSort(a);
		System.out.println(Arrays.toString(a));

	}
	public void dnfSort(int arr[]) {
		  int low = 0;
		  int mid = 0;
		  int high = arr.length - 1;
		  
		  // one pass through the array swapping
		  // the necessary elements in place
		  while (mid <= high) {
		    if      (arr[mid] == 0) { swap(arr, low++, mid++); }
		    else if (arr[mid] == 2) { swap(arr, mid, high--); }
		    else if (arr[mid] == 1) { mid++; }
		  }

	}
	private void swap(int []a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
	}

}
