package strings;

import java.util.Arrays;

public class ReverseString {

	public static void main(String[] args) {
		ReverseString obj = new ReverseString();
		char[] text = obj.reverseRecursive("abbadcargt8ooqpjdjhsb".toCharArray(), 0, "abbadcargt8ooqpjdjhsb".length()-1);
		System.out.println(Arrays.toString(text));

	}
	
	private char[] reverseRecursive(char[] text, int i, int j) {
		if(i==j) {
			return text;
		}else if(i+1 == j) {
			char temp = text[i];
			text[i] = text[j];
			text[j] = temp;
			return text;
		}else {
			char temp = text[i];
			text[i] = text[j];
			text[j] = temp;
			return reverseRecursive(text,i+1,j-1);
		}
	}

}
