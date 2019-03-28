package tree;

public class NodeCounts {

	public static void main(String[] args) {
		NodeCounts obj = new NodeCounts();
		BTNode root = obj.createBinaryTree();
		int leafCount = obj.getLeafCount(root);
		System.out.println(leafCount);
		System.out.println(obj.getFullNodeCount(root));
		System.out.println(obj.getHalfNodes(root));
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
	
	public int getLeafCount(BTNode node) {
		if(node == null) {
			return 0;
		}else if(node.right == null && node.left == null) {
			return 1;
		}else {
			int left = getLeafCount(node.left);
			int right = getLeafCount(node.right);
			return left + right;
		}
	}
	
	public int getFullNodeCount(BTNode node) {
		if(node == null) {
			return -1;
		}else if(node.right == null && node.left == null) {
			return 0;
		}else {
			int left = getFullNodeCount(node.left);
			int right = getFullNodeCount(node.right);
			if(left>=0 && right>=0){
				return left+right+1;
			} else if(left>=0){
				return left;
			}else if(right>=0){
				return right;
			}else {
				return 0;
			}
		}
	}
	
	public int getHalfNodes(BTNode node) {
		if(node == null) {//null pointers
			return -1;
		}else if(node.right == null && node.left == null) {//leaf node
			return 0;
		}else {
			int left = getHalfNodes(node.left);
			int right = getHalfNodes(node.right);
			if(left>=0 && right==-1 || left==-1 && right>=0){//half node. i.e 1 child
				return 1 + (left>=0 ? left : right);
			} else if(left>=0 && right>=0){//full node, just pass on the sum from children
				return left+right;
			}else { //both left and right == -1 => leaf node
				return 0;
			}
		}
	}

}
