package searching;

public class NoOfRepetitions {

	/**
	 * Number of repetitions in a SORTED array
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = {0,1,2,3,4,5,5,5,5,6,6,7,8,9,9,9,10,10,11,11,11,11,11,11};
		NoOfRepetitions obj = new NoOfRepetitions();
		int first = obj.findFirstOccurence(a, 0, 0, a.length-1);
		int last = obj.findLastOccurence(a, 0, 0, a.length-1);
		System.out.println("Length = "+(last - first + 1));
	}
	
	public int findFirstOccurence(int a[], int key, int low, int high) {
		int index = -1;
		if(key< a[low] || key>a[high]) {
			return index;
		}
		while(low<=high) {
			int mid = low + (high-low)/2;
			if(mid == low && a[mid] == key ||  mid>low && a[mid] == key && a[mid-1] < key) {
				return mid;
			}else if(mid > low && a[mid] == key && a[mid - 1] == key) {
				high = mid - 1;
			}else if (a[mid] > key){
				high = mid - 1;
			}else {
				low = mid+1;
			}
			
		}
		return index;
	}
	public int findLastOccurence(int a[], int key, int low, int high) {
		int index = -1;
		if(key< a[low] || key>a[high]) {
			return index;
		}
		while(low<=high) {
			int mid = low + (high-low)/2;
			if(mid == high && a[mid] == key ||  mid<high && a[mid] == key && a[mid+1] > key) {
				return mid;
			}else if(mid < high && a[mid] == key && a[mid +1] == key) {
				low = mid + 1;
			}else if (a[mid] < key){
				low = mid + 1;
			}else {
				high = mid-1;
			}
			
		}
		return index;
	}

}
