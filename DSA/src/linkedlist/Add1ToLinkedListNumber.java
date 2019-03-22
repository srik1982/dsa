package linkedlist;

/*
 * Each NOde represents a single digit of a number. the tail represents the one place
 * Head represents the highest place.
 * Add 1 and readjust. Do we ned to add
 */
public class Add1ToLinkedListNumber {
	
	public static void main(String args[]) {
		SinglyLinkedList sll = new SinglyLinkedList();
		sll.add(9);
		sll.add(9);
		sll.add(9);
		sll.add(9);
		
		Add1ToLinkedListNumber obj = new Add1ToLinkedListNumber();
		obj.addOneToList(sll);
		sll.traverse();
	}
	
	public void addOneToList(SinglyLinkedList list) {
		int retVal = addOne(list.getHead());
		if(retVal == 1) {
			list.add(1, 0);
		}
		return ;
	}
	
	/*
	 * Instead of taking list, we should modify it to take the SinglyLinkedList and the index
	 * We should use the existing methods in SinglyLinkedList
	 */
	public int addOne(SLLNode current) {
		if(current == null) {//do nothing if supplied with null list
			return -1;
		}else if(current.next == null) {// this is the last
			int data = current.data;
			data = data +1;
			if(data > 9) {
				current.data = data % 10;
				return 1;
			}else {
				current.data = data;
				return 0;
			}
		}else {
			int retVal = addOne(current.next);
			int data = current.data;
			data = data + retVal;
			if(data > 9) {
				current.data = data % 10;
				return 1;
			}else {
				current.data = data;
				return 0;
			}
		}
	}
}
