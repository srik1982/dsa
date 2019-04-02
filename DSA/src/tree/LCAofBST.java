package tree;

public class LCAofBST {

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
		
		LCAofBST obj = new LCAofBST();
		BTNode node = obj.getLCA(bst.getRoot(), 4, 2);
		System.out.println("LCA = "+node.data);
	}
	
	public BTNode getLCA(BTNode root, int data1, int data2) {
		if(root == null)return null;
		
		int d = root.data;
		if(d > data1 && d < data2 || d > data2 && d < data1) {
			return root;
		}
		
		if(d > data1 && d > data2) {
			return getLCA(root.left,data1,data2);
		}else if(d < data1 && d < data2){
			return getLCA(root.right,data1,data2);
		}else {
			return root;
		}
	}
	

}
