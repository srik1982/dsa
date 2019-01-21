package divideandconquer;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Problem description: Given an array of 2n integers where 2n = 2^k
 *  in the following format a1 a2 a3 ...an b1 b2 b3...bn. 
 *  Shuffle the array to a1 b1 a2 b2 a3 b3 ... an bn without any extra memory
 * @author srikanthrao
 *
 */
public class AlternateElements {

	public static void main(String[] args) {
		String[] a = new String[32];
		for(int i=0;i<16;i++) {
			a[i] = "a"+i;
		}
		for(int i=0;i<16;i++) {
			a[i+16] = "b"+i;
		}
		
		AlternateElements alternator = new AlternateElements();
		alternator.alternateRecursive(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
		System.out.println(alternator.counter);
		System.out.println(alternator.counter2);
	}
	int counter = 0;
	int counter2 = 0;
	
	/**
	 * n*log(n)
	 * @param a
	 * @param low
	 * @param high
	 */
	public void alternateRecursive(String a[], int low, int high) {
		if(low < high) {
			counter2++;
			int half = (low+high)/2;
			int quarter = (low+half)/2;
			int threeFourth = (half+high)/2;
			
			for(int i=quarter+1, j = half+1; i<=half && j<=threeFourth;i++,j++) {
				swap(a,i,j);
			}
			alternateRecursive(a,low, half);
			alternateRecursive(a,half+1, high);
		}
	}
	private void swap(String[] a, int i, int j) {
		counter++;
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
