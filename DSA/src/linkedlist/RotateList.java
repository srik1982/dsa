package linkedlist;

public class RotateList {

	public static void main(String[] args) {
		SinglyLinkedList sll = new SinglyLinkedList();
		sll.add(1);
		sll.add(3);
		sll.add(5);
		sll.add(7);
		sll.add(2);
		sll.add(4);
		sll.add(6);
		sll.add(8);
		
		RotateList obj = new RotateList();
		sll.traverse();
		SLLNode newHead = obj.rotate(sll.getHead(), 5);
		sll.setList(newHead);
		sll.traverse();
	}
	
	public SLLNode rotate(SLLNode head, int k) {
		SLLNode current = head;
		int counter = 1;
		while(counter!=k && current!=null) {
			counter++;
			current = current.next;
		}
		SLLNode newHead = current.next;		
		current.next = null;
		current = newHead;
		while(current.next!=null) {
			current = current.next;
		}
		current.next = head;
		return newHead;
	}

}
