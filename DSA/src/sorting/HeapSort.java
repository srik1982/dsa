package sorting;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		int a[] = new int[] {6,-8,1,3,-2,5,4,9,-17,16};
		HeapSort sorter = new HeapSort();
		sorter.sort(a,a.length-1);
		System.out.println(Arrays.toString(a));

	}
	
	/**
	 * That AH Ravindrababu with his long video confused me into writing wrong logic in HeapSort.
	 * Well, i should not use bad language as these people are giving knowledge for free, and furthermore i didn't wait till i went to the end of the lecture.
	 * 
	 * After wasting close to 2 hours, i have got enlightened that BinaryMinHeap i wrote in the graphs section is pretty good.
	 * And I can use the same with some modifications
	 * 
	 * So instead of min heap, i will be implementing a max heap.
	 * Second, i don't need weights. Weight would be a special case if we need to remember the node key as well, which we dont need to do here.
	 * Third, and this is an additional restriction. we use in-place sorting. MinHeap used add() method to add each element to the arrayList, here
	 * we use the array provided.
	 * @param a
	 * @param size
	 */
	public void sort(int []a, int lastIndex) {
		for(int i=0;i<=lastIndex;i++) {
			addToHeap(a, i);
		}
		for(int i=lastIndex;i>=0;i--) {
			extractMax(a,i);
		}
	}
	
	public int extractMax(int []a, int lastIndex) {
		int max = a[0];
		swap(a,0,lastIndex--);
		
		int parent = 0;
		int lChild = 1;
		int rChild = 2;
		
		while(lChild < lastIndex && rChild <= lastIndex) {
			int maxIndex = -1;
			
			if(lChild < lastIndex && a[lChild] > a[parent]) {
				maxIndex = lChild;
			}
			if(rChild <= lastIndex && a[rChild] > a[parent]) {
				if(a[rChild] > a[lChild]) {
					maxIndex = rChild;
				}
			}
			
			if(maxIndex == -1) break;
			
			swap(a,parent,maxIndex);
			parent = maxIndex;
			lChild = parent * 2 + 1;
			rChild = lChild + 1;
		}
		
		return max;
	}
	
	public void addToHeap(int []a, int lastIndex) {
		int i = lastIndex;
		while(i>0) {
			int parentIndex = (i-1)/2;
			if(a[i] > a[parentIndex]) {
				swap(a,i,parentIndex);
				i = parentIndex;
			}else {
				break;
			}
		}
	}
	
	private void swap(int []a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
	}

}
