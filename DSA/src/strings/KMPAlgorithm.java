package strings;

/**
 * https://www.youtube.com/watch?v=GTJr8OvyEVQ
 * https://www.youtube.com/watch?v=KG44VoDtsAA
 * SubstringSearch - Tushar-Programs
 * @author srikanthrao
 *
 */
public class KMPAlgorithm {

	public static void main(String[] args) {
		KMPAlgorithm obj = new KMPAlgorithm();
		String str = "srikanth srikarao rap ashwatha nararayana raya";
        String subString = "raya";
        System.out.println("matching = "+obj.searchSubstring(str.toCharArray(), subString.toCharArray()));
	}
	
	/**
	 * Its a difficult algorithm.
	 * Instead of describing this, its better to go through the video once again.
	 * @param pattern
	 * @return
	 */
	private int[] prefixArray(char[] pattern) {
		int j=0;
		int[] lps = new int[pattern.length];
		for(int i=1;i<pattern.length;) {
			if(pattern[j] == pattern[i]) {
				lps[i] = j+1;
				j++;
				i++;
			}else if(j!=0) {
				j = lps[j-1];
			}else {
				lps[i++] = 0;
			}
		}
		return lps;
	}
	
	public int searchSubstring(char[] text, char []pattern) {
		int[] lps = prefixArray(pattern);
		int i=0,j=0;
		int index = -1;
		while(i<text.length && j<pattern.length) {
			if(text[i] == pattern[j]) {
				if(index==-1) {
					index = i;
				}
				i++;
				j++;
			}else if(j!=0) {
				j = lps[j-1];
				index = -1; // false positive earlier
			}else {
				i++;
			}
		}
		
		return index; // returns first matching index
	}
	
	/**
     * Slow method of pattern matching
     */
    public boolean hasSubstring(char[] text, char[] pattern){
        int i=0;
        int j=0;
        int k = 0;
        while(i < text.length && j < pattern.length){
            if(text[i] == pattern[j]){
                i++;
                j++;
            }else{
                j=0;
                k++;
                i = k;
            }
        }
        if(j == pattern.length){
            return true;
        }
        return false;
    }

}
