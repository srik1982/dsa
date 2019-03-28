package tree;

public class BTNode {
	int data;
	BTNode left;
	BTNode right;
	
	public BTNode(int data){
		this.data = data;
	}
	public BTNode(int data, BTNode left){
		this(data);
		this.left = left;
	}
	public BTNode(int data, BTNode left, BTNode right) {
		this(data,left);
		this.right = right;
	}
	public String toString() {
		return String.valueOf(data);
	}
}
