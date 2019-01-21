package searching;

public class InfiniteArray {
	/**
	 * Find the first 0's index when the size of the array is unknown and the array is filled with 1s at the start and 0s at the end
	 * 1111111...0000000...
	 * @param args
	 */
	public static void main(String[] args) {
		InfiniteArray obj = new InfiniteArray();
		int []a = new int[100000];
		for(int i=0;i<66667 ;i++) {
			a[i] = 1;
		}
		System.out.println(obj.modifiedBinarySearch(a));
		System.out.println(obj.counter);
	}
	int seed = 0;
	int counter = 0;
	/**
	 * Tried to find the time complexity. Its more than log(n) but can't really put the exact formula. Definitely less than sqrt(n)
	 * @param a
	 * @return
	 */
	public int modifiedBinarySearch(int []a) {
		int i = 1;
		int startIndex = seed;
		int endIndex = seed+2*i;
		while(endIndex < a.length && a[endIndex]==1) {
			startIndex = endIndex;
			i = 2*i;
			endIndex = endIndex+i;
			counter++;
		}
		System.out.println("Counter in first recursion : "+counter);
		if(endIndex == startIndex+2){// i.e., initial values
			for(int j=startIndex;j<endIndex;j++) {
				if(a[j] == 0)
				{
					return j;
				}
			}
			return -1;
		}else {
			seed = startIndex;
			return modifiedBinarySearch(a);
		}
	}

}
