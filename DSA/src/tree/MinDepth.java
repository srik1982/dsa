package tree;

import queue.Queue;

public class MinDepth {

	public static void main(String[] args) {
		MinDepth obj = new MinDepth();
		BTNode node = obj.createBinaryTree();
		int min = obj.getMinDepthIterative(node);
		System.out.println(min);

	}
	public BTNode createBinaryTree() {
		BTNode root = new BTNode(1);
		root.left = new BTNode(2);
		root.right = new BTNode(3);
		root.left.left = new BTNode(4);
		root.left.right = new BTNode(5);
		root.right.left = new BTNode(6);
		root.right.right = new BTNode(7);
		root.right.right.left = new BTNode(8);
		root.right.right.right = new BTNode(9);
		
		return root;
	}
	
	public int getMinDepth(BTNode node) {
		if(node == null) {
			return -1;
		}else {
			int left = getMinDepth(node.left);
			int right = getMinDepth(node.right);
			return 1 + Math.min(left, right);
		}
	}
	
	public int getMinDepthIterative(BTNode root) {
		Queue<BTNode> q = new Queue<BTNode>();
		q.enqueue(root);
		q.enqueue(null);
		int depth = 0;
		boolean empty = false;
		while(!q.isEmpty()) {			
			empty = true;
			while(q.front()!=null) {
				BTNode node = q.dequeue();	
				if(node.left == null && node.right == null)break;
				
				if(node.left!=null)
					q.enqueue(node.left);
				
				if(node.right!=null)
					q.enqueue(node.right);	
				
				empty = false;
			}
			if(empty)break;		
			
			depth++;
			q.dequeue();
			q.enqueue(null);
		}
	
		return depth;
	}

}
