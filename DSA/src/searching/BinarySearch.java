package searching;

public class BinarySearch {

	public static void main(String[] args) {
		int []a = {1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18};
		BinarySearch bs = new BinarySearch();
		System.out.println(bs.searchIterative(a, 13));

	}
	// we can improve the not found case performance. By checking if the item < a[low] || item> a[high], in O(1) we can dismiss the cases.
	// of course if its right in the middle, we still need the same process
	public int searchIterative(int []a, int item) {
		int low = 0;
		int high = a.length - 1;
		int index = -1;
		if(item< a[low] || item>a[high]) {
			return index;
		}
		while(low<=high) {
			int mid = low + (high-low)/2;
			if(a[mid] == item) {
				index = mid;
				break;
			}else if(item < a[mid]) {
				high = mid - 1;
			}else {
				low = mid+1;
			}
			
		}
		return index;
	}
	
	public int searchRecursive(int []a, int item) {
		int low = 0;
		int high = a.length - 1;
		int index = -1;
		if(item< a[low] || item>a[high]) {
			return index;
		}
		return searchRecursive(a,item,low,high);
	}
	private int searchRecursive(int[] a, int item, int low, int high) {
		if(low>=0 && high<=a.length-1 && low<=high) {
			int mid = low + (high-low)/2;
			if(a[mid] == item) {
				return mid;
			}else if(item<a[mid]) {
				return searchRecursive(a,item,low,mid-1);
			}else {
				return searchRecursive(a,item,mid+1,high);
			}
		}
		return -1;
	}

}
