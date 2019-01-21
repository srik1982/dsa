package sorting;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int a[] = new int[] {6,8,1,3,2,5,4,9,7,6};
		MergeSort sorter = new MergeSort();
		sorter.mergeSort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
	}
	
	public void mergeSort(int [] a, int l, int h) {
		if(l<h) {
			int m = (l + h - 1)/2;
			mergeSort(a,l,m);
			mergeSort(a,m+1,h);
			merge(a,l,m,h);
		}
	}
	// T(n) = an+b
	private void merge(int[] a, int l, int m, int h) {
		int n1 = m-l+1;
		int n2 = h-m; // second part is m+1 to h
		int p1[] = new int[n1];
		int p2[] = new int[n2];
		
		for(int i=l,k=0;i<=m;i++,k++) {
			p1[k] = a[i];
		}
		for(int i=m+1,k=0;i<=h;i++,k++) {
			p2[k] = a[i];
		}
		
		int i=l;
		int p1Index = 0, p2Index = 0;
		
		while(p1Index < n1 && p2Index< n2) {
			if(p1[p1Index]<=p2[p2Index]) {
				a[i++] = p1[p1Index++];
			}else {
				a[i++] = p2[p2Index++];
			}
		}
		
		while(p1Index < n1) {
			a[i++] = p1[p1Index++];
		}
		
		while(p2Index < n2) {
			a[i++] = p2[p2Index++];
		}
		
	}

}
