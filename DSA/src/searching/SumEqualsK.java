package searching;

import java.util.HashSet;

public class SumEqualsK {

	public static void main(String[] args) {
		int a[] = {-7,4,8,1,3,2,5,7,6,9,0,-2,-3};
		HashSet<Integer> set = new HashSet<>();
		int sum = 5;
		for(int x : a) {
			if(set.contains(sum-x)) {
				System.out.println("Sum of "+x+" and "+(sum-x)+" equals "+sum);
			}
			set.add(x);
			
		}

	}

}
