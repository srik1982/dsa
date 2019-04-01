package tree;

public class LCA {

	public static void main(String[] args) {
		BTNode root = TreeUtil.createBinaryTree();
		LCA obj = new LCA();
		System.out.println("LCA = "+obj.lca(root, 9, 8));

	}
	
	public BTNode lca(BTNode node, int data1, int data2) {
		if(node == null) return null;
		
		if(node.data == data1 || node.data == data2) return node;
		
		BTNode left = lca(node.left,data1, data2);
		BTNode right = lca(node.right,data1,data2);
		
		if(left!=null && right!=null) {
			return node;
		}else if(left!=null) {
			return left;
		}else if(right!=null) {
			return right;
		}else {
			return null;
		}
		
	}
}
