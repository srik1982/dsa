package tree;

public class AVLNode {
	int data;
	AVLNode left;
	AVLNode right;
	int height;
	
	AVLNode(int data){
		this.data = data;
	}
	
	public String toString() {
		return String.valueOf(data);
	}
}
