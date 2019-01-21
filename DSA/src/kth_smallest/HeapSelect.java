package kth_smallest;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Using Heap / Priority queue to select the kth smallest 
 * There are 2 approaches here :
 * 1. Add each of the n elements to MIN HEAP. Total time = n * logn. Plus remove k elements using extractMin : klog(n) = n*log(n)
 * 2. Add k elements in k*logK to MAX HEAP. Note the max element. compare remaining (n-k) elements and only if they are smaller, replace the largest and replace with smaller one
 * The resulting Time Complexity is k*logk + (n-k)logK = nLogK 
 * @author srikanthrao
 *
 */
public class HeapSelect {

	public static void main(String[] args) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); // Descending order. We want kth smallest.
		
		int a[] = {99,91,81,2,6,3,1,44,31,23,65,89,87,42,56,19,25,77,75,68,55,49};
		int k = 7;//7th smallest
		for(int i=0;i<k;i++) {
			maxHeap.offer(a[i]);
		}
		for(int i=k;i<a.length;i++) {
			if(a[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.offer(a[i]);
			}
		}
		System.out.println("Kth (7th) smallest is "+maxHeap.peek());
		System.out.println("K Smallest elements are (in descending order)");
		while(!maxHeap.isEmpty()) {
			System.out.println(maxHeap.poll());
		}

	}

}
