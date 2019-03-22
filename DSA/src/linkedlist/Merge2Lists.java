package linkedlist;

/*
 * Merge 2 sorted lists. THe resultant list should be sorted.
 */
public class Merge2Lists {

	public static void main(String[] args) {
		SinglyLinkedList sll1 = new SinglyLinkedList();
		sll1.add(1);
		sll1.add(3);
		sll1.add(5);
		sll1.add(7);
		
		SinglyLinkedList sll2 = new SinglyLinkedList();
		sll2.add(2);
		sll2.add(4);
		sll2.add(6);
		sll2.add(8);
		
		Merge2Lists obj = new Merge2Lists();
		SLLNode head = obj.mergeIterative(sll1.getHead(), sll2.getHead());
		SinglyLinkedList sll3 = new SinglyLinkedList();
		sll3.setList(head);
		sll3.traverse();
	}
	
	public SLLNode merge(SLLNode head1, SLLNode head2) {
		if(head1==null && head2==null)return null;
		if(head1==null && head2!=null)return head2;
		if(head1!=null && head2==null)return head1;
		
		SLLNode node = null;
		if(head1.data <= head2.data) {
			node = head1;
			node.next = merge(head1.next,head2);
		}else {
			node = head2;
			node.next = merge(head1,head2.next);
		}
		return node;
	}
	
	public SLLNode mergeIterative(SLLNode head1, SLLNode head2) {
		if(head1==null && head2==null)return null;
		if(head1==null && head2!=null)return head2;
		if(head1!=null && head2==null)return head1;
		
		SLLNode merged = null, head = null;;
		if(head1.data <= head2.data) {
			merged = head1;
			head1 = head1.next;
		}else {
			merged = head2;
			head2 = head2.next;
		}
		head = merged;
		
		while(head1!=null && head2!=null) {
			if(head1.data <=head2.data) {
				merged.next = head1;
				head1 = head1.next;
			}else {
				merged.next = head2;
				head2 = head2.next;
			}
			merged = merged.next;
		}
		
		while(head1!=null) {
			merged.next = head1;
			head1 = head1.next;
		}
		while(head2!=null) {
			merged.next = head2;
			head2 = head2.next;
		}
		return head;
	}
}
