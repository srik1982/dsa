package searching;

import sorting.MergeSort;

public class MajorityElement {

	public static void main(String[] args) {
		int []a = {7,1,2,4,1,1,5,4,1,6,8,1,2,3,5,1,9,5,1,3,1,7,8,5,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		MergeSort sort = new MergeSort();
		sort.mergeSort(a, 0, a.length-1);
		MajorityElement obj = new MajorityElement();
		System.out.println(obj.noRepsGeneric(a));

	}
	
	/**
	 * No of Repetitions of the majority element i.e. one that repeats more than n/2 times.
	 * Time Complexity O(n)
	 * @param a
	 * @return
	 */
	int noRepsGeneric(int a[]) {
		int max_count = 0;
		int repeatingElement = -1;
		int curr_count = 0;
		
		for(int i=0;i<a.length;i++) {
			if(i==0) {
				curr_count++;
				max_count = curr_count;
				repeatingElement = a[i];
			}else if(a[i] == a[i-1]) {
				curr_count++;
			}else if(curr_count > max_count) {
				max_count = curr_count;
				curr_count = 0;
				repeatingElement = a[i-1];
			}
		}
		System.out.println("Max Count = "+max_count);
		return repeatingElement;
	}

}
