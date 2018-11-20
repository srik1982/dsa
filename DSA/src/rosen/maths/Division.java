package rosen.maths;

public class Division {

	public static void main(String[] args) {
		int a = 15000;
		int d = 15;
		quotient(a,d);
	}
	
	private static int quotient(int a , int d) {
		int q = 0;
		int counter = 1;
		int prevD = d;
		boolean done = false;
		int loopCounter = 0;
//		System.out.println("Log of a = "+logBase2(a));
		
		while(!done) {
			while(a>d) {
				d = d << 1;
				counter = counter << 1;
				loopCounter++;
			}
			if(a < d) {
				counter = counter >> 1;
			    q = q + counter;
				d = d >> 1;
				counter = 1;
				a = a - d;
				d = prevD;
				
				if (a < d) {
					done = true;
					System.out.println("Remainder = "+ a);
				}
			}else {
				q = q +1;
				done = true;
				System.out.println("Remainder = 0");
			}
		}
		System.out.println("Q = "+q);
		System.out.println("Loop Counter = "+loopCounter);

		return q;
	}
	
	private static int logBase2(int num) {
		int x = num;
		int count = 0;
		while(x>1) {
			x = x/2;
			count++;
		}
		return count;
	}

}
