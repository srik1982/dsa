package dynamic;

/*
 * Given a string, we want to cut it into pieces such that each piece is a palindrome. Write a function to return the minimum number of cuts needed.
 */
public class PalindromicPartitions {

	public static void main(String[] args) {
		System.out.println(getNoOfPartitionsDP("abcbda"));
		System.out.println(getNoOfPartitionsDP("adhbcbha"));
		System.out.println(getNoOfPartitionsDP("pqr"));
	}
	
	/*
	 * Time Complexity O(n2)
	 * Space Complexity O(n2)
	 */
	public static int getNoOfPartitionsDP(String text) {
		int N = text.length();
		
		//we shall first detect the palindromes and memoize and then write the logic for
		//finding how of cuts are needed.
		boolean[][] palindromes = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			palindromes[i][i] = true;
		}
		
		for(int i=N-2;i>=0;i--) {
			for(int j=i+1;j<N;j++) {
				int len = j-i+1;
				if(text.charAt(i) == text.charAt(j)) {
					if(len==2) {
						palindromes[i][j] = true;
					}else {
						palindromes[i][j] = palindromes[i+1][j-1];
					}
				}else {
					palindromes[i][j] = false;
				}
			}
		}
		
		//now that we have this aside, lets create a table for no of partitions
		int[][] partitions = new int[N][N];
		
		//Since single chars are palindromes and they don't need any partitions, partitions[i][i] = 0
		//but since 0 is the detault value, we don't have to initialize explicitly
		
		for(int i=N-2;i>=0;i--) {
			for(int j=i+1;j<N;j++) {
				int len = j-i+1;
				if(palindromes[i][j]) {
					partitions[i][j] = 0;
				}else if(len == 2) {
					partitions[i][j] = 1;
				}else if(len==3){
					//length 3 needs 2 partions and hence we need to check 2 other entries in partition table.
					partitions[i][j] = 1 + Math.min(partitions[i][j-1], partitions[i+1][j]);
				}else {
					//here we need 3 values to be considered.
					// the first 2 below correspond to splitting with 1 char on left/right
					int x = 1 + partitions[i][j-1];
					int y = 1 + partitions[i+1][j];
					//this one is 2 chars on either sides. Since they are built using the smaller strings, we don't have to iterate/recurse till we have 1 char
//					int z = 1 + partitions[i][j-2] + partitions[i+2][j];
//					partitions[i][j] = Math.min(Math.min(x, y), z);
					partitions[i][j] = Math.min(x, y);
				}
			}
		}
		
		return partitions[0][N-1];
	}

}
