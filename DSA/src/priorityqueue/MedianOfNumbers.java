package priorityqueue;

import java.util.Arrays;
import java.util.Random;

public class MedianOfNumbers {

	private MinHeapADT minHeap = new MinHeap();
	private MaxHeapADT maxHeap = new MaxHeap();
	
	public void add(int item) {
		if(maxHeap.isEmpty() || item < maxHeap.getMax()) {
			maxHeap.insertKey(item);
		}else {
			minHeap.insertKey(item);
		}
		
		rebalance();
	}
	
	public void rebalance() {
		int diff = maxHeap.size() - minHeap.size();
		if(Math.abs(diff) >= 2) {
			if(diff > 1) {
				minHeap.insertKey(maxHeap.deleteMax());
			}else {
				maxHeap.insertKey(minHeap.deleteMin());
			}
		}
	}
	
	
	public double getMedian() {
		int count = maxHeap.size() + minHeap.size();
		if(count % 2 == 1) {
			return maxHeap.size() > minHeap.size() ? maxHeap.getMax() : minHeap.getMin();
		}else {
			return (maxHeap.getMax() + minHeap.getMin())/2;
		}
	}
	
	
	public static void main(String args[]) {
		Random rand = new Random();
		MedianOfNumbers median = new MedianOfNumbers();
//		for(int i=0; i<100;i++) {
//			median.add(rand.nextInt());
//		}
		int[] arr = new int[] {2,7,18,12,-7,1,45,32,57,22,99,76,38,67,17,21};
		Arrays.stream(arr).forEach(item -> median.add(item));
		System.out.println(median.maxHeap.toString());
		System.out.println(median.minHeap.toString());
		System.out.println(median.getMedian());
	}
}
