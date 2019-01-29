package divideandconquer;

import java.util.Arrays;

public class MedianOf2Arrays {

	public static void main(String[] args) {
		int m = 9, n = 12;
		int []a = new int[m];
		int []b = new int[n];
		
		for(int i=1;i<=m;i++)
		{
			a[i-1] = i;
		}
		for(int j=1; j<=n;j++) {
			b[j-1] = m+j;
		}
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
		int median = findMedian(b,n,a,m);
		System.out.println("Median = "+median);
	}

	private static int findMedian(int[] a, int m, int[] b, int n) {
		return findMedian(a,0,m,b,0,n);
	}

	private static int findMedian(int[] a, int s1, int e1, int[] b, int s2, int e2) {
		if(e1-s2 == 1 && e2-s2 == 1) { //if both arrays have an interval of 2 elements, then we find median there
			int median = (Math.min(a[e1], b[e2]) + Math.max(a[s1], b[s2]))/2;
			return median;
		}else if(e2-s2 == 0) { // these 2 cases take of cases where the arrays are disjoint
			return b[e2];
		}else if(e1-s1 == 0) { // i.e., one array comes after another
			return a[e1];
		}
		else { // otherwise keep moving till find a[m1] = b[m2]
			int m1 = (e1 + s1)/2;
			int m2 = (e2 + s2)/2;
			if(a[m1] > b[m2]) {
				return findMedian(a,s1,m1,b,m2,e2);
			}else if(a[m1] < b[m2]) {
				return findMedian(a,m1,e1,b,s2,m2);
			}else {
				return m1;
			}
		}
	}

}
