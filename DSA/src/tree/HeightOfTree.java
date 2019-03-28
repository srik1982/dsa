package tree;

import queue.Queue;

public class HeightOfTree {

	public static void main(String[] args) {
		HeightOfTree obj = new HeightOfTree();
		BTNode root = obj.createBinaryTree();
		int ht = obj.heightIterative(root);
		System.out.println("Height = "+ht);

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
	
	public int height(BTNode node) {
		if(node == null) {
			return 0;
		}else {
			int left = height(node.left);
			int right = height(node.right);
			return 1 + Math.max(left, right);
		}
	}
	
	public int heightIterative(BTNode root) {
		Queue<BTNode> q = new Queue<BTNode>();
		q.enqueue(root);
		q.enqueue(null);
		int height = 0;
		boolean empty = false;
		while(!q.isEmpty()) {			
			empty = true;
			while(q.front()!=null) {
				BTNode node = q.dequeue();	
				if(node.left!=null)
					q.enqueue(node.left);
				
				if(node.right!=null)
					q.enqueue(node.right);	
				
				empty = false;
			}
			if(empty)break;		
			
			height++;
			q.dequeue();
			q.enqueue(null);
		}
	
		return height;
	}

}
