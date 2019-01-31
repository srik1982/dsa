package divideandconquer;

class MaxSubarray {

	  // function to return maximum number among three numbers
	  static int maximum(int a, int b, int c)
	  {
	    if (a>=b && a>=c)
	      return a;
	    else if (b>=a && b>=c)
	      return b;
	    return c;
	  }

	  // function to find maximum sum of subarray crossing the middle element
	  static int maxCrossingSubarray(int ar[], int low, int mid, int high)
	  {
	    /*
	      Initial leftSum should be -infinity.
	    */
	    int leftSum = Integer.MIN_VALUE;
	    int sum = 0;
	    int i;

	    /*
	      iterating from middle
	      element to the lowest element
	      to find the maximum sum of the left
	      subarray containing the middle
	      element also.
	    */
	    for (i=mid; i>=low; i--)
	    {
	      sum = sum+ar[i];
	      if (sum>leftSum)
	        leftSum = sum;
	    }

	    /*
	      Similarly, finding the maximum
	      sum of right subarray containing
	      the adjacent right element to the
	      middle element.
	    */
	    int rightSum = Integer.MIN_VALUE;
	    sum = 0;

	    for (i=mid+1; i<=high; i++)
	    {
	      sum=sum+ar[i];
	      if (sum>rightSum)
	        rightSum = sum;
	    }

	    /*
	      returning the maximum sum of the subarray
	      containing the middle element.
	    */
	    return (leftSum+rightSum);
	  }

	  // function to calculate the maximum subarray sum
	  static int maxSumSubarray(int ar[], int low, int high)
	  {
	    if (high == low) // only one element in an array
	    {
	      return ar[high];
	    }

	    // middle element of the array
	    int mid = (high+low)/2;

	    // maximum sum in the left subarray
	    int maximumSumLeftSubarray = maxSumSubarray(ar, low, mid);
	    // maximum sum in the right subarray
	    int maximumSumRightSubarray = maxSumSubarray(ar, mid+1, high);
	    // maximum sum in the array containing the middle element
	    int maximumSumCrossingSubarray = maxCrossingSubarray(ar, low, mid, high);

	    // returning the maximum among the above three numbers
	    return maximum(maximumSumLeftSubarray, maximumSumRightSubarray, maximumSumCrossingSubarray);
	  }

	  public static void main(String[] args) {
	    int a[] = {3, -1, -1, 10, -3, -2, 4, 5, 9, -4, -2, 8, -1};
	    System.out.println(maxSumSubarray(a, 0, 12));
	  }
	}
