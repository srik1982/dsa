package searching;

public class LocalMinima {
	// A binary search based function that returns 
		// index of a local minima. 
		public static int localMinUtil(int[] arr, int low, 
									int high, int n) 
		{ 
			
			// Find index of middle element 
			int mid = low + (high - low) / 2; 
			
			// Compare middle element with its neighbours 
			// (if neighbours exist) 
			if(mid == 0 || arr[mid - 1] > arr[mid] && mid == n - 1 || 
			arr[mid] < arr[mid + 1]) 
					return mid; 
			
			// If middle element is not minima and its left 
			// neighbour is smaller than it, then left half 
			// must have a local minima. 
			else if(mid > 0 && arr[mid - 1] < arr[mid]) 
					return localMinUtil(arr, low, mid - 1, n); 
			
			// If middle element is not minima and its right 
			// neighbour is smaller than it, then right half 
			// must have a local minima. 
			return localMinUtil(arr, mid + 1, high, n); 
		} 
		
		// A wrapper over recursive function localMinUtil() 
		public static int localMin(int[] arr, int n) 
		{ 
			return localMinUtil(arr, 0, n - 1, n); 
		} 
		
		
		public static void main (String[] args) 
		{ 
			
			int arr[] = {4, 3, 1, 14, 16, 40, 12, 2,19}; 
			int n = arr.length; 
			System.out.println("Index of a local minima is at " + localMin(arr, n)); 
		
		} 
		
		//ok the above localMinUtil doesn't work -- trying to use a log n algorithm on unsorted array is useless.
		//instead do a linear search.
}
