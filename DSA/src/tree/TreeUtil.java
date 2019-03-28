package tree;

/*
 * Should i written this earlier. Instead of writing createBinaryTree() method in each class.
 */
public class TreeUtil {

	public static boolean isLeaf(BTNode root) {		
		return root!=null && root.left==null && root.right==null;
	}
	
	public static BTNode createBinaryTree() {
		BTNode root = new BTNode(1);
		root.left = new BTNode(2);
		root.right = new BTNode(3);
		root.left.left = new BTNode(4);
		root.left.right = new BTNode(5);
		root.left.right.right = new BTNode(9);
		root.right.left = new BTNode(6);
		root.right.right = new BTNode(7);
		root.right.right.left = new BTNode(8);		
		
		return root;
	}
}
