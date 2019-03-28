package tree;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import queue.Queue;
import stack.Stack;

public class BinaryTreeTraversals {

	public static void main(String args[]) {
		BinaryTreeTraversals o = new BinaryTreeTraversals();
		BTNode root = o.createBinaryTree();
		o.levelOrder(root);
//		System.out.println();
//		o.preOrderIterative(root);
	}
	
	public BTNode createBinaryTree() {
		BTNode root = new BTNode(1);
		root.left = new BTNode(2);
		root.right = new BTNode(3);
		root.left.left = new BTNode(4);
		root.left.right = new BTNode(5);
		root.right.left = new BTNode(6);
		root.right.right = new BTNode(7);
		
		return root;
	}
	
	public void preOrder(BTNode node) {
		if(node!=null) {
			System.out.print(node.data+" ");
			
			if(node.left !=null)
				preOrder(node.left);
			
			if(node.right!= null)
				preOrder(node.right);
		}
	}
	
	public void preOrderIterative(BTNode node) {
		if(node == null)return;
		
		Stack<BTNode> stack = new Stack<BTNode>();
		stack.push(node);
		
		while(!stack.isEmpty()) {
			BTNode _node = stack.pop();			
			System.out.print(_node.data+" ");
			if(_node.right!=null)
				stack.push(_node.right);
			
			if(_node.left!=null)
				stack.push(_node.left);
			
		}
	}
	
	public void inOrder(BTNode node) {
		if(node!=null) {
			if(node.left !=null)
				inOrder(node.left);
			
			System.out.print(node.data+" ");
			
			if(node.right!= null)
				inOrder(node.right);			
		}
	}
	
	public void inOrderIterative(BTNode root) {
		BTNode node = root;
		Stack<BTNode> stack = new Stack<BTNode>();
		while(node!=null || !stack.isEmpty()) {
			while(node!=null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			System.out.print(node.data+" ");
			node = node.right;
		}
	}
	
	public void postOrder(BTNode node) {
		if(node!=null) {
			if(node.left !=null)
				postOrder(node.left);
			
			if(node.right!= null)
				postOrder(node.right);			
			
			System.out.print(node.data+" ");
		}
	}
	/*
	 * O(n) time
	 * 2 * O(n) = O(n) for space
	 */
	public void postOrderIterative(BTNode node) {
		Stack<BTNode> s1 = new Stack<BTNode>();
		Stack<BTNode> s2 = new Stack<BTNode>();
		s1.push(node);
		while(!s1.isEmpty()) {
			BTNode n = s1.pop();
			s2.push(n);
			if(n.left!=null) {
				s1.push(n.left);
			}
			if(n.right!=null) {
				s1.push(n.right);
			}
		}
		while(!s2.isEmpty())
		System.out.print(s2.pop()+" ");
	}
	
	/*
	 * O(n) time
	 * 2 * O(n) = O(n) for space
	 * 
	 * There is also a O(n) space solution, but a little difficult.
	 */
	public void postOrderIterative2(BTNode root) {
		Stack<BTNode> s = new Stack<BTNode>();
		Set<BTNode> visited = new HashSet<BTNode>();
		s.push(root);
		while(!s.isEmpty()) {//we shall come back to the exit condition shortly
			BTNode node = s.pop();
			if(node.left != null && !visited.contains(node.left)) {
				while(node!=null) {
					s.push(node);
					node = node.left;
				}
				BTNode n = s.pop();
				System.out.print(n+" ");
				visited.add(n);
			}else if(node.right!=null && !visited.contains(node.right)) {
				s.push(node);
				s.push(node.right);
			}else if(!visited.contains(node)){
				System.out.print(node+" ");
				visited.add(node);
			}
		}
	}
	
	public void levelOrder(BTNode root) {
		Queue<BTNode> q = new Queue<BTNode>();
		q.enqueue(root);
//		q.enqueue(null);
//		boolean empty = false;
		while(!q.isEmpty()) {	
//			empty = true;
//			while(q.front()!=null) {
				BTNode node = q.dequeue();
				System.out.print(node+" ");
				if(node.left!=null)
					q.enqueue(node.left);
				
				if(node.right!=null)
					q.enqueue(node.right);	
//				empty = false;
//			}
//			if(empty)break;
			
//			q.dequeue();
//			q.enqueue(null);
		}
	}
	
	public void zigZag(BTNode root) {
		Stack<BTNode> L2R = new Stack<BTNode>();
		Stack<BTNode> R2L = new Stack<BTNode>();
		L2R.push(root);
		boolean left2Right = true;
		while(left2Right && !L2R.isEmpty() || !left2Right && !R2L.isEmpty()) {
			if(left2Right) {
				while(!L2R.isEmpty()) {
					BTNode node = L2R.pop();
					System.out.print(node+" ");
					
					if(node.left!=null)
						R2L.push(node.left);
					
					if(node.right!=null)
						R2L.push(node.right);					
				}
				left2Right = !left2Right;
			}else {
				while(!R2L.isEmpty()) {
					BTNode node = R2L.pop();
					System.out.print(node+" ");
					
					if(node.left!=null)
						L2R.push(node.right);
					
					if(node.right!=null)
						L2R.push(node.left);
				}
				left2Right = !left2Right;
			}
		}
	}
	
}
