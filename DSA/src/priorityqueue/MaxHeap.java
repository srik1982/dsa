package priorityqueue;

import java.util.Arrays;

import graphs.v2.BinaryMinHeap.HeapNode;

public class MaxHeap implements MaxHeapADT{	

	public static void main(String[] args) {
		int[] arr = new int[] {6,2,4,1,5,7,8,0,9,3,17,12,13,19};
		MaxHeap pq = new MaxHeap();
		pq.sort(arr);
		
//		while(pq.size() > 0) {
//			System.out.println(pq.deleteMax());
//		}
	}
	
	private final int DEFAULT_SIZE = 10;
	
	private int[] heap = new int[DEFAULT_SIZE];
	private int lastIndex = 0;

	/*
	 * O(n) = n * logn
	 */
	@Override
	public void insertAll(int[] arr) {
		if(arr.length > heap.length) {
			resizeHeap();
		}
		
		Arrays.stream(arr).forEach((key) -> {
			heap[lastIndex] = key;
			percolateUp(lastIndex++);
		});
		
		System.out.println(toString());
	}

	@Override
	public void insertKey(int key) {
		if(lastIndex == heap.length - 1) {
			resizeHeap();
		}
		heap[lastIndex] = key;
		percolateUp(lastIndex++);
	}

	@Override
	public int getMax() {
		return heap[0];
	}

	@Override
	public int deleteMax() {
		int temp = heap[0];
		if(size() > 3 || size() < 3) {
			heap[0] = heap[lastIndex-1];
			lastIndex--;
		}else if(size() == 3){
			int index = heap[1] > heap[2] ? 1 : 2;
			heap[0] = heap[index];
			heap[index] = -1;
			if(index == 1)
				swap(1,2);
			lastIndex--;
		}
		
		percolateDown(0); 
		return temp;
	}
	
	private void resizeHeap() {
		heap = Arrays.copyOf(heap, heap.length * 2);
	}
	
	private void percolateDown(int index) {
		int lChildIndex = lChildIndex(index);
		int rChildIndex = rChildIndex(index);
		if(lChildIndex < lastIndex && rChildIndex < lastIndex) {
			int lChildKey = heap[lChildIndex];
			int rChildKey = heap[rChildIndex];
			
			if(lChildKey > rChildKey && heap[index] < lChildKey) {
				swap(index,lChildIndex);
				percolateDown(lChildIndex);
			}else if(heap[index] < rChildKey) {
				swap(index,rChildIndex);
				percolateDown(rChildIndex);
			}			
		}else if(lChildIndex < lastIndex) {
			int lChildKey = heap[lChildIndex];
			if(heap[index] < lChildKey) {
				swap(index,lChildIndex);
				percolateDown(lChildIndex);
			}
		}else if(rChildIndex < lastIndex) {
			int rChildKey = heap[rChildIndex];
			if(heap[index] < rChildKey) {
				swap(index,rChildIndex);
				percolateDown(rChildIndex);
			}
		}
	}
	
	private void swap(int index, int lChildIndex) {
		int temp = heap[index];
		heap[index] = heap[lChildIndex];
		heap[lChildIndex] = temp;
		
	}

	private void percolateUp(int index) {
		if(index >= 0) {
			int parentIndex = parentIndex(index);
			int parentKey = heap[parentIndex];
			
			if(heap[index] > parentKey) {
				swap(index,parentIndex);
				percolateUp(parentIndex);
			}			
		}
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

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return lastIndex;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		Arrays.stream(heap).forEach(elem -> sb.append(elem).append(" , "));
		sb.append(" ]");
		return sb.toString();
	}

	@Override
	public void heapify(int[] keys) {
		heap = keys;
		lastIndex = keys.length;
		heapify(lastIndex/2);
		System.out.println(toString());
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
		for(int i=lastIndex-1; i>=0; i--) {
			int key = deleteMax();
			heap[i] = key;
		}
		System.out.println(Arrays.toString(arr));
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

}
