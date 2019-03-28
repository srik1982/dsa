package tree;

public class StructurallySame {

	public static void main(String[] args) {
		StructurallySame obj = new StructurallySame();
		BTNode root1 = obj.createBinaryTree();
		BTNode root2 = obj.createBinaryTree2();
		boolean same = obj.isSame(root1, root2);
		System.out.println("is same = "+same);
	}
	public BTNode createBinaryTree() {
		BTNode root = new BTNode(1);
		root.left = new BTNode(2);
		root.right = new BTNode(3);
		root.left.left = new BTNode(4);
		root.left.right = new BTNode(5);
//		root.left.right.right = new BTNode(9);
		root.right.left = new BTNode(6);
		root.right.right = new BTNode(7);
//		root.right.right.left = new BTNode(8);
		
		
		return root;
	}
	public BTNode createBinaryTree2() {
		BTNode root = new BTNode(1);
		root.left = new BTNode(2);
//		root.right = new BTNode(3);
		root.left.left = new BTNode(4);
		root.left.right = new BTNode(5);
//		root.left.right.right = new BTNode(9);
//		root.right.left = new BTNode(6);
//		root.right.right = new BTNode(7);
//		root.right.right.left = new BTNode(8);
		return root;
	}
	
	public boolean isSame(BTNode root1, BTNode root2) {
		if(root1==null && root2==null) {
			return true;
		}else if(root1==null || root2==null) {
			return false;
		}else if(isLeaf(root1) && isLeaf(root2)) {
			return true;
		}else if(isLeaf(root1) || isLeaf(root2)) {
			return false;
		}else {
			boolean left = isSame(root1.left,root2.left);
			boolean right = isSame(root1.right,root2.right);
			return left && right;
		}
	}
	private boolean isLeaf(BTNode root) {		
		return root!=null && root.left==null && root.right==null;
	}
	

}
