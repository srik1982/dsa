package linkedlist;

public class SLLNode {
	int data;
	SLLNode next;
	
	public SLLNode() {}
	public SLLNode(int data) {
		this.data = data;
	}
	public SLLNode(int data, SLLNode next) {
		this(data);
		this.next = next;
	}
	
	public String toString() {
		return String.valueOf(data);
	}
}
