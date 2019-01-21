package sorting;

import java.util.Arrays;

/**
 * Variation of DutchNationalFlag problem.
 * @author srikanthrao
 *
 */
public class SeparateOddAndEven {

	public static void main(String[] args) {
		int a[] = {1,4,2,5,3,6,7,4,9,8,0,12,56,22,11,19,27,31,44,47,99,78};
		SeparateOddAndEven obj = new SeparateOddAndEven();
		obj.separate(a);
		System.out.println(Arrays.toString(a));
	}
	
	public void separate(int []a) {
		int low = 0;
		int high = a.length - 1;
		
		while(low < high) {
			if(a[low] % 2 == 0 && a[high] %2 == 1) {
				swap(a,low,high);
			}else if(a[low] %2 == 0 && a[high] %2 == 0) {
				high--;
			}else if(a[low] %2 == 1 && a[high] %2 == 0) {
				low++;
				high--;
			}else {
				low++;
			}
		}
	}
	private void swap(int []a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
	}

}
