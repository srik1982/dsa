package leetcode;

/*
 * You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [direction, amount]:

    direction can be 0 (for left shift) or 1 (for right shift). 
    amount is the amount by which string s is to be shifted.
    A left shift by 1 means remove the first character of s and append it to the end.
    Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.

Return the final string after all operations.

 

Example 1:

Input: s = "abc", shift = [[0,1],[1,2]]
Output: "cab"
Explanation: 
[0,1] means shift to left by 1. "abc" -> "bca"
[1,2] means shift to right by 2. "bca" -> "cab"
 */
public class Shifts {

	public static void main(String[] args) {
		String s = "mecsk";
		int[][] shifts = new int[5][2];
		shifts[0][0] = 1;
		shifts[0][1] = 4;
		shifts[1][0] = 0;
		shifts[1][1] = 5;
		shifts[2][0] = 0;
		shifts[2][1] = 4;
		shifts[3][0] = 1;
		shifts[3][1] = 1;
		shifts[4][0] = 1;
		shifts[4][1] = 5;
		
		
		Shifts obj = new Shifts();
		String result = obj.stringShift(s, shifts);
		System.out.println(result);

	}
	
    public String stringShift(String s, int[][] shift) {
        
        int leftShifts = 0;
        int rightShifts = 0;
        
        for(int i=0;i<shift.length;i++){
        	if(shift[i][0] == 0)
        		leftShifts += shift[i][1];
        	else
        		rightShifts += shift[i][1];
        }
        
        String retString = s;
        
        if(leftShifts!=rightShifts){
        	if(leftShifts>rightShifts){
                int shifts = (leftShifts - rightShifts) % s.length();
                String subStrRight = retString.substring(0,shifts);
                String subStrLeft = retString.substring(shifts);
                retString = subStrLeft+subStrRight;
            }else{
                int shifts = (rightShifts - leftShifts) % s.length();
                String subStrLeft = retString.substring(s.length()-shifts);
                String subStrRight = retString.substring(0,s.length()-shifts);
                retString = subStrLeft+subStrRight;
            }
            
        }
            
        return retString;
    }
	    

}
