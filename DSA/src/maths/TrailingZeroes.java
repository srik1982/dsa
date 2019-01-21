package maths;

/**
 * Trailing zeroes in n factorial
 * @author srikanthrao
 *
 */
public class TrailingZeroes {

	public static void main(String[] args) {
		System.out.println(TrailingZeroes.trailingZeroes(1000));

	}
	
	public static int trailingZeroes(int n) {
		int count = 0;
		int power = 1;
		boolean stop = false;
		while(!stop) {
			int factors= (int) Math.floor(n/Math.pow(5, power++));
			if(factors > 0) {
				count += factors;
			}else {
				stop = true;
			}
		}
		return count;
	}

}
