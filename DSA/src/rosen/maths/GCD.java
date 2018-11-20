package rosen.maths;

public class GCD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Printing GCD of 24,64="+gcd(24,64));
	}
	
	private static int gcd(int a, int b) {
		int x=a,y=b;
		int r = 0;
		while(y!=0) {
			r = x % y;
			x = y;
			y = r;
		}
		
		return x;
	}
	static int recursivegcd(int a, int b) 
    { 
        // Everything divides 0  
        if (a == 0 || b == 0) 
           return 0; 
       
        // base case 
        if (a == b) 
            return a; 
       
        // a is greater 
        if (a > b) 
            return recursivegcd(a-b, b); 
        return recursivegcd(a, b-a); 
    } 
	static int lcm(int a, int b) 
    { 
        return (a*b)/gcd(a, b); 
    }

}
