package searching;

public class MaxRepeatingElement {

	public static void main(String[] args) {
		int a[] = {1,2,2,3,4,4,5,5,5,5,7,7,8,8,8,9,9,10,11,11,11,11};
		MaxRepeatingElement maxRepeatingElement = new MaxRepeatingElement();
		System.out.println(maxRepeatingElement.maxRepeatingElem(a));
	}
	// I think we can't really use the same array.
	// Need an auxiliary array to accomplish this
	//Or use a hashtable.
	public int maxRepeatingElem(int [] a) {
		int block = 0;
		int maxCount = 0;
		int maxIndex = 0;
		int arraySize = 0;
		
		for(int x: a) {
			if(x>block) {
				block = x;
			}
			arraySize++;
		}
		block++;
		for(int x: a) {
			a[x]+=1;
		}
		
		for(int i = 0; i< arraySize;i++) {
			if(a[i] > block && a[i] / block > maxCount) {
				maxCount = a[i] / block;
				maxIndex = i;
			}
		}
		return maxIndex;
		
	}

}
