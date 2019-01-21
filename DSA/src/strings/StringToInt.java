package strings;

public class StringToInt {

	public static void main(String[] args) {
		System.out.println("Max value = "+Long.MAX_VALUE);
		System.out.println(StringToInt.convertToInt("1312-31123123"));
	}
	
	public static long convertToInt(String str) {
		if(str == null || str.length()==0) {
			throw new IllegalArgumentException("Illegal Argument : "+str);
		}
		char [] strArr = str.toCharArray();
		long number = 0;
		int zero = (int)'0';
		int power = 0;
		
		for(int i=str.length()-1;i>=0;i--) {
			char c = strArr[i];
			if(c >= '0' && c<= '9') {
				long temp = (long) ((c - zero) * Math.pow(10,power++)) ;
				if(temp == Long.MAX_VALUE) {
					throw new IllegalArgumentException("Exceeding max value of Long "+str);
				}
				number = number + temp;
				if(number<0) {
					//roll over happened
					throw new IllegalArgumentException("Exceeding max value of Long "+str);
				}
			}else if(c == '-' && i==0) {
				number = number * -1;
			}else if(c == '+' && i==0) {
				//do nothing. but this is acceptable.
			}
			else {
				throw new IllegalArgumentException("Illegal Argument : "+str);
			}
		}
		
		return number;
	}

}
