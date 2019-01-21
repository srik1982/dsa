package kth_smallest;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * References :
 * https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array-set-3-worst-case-linear-time/
 * https://www.youtube.com/watch?v=fcf56RTbkHc
 * Page 611, Karumanchi
 * 
 * O(n) in worst case. Don't ask me for proof, really complicated.
 * @author srikanthrao
 *
 */
public class MedianOfMedians {

	public static void main(String[] args) {
		MedianOfMedians obj = new MedianOfMedians();
		int a[] = new int [123];
		for(int i=0;i<123;i++) {
			a[i] = obj.getRandomNumber(123);
		}
		int k = 110;
		System.out.println(Arrays.toString(a));
		obj.findKthSmallest(a, k);
		
		
		System.out.println(k+"th element is "+a[k]);
		
//		Arrays.sort(a);
//		System.out.println("After arrays.sort "+ Arrays.toString(a));
//		System.out.println(k+"th element is "+a[k]);
	}
	
	public void findKthSmallest(int []a, int k) {
		boolean found = false;
		int low = 0;
		int high = a.length-1;
		while(!found) {
			int x = findMedian(a,low,high);
			int index = partition(a,low, high, x);
			if(index == k) {
				found = true;
			}else if(k < index) {
				high = index - 1;
			}else {
				low = index + 1;
			}
		}
	}
	
	private  void swap(int[] array, int a, int b) {
		int tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}
	
	private  int partition(int[] a, int low, int high, int pivot) {
		int pivotIndex = -1;
		for(int i=low;i<=high;i++) {
			if(a[i] == pivot)
			{
				pivotIndex = i;
				break;
			}
		}
		if(pivotIndex == -1) {
			System.out.println("Pivot not found = "+pivot+" low = "+low+" , high="+high);
		}
		
		swap(a,pivotIndex,high);
		int i = low;
		for(int j=low; j<= high-1;j++) {
			if(a[j] < pivot) {
				swap(a,i++,j);
			}
		}
		swap(a,i,high);
		return i;
	}


	Set<Integer> randomNumbers = new HashSet<Integer>();
	private  int getRandomNumber(int max) {
		
		int num = -1;
		while(true) {
			 num = (int) Math.floor(Math.random() * max);
			if(!randomNumbers.contains(num)) {
				randomNumbers.add(num);
				break;
			}
		}
		return num;
	}

	public int findMedian(int []a, int low, int high) {
		if(high - low + 1 > 5) {
			int size = high - low + 1;
			int arraySize = size % 5 == 0 ?  (size / 5) : ((size/5) + 1);
			int [] medians = new int[arraySize];
			for(int i=low, j=0;i<=high;i=i+5) {
				int firstIndex = i;
				int lastIndex = (i + 4) > high ? high : (i+4);
				sort(a,firstIndex,lastIndex);
				medians[j++] = a[(firstIndex+lastIndex)/2];
			}
			return findMedian(medians, 0, medians.length - 1);
		}else {
			sort(a,low,high);
			return a[(low+high)/2] ;
		}
		
	}
	
	public void sort(int []a, int low, int high) {
		for(int i = low + 1; i<=high;i++) {
			int key = a[i];
			int j=i-1;
			while(j>=low) {
				if(a[j] < key) {
					break;
				}else {
					a[j+1] = a[j];
					j--;
				}
			}
			a[j+1] = key;
		}
	}
}
