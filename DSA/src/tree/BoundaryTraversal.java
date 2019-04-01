package tree;

/*
 * Traverse the boundary nodes in anti-clockwise
 */
public class BoundaryTraversal {

	public static void main(String[] args) {
		BoundaryTraversal obj = new BoundaryTraversal();
		BTNode root = obj.constructTree();
		obj.doBoundaryTraversal(root);
	}
	
	public BTNode constructTree() {
		BTNode root = new BTNode(1);
		root.left = new BTNode(2);
		root.right = new BTNode(3);
		
		root.left.left = new BTNode(4);
		root.left.left.right = new BTNode(8);
		root.left.left.right.left = new BTNode(9);
		root.left.left.right.left.left = new BTNode(11);
		root.left.left.right.right = new BTNode(10);
		
		root.left.right = new BTNode(5);
		root.left.right.left = new BTNode(16);
		root.left.right.right = new BTNode(19);
		
		root.right.left = new BTNode(6);
		root.right.left.left = new BTNode(17);
		root.right.left.right = new BTNode(18);
		
		root.right.right = new BTNode(7);
		root.right.right.right = new BTNode(12);
		root.right.right.right.right = new BTNode(13);
		root.right.right.right.right.left = new BTNode(14);
		root.right.right.right.right.right = new BTNode(15);
		
		return root;
	}
	
	public void doBoundaryTraversal(BTNode root) {
		doLeftTraversal(root);
		traverseLeafNodes(root);
		traverseRightNodes(root);
	}

	private void traverseRightNodes(BTNode node) {
		if(node!=null && !TreeUtil.isLeaf(node)) {
			traverseRightNodes(node.right);
			System.out.print(node.data+" ");
		}
		
	}

	private void traverseLeafNodes(BTNode node) {
		if(node!=null && TreeUtil.isLeaf(node)) {
			System.out.print(node.data+" ");
		}else if(node!=null) {
			traverseLeafNodes(node.left);
			traverseLeafNodes(node.right);
		}
		
	}

	private void doLeftTraversal(BTNode node) {
		if(node!=null && !TreeUtil.isLeaf(node)) {
			System.out.print(node.data+" ");
			if(node.left!=null) {
				doLeftTraversal(node.left);
			}else if(node.right!=null) {
				doLeftTraversal(node.right);
			}
		}		
	}

}
