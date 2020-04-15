package leetcode;

/*
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:

Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

Example 2:

Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

 */
public class ContiguousArray {
	
	public static void main(String args[]) {
		ContiguousArray obj = new ContiguousArray();
		int [] nums = new int[] {0,1,0,1,0,0,1,1,0,0,0,0,1,0,1,1,0,1,1,1,1,0,0,0,0};
		int maxLen = obj.getContiguousArrayLength(nums);
		System.out.println(maxLen);
	}
	
	public int getContiguousArrayLength(int[] nums) {
		int start = 0;
		int end = 0;
		int maxLen = 0;
		int startingNum = -1, endingNum = -1;
		int zeroCt = 0, onesCt = 0;
		boolean starting = true, change = false;
		
		while(start<=end && end<nums.length) {
			if(!starting) {
				endingNum = nums[end];
				
				if(nums[end]==0)
					zeroCt++;
				else
					onesCt++;
				
				if(change && startingNum!=endingNum) {
					if(zeroCt == onesCt) {
						int len = onesCt + zeroCt;
						if(len > maxLen)
							maxLen = len;
					}
					
					if(startingNum == 0) {
						start = start + zeroCt;
						zeroCt = 0;
					}
					else {
						start = start + onesCt;
						onesCt = 0;
					}
					startingNum = nums[start];					
				}
				else if(!change && startingNum != endingNum) {
					change=true;
				}
				end++;
				
			}
			else if(starting) {
				if(nums[end]==0)
					zeroCt++;
				else
					onesCt++;
				starting = false;
				startingNum = nums[end];
				end++;
			}
			
		}
		return maxLen;
	}

}
