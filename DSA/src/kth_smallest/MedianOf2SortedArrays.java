package kth_smallest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reference : https://www.youtube.com/watch?v=LPFhl65R7ww
 * @author srikanthrao
 *
 */
public class MedianOf2SortedArrays {

	public static void main(String[] args) {
		int []a = {2,3,5,7,8,9,11};
		int []b = {4,6,10,12,13,14,15,16};
		
		int median = findMedian(a,b);
		
		System.out.println("Median = "+median);
		System.out.println("By Traditional merge and sort method");
		
		int c[] = new int[a.length + b.length];
		
		int j=0;
		for(int i=0;i<a.length;i++) {
			c[j++] = a[i];
		}
		for(int i=0;i<b.length;i++) {
			c[j++] = b[i];
		}
		Arrays.sort(c);
		
		System.out.println(Arrays.toString(c));

	}

	private static int findMedian(int[] x, int[] y) {
		int startX = 0;
		int endX = x.length - 1;
		int sizeX = x.length;
		int sizeY = y.length;
		
		if(sizeX > sizeY) {
			findMedian(y,x);
		}
		
		int median = -1;
		while(startX <= endX) {
			int partX = (startX + endX)/2;
			int partY = (sizeX + sizeY + 1)/2 - partX;
			
			int maxLeftX = partX <= 0 ? Integer.MIN_VALUE : x[partX - 1];
			int minRightX = partX<= x.length - 1 ? x[partX] : Integer.MAX_VALUE;
			int maxLeftY= partY <= 0 ? Integer.MIN_VALUE : y[partY-1];
			int minRightY = partY<= y.length - 1 ? y[partY] : Integer.MAX_VALUE;
			
			if(maxLeftX <= minRightY && maxLeftY <= minRightX) {
				median = (sizeX + sizeY)%2 == 1 ? Math.max(maxLeftX, maxLeftY) : (Math.max(maxLeftX,maxLeftY) + Math.min(minRightY, minRightY))/2;
				break;
			}else if(maxLeftX > minRightY) {
				endX = partX - 1;
			}else if(maxLeftY > minRightX) {
				startX = partX + 1;
			}
		}
		
		return median;
	}

}
