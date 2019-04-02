package tree;

import stack.Stack;

public class BST2DLL {

	public static void main(String[] args) {
		BTNode root = TreeUtil.createBST();
		BTNode head = convertBst2Dll(root);
		for(BTNode n = head; n!=null; n = n.right) {
			System.out.println(n.data);
		}
	}
	
	public static BTNode convertBst2Dll(BTNode root) {
		BTNode node = root;
		BTNode prev = null, head = null;
		Stack<BTNode> stack = new Stack<BTNode>();
		while(node!=null || !stack.isEmpty()) {
			while(node!=null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			//this is where we print
			if(head == null) {
				head = node;
				prev = node;
			}else {
				prev.right = node;
				prev = node;
			}
			
			node = node.right;
		}
		return head;
	}
	
}
