package tree;

/*
 * Not correct implementation. Because diameter doesn't have to pass through the root node.
 * if one child of hte root is null, then the diameter doesn't pass through root.
 * Updated with correct version.
 * O(n2)
 */
public class DiameterOfTheTree {

	public static void main(String[] args) {
		DiameterOfTheTree obj = new DiameterOfTheTree();
		BTNode root = obj.createBinaryTree();
		int d = obj.getDiameter(root);
		System.out.println("Diameter = "+d);
	}
	public BTNode createBinaryTree() {
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
	
	public int getDiameter(BTNode node) {
		if(node == null)return 0;
		//if diameter pass through this node
		int lHeight = height(node.left);
		int rHeight = height(node.right);
		int diameter = lHeight+rHeight+1;
		
		//if diameter doesn't pass through this node
		lHeight = getDiameter(node.left);
		rHeight = getDiameter(node.right);
		//then we don't add lHeight+rHeight+1, just get the max of both
		return Math.max(diameter, Math.max(rHeight, rHeight));
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
}
