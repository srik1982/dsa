package sorting;

import java.util.Arrays;

public class MPlusNArray {

	public static void main(String[] args) {
		Integer []mplusn = new Integer[] {-8,null,-2,null,1,3,4,null,null,5,6,null,6,null,9,null,77};
		Integer []narr = new Integer[] {-20,-3,-1,12,19,45,66};
		MPlusNArray obj = new MPlusNArray();
		obj.moveToEnd(mplusn);
		obj.merge(mplusn, narr, 10, 7);
		System.out.println(Arrays.toString(mplusn));
	}
	
	public void merge(Integer [] mPlusN, Integer[] nArr, int m, int n) {
		int k=0,i=n,j=0;
		while(k< (m+n) && j<n && i < (m+n)) {
			if(nArr[j] < mPlusN[i]) {
				mPlusN[k++] = nArr[j++];
			}else {
				mPlusN[k++] = mPlusN[i++];
			}
		}
		
	}
	
	public void moveToEnd(Integer [] mPlusN) {
		int i = mPlusN.length - 1, j = mPlusN.length - 1;
		while(i>=0) {
			if(mPlusN[i] !=null) {
				mPlusN[j--] = mPlusN[i]; 
//				mPlusN[i] = null;
			}
			i--;
		}
	}

}
