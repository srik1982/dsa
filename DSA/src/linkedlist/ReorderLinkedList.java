package linkedlist;

/*
 * Linked List : L1 -> L2 -> .. Ln-1 -> Ln
 * After Reorder : L1 -> Ln -> L2 -> Ln-1 ...
 */
public class ReorderLinkedList {

	public static void main(String[] args) {
		SinglyLinkedList sll = new SinglyLinkedList();
		sll.add(1);
		sll.add(2);
		sll.add(3);
		sll.add(4);
		sll.add(5);
		sll.add(6);
		sll.add(7);
		sll.add(8);
		sll.add(9);
		sll.add(10);
		sll.add(11);
		sll.add(12);
		sll.add(13);
		sll.add(14);
		sll.add(15);
		sll.add(16);
		sll.add(17);
		sll.add(18);
		sll.add(19);
		sll.add(20);
		sll.add(21);
		sll.add(22);
		sll.add(23);
		sll.add(24);
		
		ReorderLinkedList obj = new ReorderLinkedList();
		sll.traverse();
		obj.reorder(sll.getHead());
		sll.traverse();
	}
	
	public void reorder(SLLNode head) {
		SLLNode midPoint = findMiddleNode(head);
		ReverseEveryKElements reverser = new ReverseEveryKElements();
		SLLNode head2 = reverser.reverse(midPoint.next);
		
		SLLNode ptr1 = head.next, ptr2 = head2;
		SLLNode merged = head;
		boolean first = false;
		while(ptr1!=midPoint.next && ptr2!=null) {
			if(!first) {
				merged.next = ptr2;
				ptr2 = ptr2.next;
			}else {
				merged.next = ptr1;
				ptr1 = ptr1.next;
			}
			merged = merged.next;
			first = !first;
		}
		while(ptr1 != midPoint.next) {
			merged.next = ptr1;
			ptr1 = ptr1.next;
			merged = merged.next;
		}
		while(ptr2!= null) {
			merged.next = ptr2;
			ptr2 = ptr2.next;
			merged = merged.next;
		}
	}

	private SLLNode findMiddleNode(SLLNode head) {
		SLLNode slowPtr = null;
		SLLNode fastPtr = head;
		while(fastPtr!=null && fastPtr.next!=null) {
			if(slowPtr==null) {
				slowPtr = head;
			}else {
				slowPtr = slowPtr.next;
			}
			fastPtr = fastPtr.next.next;
		}
		return slowPtr;
	}
	
}
