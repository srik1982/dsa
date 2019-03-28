package tree;

public class FindMaxSumPath {

	public static void main(String[] args) {
		FindMaxSumPath obj = new FindMaxSumPath();
		BTNode root = TreeUtil.createBinaryTree();
		int maxPath = obj.findMaxSum(root);
		System.out.println("MaxPathSum = "+maxPath);
	}
	public BTNode createBinaryTree() {
		BTNode root = new BTNode(10);
		root.left = new BTNode(2);
		root.right = new BTNode(13);
		root.left.left = new BTNode(10);
		root.left.right = new BTNode(1);
		root.right.right = new BTNode(-25);
		root.right.right.left = new BTNode(5);
		root.right.right.right = new BTNode(6);
		
		return root;
	}
	int localMax = Integer.MIN_VALUE;
	public int findMaxSum(BTNode root) {
		
		int maxPathSum= findMaxSumPath(root);
		return Math.max(localMax, maxPathSum);
	}

	private int findMaxSumPath(BTNode node) {
		if(node==null)return 0;
		if(TreeUtil.isLeaf(node))return node.data;
		
		int l = findMaxSumPath(node.left);
		int r = findMaxSumPath(node.right);
		int singlePathMax = node.data + Math.max(l,r);
		localMax = Math.max(localMax, Math.max(singlePathMax, l+r+node.data));
		return singlePathMax;
	}
	
	
}
