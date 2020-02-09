package priorityqueue;

public interface MaxHeapADT {
	public void insertAll(int [] arr);
	public void insertKey(int key);
	public void heapify(int[] keys);
	public int getMax();
	public int deleteMax();
	public int size();
	public String toString();
	public void sort(int [] arr);
	public boolean isEmpty();
}
