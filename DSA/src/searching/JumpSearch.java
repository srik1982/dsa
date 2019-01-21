package searching;

public class JumpSearch {

	public static void main(String[] args) {
		 int a[] = new int[]{10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 
				24, 33, 35, 42, 47}; 
		 JumpSearch obj = new JumpSearch();
		 System.out.println(obj.search(a, 5));
	}
	/**
	 * Time Complexity O(sqrt(n))
	 * @param a
	 * @param item
	 * @return
	 */
	public int search(int []a, int item) {
		int index = -1;
		int jumpSize = (int) Math.sqrt(a.length);
		int low = 0;
		int high = jumpSize;
		boolean found = false;
		while(high < a.length && item >= a[low] && item <=a[high] ) {
			if(item <= a[high] && item >= a[low]) {
				found = true;
				break;
			}else {
				low = high;
				high = high + jumpSize;
			}
		}
		
		if(found) {
			for(int i=low;i<=high;i++) {
				if(a[i] == item) {
					index = i;
				}
			}
		}
		return index;
	}

}
