package kth_smallest;

public class SecondLargest {

	public static void main(String[] args) {
		int a[] = {2,1,5,3,9,7,8,0,5,6,10,13,21,12};
		int largest = Integer.MIN_VALUE;
		int second = largest;
		for(int x : a) {
			if(x > largest) {
				second = largest;
				largest = x;
			}else if(x > second) {
				second = x;
			}
		}
		System.out.println("Largest = "+largest);
		System.out.println("Second Largest = "+second);

	}

}
