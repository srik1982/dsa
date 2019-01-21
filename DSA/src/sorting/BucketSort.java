package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BucketSort {

	public static void main(String[] args) {
		int []a = new int[] {23,56,12,88,46,88,5,7,99,54,76,43,81,56,19,12};
		BucketSort sorter = new BucketSort();
		a = sorter.sort(a);
		System.out.println(Arrays.toString(a));

	}
	int j=0;
	/**
	 * Some theory about Bucket sort. Ref for more info - https://www.youtube.com/watch?v=geVyIsFpxUs
	 * 1. So we use 10 buckets like in radix sort.
	 * 2. Choose the maximum number and divide by 10 to get a divider => Meaning the maximum value should fit into bucket when divided by divider
	 * 3. Divide each input by divider and based on the remainder, assign to appropriate bucket. Remainder 0 to 0th bucket and so on.
	 * 4. Once the assignment is done, each individual bucket is typically sorted with Insertion Sort.
	 * 5. Now add each bucket from 0 to 9 back to the original array, they are sorted.
	 * 
	 * Note here, that this kind of data distribution, insertion sort is supposed to be practically O(n). Although in worst case it is O(n2).
	 * I am not going to the maths part, it has been mathematically proven already that this <Bucket,Insertion_Sort> combination gives O(n).
	 * Check the prints from Insertion sort here to find out that this is actually true. 
	 *
	 * @param a
	 * @return
	 */
	public int [] sort(int [] a) {
		List<List<Integer>> buckets = new ArrayList<List<Integer>>();
		
		int bucketCount = 10;
		for(int i=0;i<bucketCount;i++) {
			buckets.add(new ArrayList<Integer>());
		}
		
		int max = 0;
		for(int x : a) {
			max = Math.max(x, max);
		}
		int divider = (int) Math.ceil(max/10.0); //using 10.0 here for double division instead of integer division
		
		for(int x : a) {
			int remainder = x/divider;
			buckets.get(remainder).add(x);
		}
		
		for(List<Integer> bucket : buckets) {
			InsertionSort.sort(bucket);
		}
		
		buckets.forEach(bucket -> {
			for(int x : bucket) {
				a[j++] = x;
			}
		});
		
		return a;
	}

}
