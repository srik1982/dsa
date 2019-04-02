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
	
	public static BTNode createBST() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(8);
		bst.insert(4);
		bst.insert(6);		
		bst.insert(2);
		bst.insert(12);
		bst.insert(10);
		bst.insert(14);
		bst.insert(13);
		bst.insert(15);
		return bst.getRoot();
	}
}
