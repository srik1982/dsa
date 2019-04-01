package tree;

public class CreateTreeFromPreorder {

	public static void main(String[] args) {
		CreateTreeFromPreorder obj = new CreateTreeFromPreorder();
		BTNode root = obj.createTree("IILILLILL");
		BinaryTreeTraversals traverse = new BinaryTreeTraversals();
		traverse.levelOrder(root);

	}
	
	int index = 0;
	
	public BTNode createTree(String preOrder) {
		if(index>=preOrder.length())return null;
		
		char ch = preOrder.charAt(index);
		if(ch == 'L') {
			return new BTNode(index+1);
		}
		
		BTNode node = new BTNode(index+1);
		index++;
		node.left = createTree(preOrder);
		index++;
		node.right = createTree(preOrder);
		return node;
	}
}
