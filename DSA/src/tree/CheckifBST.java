package tree;

import stack.Stack;

public class CheckifBST {

	public static void main(String[] args) {
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
		
		bst.traverse();
		
		CheckifBST obj = new CheckifBST();
		System.out.println("\n is BST "+obj.checkIfBST(bst.getRoot()));
		System.out.println("Is BST "+obj.checkIfBST(TreeUtil.createBinaryTree()));
	}
	
	boolean checkIfBST(BTNode root) {
		return inOrderIterative(root, Integer.MIN_VALUE);
	}

	
	
	public boolean inOrderIterative(BTNode root, int prev) {
		BTNode node = root;
		Stack<BTNode> stack = new Stack<BTNode>();
		while(node!=null || !stack.isEmpty()) {
			while(node!=null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			//this is where we print
			if(node.data < prev) {
				return false;
			}else {
				prev = node.data;
			}
			node = node.right;
		}
		return true;
	}
}
