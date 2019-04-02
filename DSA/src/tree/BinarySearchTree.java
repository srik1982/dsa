package tree;

public class BinarySearchTree {

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
		
		System.out.println(bst.find(9));
		bst.delete(10);
		bst.traverse();
	}
	
	private BTNode root;
	
	BTNode getRoot() {
		return root;
	}
	
	public void insert(int data) {
		root = insert(root,data);
		
	}

	public BTNode insert(BTNode node, int data) {
		if(node == null) {
			node = new BTNode(data);			
		}else if(data > node.data){
			node.right = insert(node.right,data);
		}else {
			node.left = insert(node.left,data);
		}
		return node;
	}
	
	public boolean find(int data) {
		return find(root,data);
	}

	private boolean find(BTNode node, int data) {
		if(node == null) {
			return false;
		}
		
		if(node.data == data) {
			return true;
		}else if(data > node.data) {
			return find(node.right,data);
		}else {
			return find(node.left,data);
		}
	}
	
	public void traverse() {
		traverse(root);
	}

	private void traverse(BTNode node) {
		if(node!=null) {
			traverse(node.left);
			System.out.print(node.data+" ");
			traverse(node.right);
		}		
	}
	
	public void delete(int data) {
		delete(root,data);
	}

	private BTNode delete(BTNode node, int data) {
		if(node==null) {
			System.out.println("Element not found");
			return null;
		}
		
		if(data > node.data) {
			node.right = delete(node.right,data);
			return node;
		}else if(data < node.data) {
			node.left = delete(node.left,data);
			return node;
		}else {
			if(TreeUtil.isLeaf(node)) {
				return null;
			}else if(node.right == null) {
				return node.left;
			}else if(node.left == null) {
				return node.right;
			}else {
				BTNode largestInLeftSubTree = getLargestNode(node.left);
				node.data = largestInLeftSubTree.data;
				node.left = delete(node.left,largestInLeftSubTree.data);
				return node;
			}
		}
	}

	private BTNode getLargestNode(BTNode left) {
		BTNode node = left;
		while(!TreeUtil.isLeaf(node)) {
			node = node.right;
		}
		return node;
	}

}
