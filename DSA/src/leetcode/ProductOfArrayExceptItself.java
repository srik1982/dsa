package leetcode;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]

Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).
 *
 */
public class ProductOfArrayExceptItself {
	public int[] productExceptSelf(int[] nums) {
        int [] output = new int[nums.length];
        output[0] = 1;
        for(int i=1;i<nums.length;i++){
            output[i] = output[i-1] * nums[i-1];
        }
        int lastNum = nums[nums.length-1];
        for(int i=nums.length-2;i>=0;i--){
            output[i] = output[i] * lastNum;
            lastNum *= nums[i];
        }
        return output;
    }
}
