package coding.threading;

public class SharedResource {
	
	private String[] sharedArray = new String[1];
	private int index = -1;
	
	public void add(String x) {
		sharedArray[++index] = x;
	}
	
	public String remove() {
		return sharedArray[index--];
	}
	
	public boolean isFull() {
		return index == sharedArray.length-1;
	}
	
	public boolean isEmpty() {
		return index == -1;
	}
	

}
