package priorityqueue;

public interface MinHeapADT {
	public void insertAll(int [] arr);
	public void insertKey(int key);
	public void heapify(int [] arr);
	public int getMin();
	public int deleteMin();
	public int size();
	public String toString();
	public void sort(int [] arr);
	public boolean isEmpty();
}
