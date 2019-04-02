package tree;

public class FloorAndCeil {

	public static void main(String[] args) {
		BTNode root = TreeUtil.createBST();
		BinaryTreeTraversals traversals = new BinaryTreeTraversals();
		traversals.inOrder(root);
		
		FloorAndCeil obj = new FloorAndCeil();
		FandL fl = obj.new FandL();
		obj.getFloorAndCeil(root, 16, fl);
		System.out.println(fl);
	}
	
	class FandL{
		int floor = Integer.MAX_VALUE;
		int ceil = Integer.MIN_VALUE;
		
		public String toString() {
			return "[ "+floor+" -> "+ceil+" ]";
		}
	}

	void getFloorAndCeil(BTNode root, int key, FandL fl){
		if(root!=null){
			if(key < root.data){
				fl.ceil = root.data;
				getFloorAndCeil(root.left,key,fl);
			}else if(key > root.data){
				fl.floor = root.data;
				getFloorAndCeil(root.right,key,fl);
			}else{
				return;
			}
		}
	}
}
