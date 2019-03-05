package dynamic.v2;

/*
 * Longest Even Length Substring such that Sum of First and Second Half is same
 * Given a string ‘str’ of digits, find the length of the longest substring of ‘str’, such that the length of the substring is 2k digits and sum of left k digits is equal to the sum of right k digits.
 * Examples :
 * 
 * Input: str = "123123"
 * Output: 6
 * The complete string is of even length and sum of first and second
 * half digits is same
 * 
 * Input: str = "1538023"
 * Output: 4
 * The longest substring with same first and second half sum is "5380"
 */
public class FirstHalfSumEqualsSecond {

	public static void main(String[] args) {
		int[] arr = new int[] {1,5,3,8,0,2,3}; 
		System.out.println("max length = "+findMaxLength(arr));

	}
	/*
	 * Brute force. O(n3) 
	 */
	public static int findMaxLength(int[] arr){
		int maxLen = 0;
		
		for(int i=0;i<arr.length;i++) {
			for(int j=i+1; j<arr.length;j=j+2) {
				int len = j-i + 1;
				int left = 0, right = 0;
				for(int k=i;k<(i+len/2);k++) {
					left += arr[k];
					right += arr[k+len/2];
				}
				if(left == right && len > maxLen) {
					maxLen = len;
					System.out.println(" chars are ");
					for(int l = i; l<=j;l++)
						System.out.print(arr[l]+" , ");
				}
			}
		}
		
		return maxLen;
	}
	/*
	 * O(n2) and O(1) Space.
	 * This is also more intuitive.
	 */
	public static int findMaxLengthDp(int[] arr) {
		int maxLen = 0, n = arr.length;
		
		for(int i=0;i<n-1;i++) {
			int j=i, k=i+1;
			int lsum=0,rsum=0, len = k-j+1;
			while(j>=0 && k<n) {
				lsum += arr[j];
				rsum += arr[k];
				
				if(lsum == rsum && len > maxLen) {
					maxLen = len;
				}
			}
			
		}
		
		return maxLen;
	}
	
}
