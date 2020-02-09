package priorityqueue;

import java.util.Arrays;

public class MinHeap implements MinHeapADT{
	int[] heap = new int[10];
	int lastIndex = -1;

	@Override
	public void insertAll(int[] arr) {
		if(arr.length > heap.length) {
			resizeHeap();
		}
		
		Arrays.stream(arr).forEach(elem -> {
			heap[++lastIndex] = elem;
			percolateUp(lastIndex);
		});
		
	}

	private void percolateUp(int i) {		
		int parent = parentIndex(i);
		if(i >= 0 && parent >=0) {
			if(heap[i] < heap[parent]) {
				swap(i,parent);
				percolateUp(parent);
			}			
		}
	}
	
	private void swap(int index, int lChildIndex) {
		int temp = heap[index];
		heap[index] = heap[lChildIndex];
		heap[lChildIndex] = temp;
		
	}
	
	private void percolateDown(int i) {
		int lChildIndex = lChildIndex(i);
		int rChildIndex = rChildIndex(i);
		if(i <= lastIndex && lChildIndex <= lastIndex && rChildIndex <= lastIndex) {
			if(heap[lChildIndex] < heap[rChildIndex]) {
				swap(i,lChildIndex);
				percolateDown(lChildIndex);
			}else {
				swap(i,rChildIndex);
				percolateDown(rChildIndex);
			}
		}
	}

	@Override
	public void insertKey(int key) {
		if(size() == heap.length-1) {
			resizeHeap();
		}
		heap[++lastIndex] = key;
		percolateUp(lastIndex);
	}

	@Override
	public int getMin() {
		return lastIndex >=0 ? heap[0] : -1;
	}

	@Override
	public int deleteMin() {
		if(lastIndex <0) throw new IllegalStateException();
		int temp = heap[0];
		if(size() > 3 || size() < 3) {		
			heap[0] = heap[lastIndex];
			lastIndex--;
			percolateDown(0);
		}else {
			int index = heap[1] < heap[2] ? 1 : 2;
			heap[0] = heap[index];
			heap[index] = -1;
			if(index == 1) swap(1,2);
			lastIndex--;
			percolateDown(0);
		}
		return temp;
	}
	
	private int lChildIndex(int i) {
		return 2*i+1;
	}
	
	private int rChildIndex(int i) {
		return 2*i+2;
	}
	
	private int parentIndex(int i) {
		return (i-1)/2;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		Arrays.stream(heap).forEach(elem -> sb.append(elem).append(" , "));
		sb.append(" ]");
		return sb.toString();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return (lastIndex + 1);
	}
	
	private void resizeHeap() {
		heap = Arrays.copyOf(heap, heap.length * 2);
	}
	


	@Override
	public void heapify(int[] arr) {
		this.heap = arr;
		lastIndex = heap.length - 1;
		heapify(lastIndex/2);		
	}
	
	private void heapify(int index) {
		if(index >=0) {
			percolateDown(index);
			heapify(index-1);
		}
	}

	@Override
	public void sort(int[] arr) {
		heapify(arr);
		for(int i=lastIndex; i>=0; i--) {
			int key = deleteMin();
			heap[i] = key;
		}
		System.out.println(Arrays.toString(arr));
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public static void main(String args[]) {
		int[] arr = new int[] {38 , 45 , 76 , 32 , 57 , 99 , 67 };
//		int[] arr = new int[] {6,2,4,1,5,7,8,0,9,3,17,12,13,19};
		MinHeap pq = new MinHeap();
		pq.heapify(arr);
		System.out.println(pq.toString());
//		pq.sort(arr);
//		Arrays.stream(arr).forEach(elem -> pq.insertKey(elem));
		
//		while(pq.size() > 0) {
//			System.out.println(pq.deleteMin());
//		}
	}
}
