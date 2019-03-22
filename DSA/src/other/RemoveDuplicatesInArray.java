package other;

/*
 * Remoev duplicates and print once.
 */
public class RemoveDuplicatesInArray {

	public static void main(String[] args) {
		int[] a = new int[] {1,1,2,2,3,3,4,5,5,6};
		int j=0;
		for(int i=0;i<a.length-1;i++) {
			if(a[i] != a[i+1]) {
				a[j++] = a[i];
			}
		}
		
		for(int i=0;i<j;i++) {
			System.out.println(a[i]);
		}

	}

}
