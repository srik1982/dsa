package tree;

import queue.Queue;

public class AVLTree {

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		AVLNode root = tree.insert(null, 30);
		root = tree.insert(root, 40);
		root = tree.insert(root, 55);
		tree.printLevelOrder(root);
		root = tree.insert(root, 60);
		root = tree.delete(root,30);
		tree.printLevelOrder(root);
//		root = tree.insert(root, 40);
//		root = tree.insert(root, 35);
//		tree.printLevelOrder(root);
	}
	
	private AVLNode root;
	
	public AVLNode insert(AVLNode node, int data) {
		if(node==null) {
			node = new AVLNode(data);
			node.height = 0;
			return node;
		}
		if(data >= node.data) {
			node.right = insert(node.right,data);
			node.height = node.right.height+1;
		}else {
			node.left = insert(node.left,data);
			node.height = node.left.height+1;
		}
		
		int bf = height(node.left) - height(node.right); // balance factor
		node = balance(node, bf);
		
		return node;
	}
	
	private AVLNode balance(AVLNode node, int bf) {
		if(bf>1) {
			if(height(node.left.left) > height(node.left.right)) {//LL rotation
				System.out.println("Doing LL rotation at node "+node.data);
				//node.left.right will have to be reassigned to new parent ( if it is exists)
				AVLNode adoptee = node.left.right;
				node.left.right = node;
				AVLNode root = node.left;
				node.left = adoptee;
				node = root;
				//now recompute the new height of node.right and node
				node.right.height = height(node.right.left) > height(node.right.right) ? height(node.right.left) : height(node.right.right);
				node.height = height(node.left) > height(node.right) ? height(node.left) : height(node.right);
			}else {//LR rotation
				System.out.println("Doing LR rotation at node "+node.data);
				//node.left.right might have chidren which have to be reassigned.
				AVLNode leftAdoptee = node.left.right.left;
				AVLNode rightAdoptee = node.left.right.right;
				AVLNode newRoot = node.left.right;
				newRoot.left = node.left;
				newRoot.right = node;
				//left adoptee/orphan is assigned on the left subtree and right orphan on the right subtree
				newRoot.left.right = leftAdoptee;
				newRoot.right.left = rightAdoptee;
				node = newRoot;
				//now recompute the heights. Heights of node.left and node.right might have changed after the rotation.
				node.left.height = height(node.left.left) > height(node.left.right) ? height(node.left.left) : height(node.left.right);
				node.right.height = height(node.right.left) > height(node.right.right) ? height(node.right.left) : height(node.right.right);
				node.height = height(node.left) > height(node.right) ? height(node.left) : height(node.right);
			}
		}else if(bf<-1) {
			if(height(node.right.right) > height(node.right.left)) {//RR rotation
				System.out.println("Doing RR rotation at node "+node.data);
				AVLNode adoptee = node.right.left;
				node.right.left = node;
				AVLNode root = node.right;
				node.right = adoptee;
				node = root;
				//now compute the new height of node.left and node
				node.left.height = height(node.left.left) > height(node.left.right) ? height(node.left.left) : height(node.left.right);
				node.height = height(node.left) > height(node.right) ? height(node.left) : height(node.right);
			}else {//RL rotation
				//children if any of node.right.left have to be reassigned
				System.out.println("Doing RL rotation at node "+node.data);
				AVLNode leftAdoptee = node.right.left.left;
				AVLNode rightAdoptee = node.right.left.right;
				AVLNode newRoot = node.right.left;
				newRoot.right = node.right;
				newRoot.left = node;
				//reassign the adoptees
				newRoot.left.right = leftAdoptee;
				newRoot.right.left = rightAdoptee;
				node = newRoot;
				//now recompute the heights
				node.left.height = height(node.left.left) > height(node.left.right) ? height(node.left.left) : height(node.left.right);
				node.right.height = height(node.right.left) > height(node.right.right) ? height(node.right.left) : height(node.right.right);
				node.height = height(node.left) > height(node.right) ? height(node.left) : height(node.right);
			}
		}
		return node;
	}
	/*
	 * we are computing the height of the parent of this node.
	 * So we add +1
	 */
	private int height(AVLNode node) {
		if(node!=null) {
			return node.height+1;
		}
		return 0;
	}
	
	public AVLNode delete(AVLNode node, int data) {
		if(node == null) {
			System.out.println("nothing to delete or data not found");
			return null;
		}
				
		if(node.data == data) {
			if(TreeUtil.isLeaf(node)) {
				//set child to null in parent
				return null;
			}else if(node.left == null) {
				return node.right;
			}else if(node.right == null) {
				return node.left;
			}else {
				AVLNode largestNodeInLeft = getLargestNode(node.left);
				node.data = largestNodeInLeft.data;
				node.left = delete(node.left,node.data);
				return node;
			}
		}
		
		if(data > node.data) {
			node.right = delete(node.right,data);
		}else {
			node.left = delete(node.left,data);
		}
		
		
		int bf = height(node.left) - height(node.right);
		node = balance(node,bf);
		
		
		return node;
	}
	private AVLNode getLargestNode(AVLNode left) {
		AVLNode node = left;
		while(!TreeUtil.isLeaf(node)) {
			node = node.right;
		}
		return node;
	}
	
	public void printLevelOrder(AVLNode root) {
		Queue<AVLNode> q = new Queue<AVLNode>();
		q.enqueue(root);
		while(!q.isEmpty()) {
			AVLNode node = q.dequeue();
			System.out.print(node.data+" ");
			if(node.left!=null)
				q.enqueue(node.left);
			if(node.right!=null)
				q.enqueue(node.right);
		}
	}
}
