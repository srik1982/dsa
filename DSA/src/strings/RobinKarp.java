package strings;

/**
 * https://www.youtube.com/watch?v=H4VrKHVG5qI&list=PLrmLmBdmIlpvm7VaC0NTR27A_3i2sU3zd&index=2
 * https://www.youtube.com/watch?v=qQ8vS2btsxI
 * 
 * Time Complexity O(m+n) where m is the substring length, n is the main string length
 * @author srikanthrao
 *
 */
public class RobinKarp {

	public static void main(String[] args) {
		RobinKarp obj = new RobinKarp();
		char[] str1 = "srikanth ashwathanarayana rao".toCharArray();
		char[] str2 = "rao".toCharArray();
		int index = obj.subString(str1, str2);
		System.out.println("String found at "+index);
	}
	
	/**
	 * 1. Find out the hashCode for the main String and the sub String
	 * 2. The algorithm we use to find the hashCode is - a0 * prime^0 + a1 * prime^1 + ... am * prime^(m-1)
	 * 3. for( i =0 to n-m+1 )
	 *    3.1 find hashCode of the m chars starting at i
	 *    3.2 match that with substring hashCode. if equal we found our match
	 *    3.3 Else, rehash()
	 *        Rehash subtracts main[i] * prime^0
	 *        divide by prime so that we reduce the powers
	 *        add the lastChar * prime^(m-1)
	 *    3.4 Back to 3.2
	 * @param main
	 * @param sub
	 * @return
	 */
	public int subString(char[] main, char[] sub) {
		int n = main.length;
		int m = sub.length;
		long subHash = hashCode(sub, 0, m-1);
		long slidingHash = hashCode(main, 0, m-1);
		int index = -1;
		
		for(int i=0;i<n-m+1;i++) {
			for(int j=i;j<i+m;j++) {
				System.out.print(main[j]);
			}
			
			if(subHash == slidingHash && equals(main,i,i+m-1,sub)) {
				index = i;
				break;
			}else {
				slidingHash = rehash(main,i, i+m, m-1, slidingHash);
			}
		}
		
		return index;
	}
	
	private long rehash(char[] main, int currentIndex,int nextIndex, int power, long currentHash) {
		long newHash = currentHash - main[currentIndex];
		newHash = newHash / prime;
		newHash +=  main[nextIndex] * Math.floor(Math.pow(prime, power));
		return newHash;
	}

	private boolean equals(char[] main, int mainSt, int mainEnd, char[] sub) {
		boolean retVal = true;
		
		for(int i=mainSt, j=0; i<=mainEnd && j<sub.length; i++,j++) {
			if(main[i] != sub[j]) {
				retVal = false;
				break;
			}
		}
		
		return retVal;
	}

	int prime = 101;
	/**
	 * We can use mod to prevent overflow. Since we are using long, i guess we are pretty safe.
	 * @param sub
	 * @param start
	 * @param end
	 * @return
	 */
	private long hashCode(char[] sub, int start, int end) {
		long hashCode = 0;
		for(int i=start;i<=end;i++) {
			hashCode += sub[i] * Math.floor(Math.pow(prime, i));
		}
		return hashCode;
	}

}
