package linkedlist;

/*
 * This is really good question.
 */
public class FractionalNode {

	public static void main(String[] args) {
		SinglyLinkedList sll = new SinglyLinkedList();
		sll.add(1);
		sll.add(-1);
		sll.add(0);
		sll.add(3);
		sll.add(7);
		sll.add(13);
		sll.add(19);
		sll.add(23);
		sll.add(31);
		sll.add(29);
		sll.add(53);
		sll.add(67);
		sll.add(37);
		sll.add(47);
		sll.add(71);
		sll.add(79);
		sll.add(91);
		sll.add(87);
		sll.add(113);
		sll.add(143);
		sll.add(83);
		sll.add(71);
		sll.add(56);
		sll.add(41);
		
		FractionalNode obj = new FractionalNode();
		System.out.println(obj.findFractionalNode(sll.getHead(), 6).data);
	}
	
	public SLLNode findFractionalNode(SLLNode head, int k) {
		SLLNode fractionalNode = null;
		int i=1;
		SLLNode current = head;
		while(current!=null) {
			if(i%k == 0) {
				if(fractionalNode == null) {
					fractionalNode = head;
				}else {
					fractionalNode = fractionalNode.next;
				}
			}
			current = current.next;
			i++;
		}
		return fractionalNode;
	}

}
