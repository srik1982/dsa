package bst.balanced;


public class AVLNode {
	public int key, height;
	public AVLNode left, right;

	public AVLNode(int d) {
		key = d;
		height = 1;
	}
}
