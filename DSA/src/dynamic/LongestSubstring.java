package dynamic;

public class LongestSubstring {

	public static void main(String[] args) {
		System.out.println(LongestSubstring.longestSubStringDp("abcdefg".toCharArray(), "xycdeji".toCharArray()));
	}
	
	public static char[] longestSubStringDp(char[] input1, char[] input2) {
		char[] matching = new char[input1.length];
		int[][] table = new int[input1.length+1][input2.length+1];
		int k=0;
		for(int i=0;i<=input1.length;i++) {
			for(int j=0;j<=input2.length;j++) {
				if(i==0 || j==0) {
					table[i][j] = 0;
				}else {
					if(input1[i-1] == input2[j-1]) {
						table[i][j] = table[i-1][j-1]+1;
						matching[k++] = input1[i-1];
					}
				}
			}
		}
		
		return matching;
		
	}
	
	  /**
     * Recursive way of calculating lcs
     */
    public int longestCommonSubstringRec(char str1[], char str2[], int pos1, int pos2, boolean checkEqual){
        if(pos1 == -1 || pos2 == -1){
            return 0;
        }
        if(checkEqual){
            if(str1[pos1] == str2[pos2]){
                return 1 + longestCommonSubstringRec(str1, str2, pos1-1, pos2-1, true);
            }else{
                return 0;
            }
        }
        int r1 = 0;
        if(str1[pos1] == str2[pos2]){
            r1 = 1 + longestCommonSubstringRec(str1, str2, pos1-1, pos2-1, true);
        }
        return Math.max(r1,Math.max(longestCommonSubstringRec(str1, str2, pos1-1, pos2, false), longestCommonSubstringRec(str1, str2, pos1, pos2-1,false)));
    }
}
