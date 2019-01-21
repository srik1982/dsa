package searching;

public class MissingNumber {

	//Using XOR
	public static void main(String[] args) {
		int a[] = {1,2,3,4,5,7,8,9};
		int x=0,y=0;
		
		for(int i=0;i<a.length;i++) {
			x = x ^ a[i];
		}
		for(int i=1; i<=a.length+1;i++) {
			y = y ^ i;
		}
		
		System.out.println(x ^ y);
	}
	

}
