package linkedlist;

public class SinglyLinkedList {

	public static void main(String[] args) {
		SinglyLinkedList sllTest = new SinglyLinkedList();
		sllTest.add(67);
		sllTest.add(12);
		sllTest.add(91);
		sllTest.add(112);
		sllTest.add(39);
		
		sllTest.add(23,2);
		sllTest.traverse();
		System.out.println("Size of list = "+sllTest.size());
		System.out.println("is empty list = "+sllTest.isEmpty());
		
		sllTest.delete(0);
		sllTest.traverse();
	}
	
	private SLLNode head = null;
	private int size=0;
	
	/*
	 * Not a java practice to expose head like this. But need to expose to solve a problem.
	 */
	public SLLNode getHead() {
		return head;
	}
	
	public void setList(SLLNode head) {
		this.head = head;
		int count = 1;
		SLLNode temp = head;
		while(temp!=null) {
			count++;
			temp = temp.next;
		}
		size = count;
	}
	
	/*
	 * Add Element at the end
	 */
	public void add(int data) {
		if(head == null) {
			head = new SLLNode(data);
			size=1;
		}else {
			SLLNode current = head;
			while(current.next != null) {
				current = current.next;
			}
			current.next = new SLLNode(data);
			size++;
		}
	}
	/*
	 * Insert at a index
	 * 0 : start
	 * any other index < length -1 : middle insertion
	 * 
	 */
	public void add(int data, int index) throws IndexOutOfBoundsException {
		if(index<0 || index>size) {
			throw new IndexOutOfBoundsException();
		}else if(head == null && index!=0) {
			throw new IndexOutOfBoundsException();// will be some other exception. For simplicity, lets just throw the same one everywhere.
		}
		
		if(index == 0 && head!=null) {
			SLLNode newHead = new SLLNode(data);
			newHead.next = head;
			head = newHead;
			size++;
		}else if(index == size || index==0) {
			add(data);
		}else {
			SLLNode current = head;
			for(int i=0;i<index-1;i++) { // we are using 0 based index. if supplied index is 5, we go to index 4 and insert next to it.
				current = current.next;
			}
			SLLNode newNode = new SLLNode(data,current.next);
			current.next = newNode;
			size++;
		}
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public int size() {
		return size;
	}
	
	public void delete(int index) {
		if(index<0 || index>size-1) {
			throw new IndexOutOfBoundsException();
		}else if(head == null && index!=0) {
			throw new IndexOutOfBoundsException();// will be some other exception. For simplicity, lets just throw the same one everywhere.
		}
		
		if(index == 0) {//delete head
			SLLNode newHead = head.next;
			head.next = null;
			head = newHead;
			size--;
		}else {
			SLLNode current = head;
			for(int i=0;i<index-1;i++) { 
				current = current.next;
			}
			SLLNode deleteNode = current.next;
			current.next = deleteNode.next;
			deleteNode.next = null;
			size--;
		}
	}
	
	public void traverse() {
		if(head == null) {
			System.out.println("[]");
		}else {
			StringBuilder sb = new StringBuilder("[ ");
			SLLNode current = head;
			while(current!=null) {
				sb.append(current.data).append(" , "); //last element will have extra comma, lets ignore the cosmetics for now
				current = current.next;
			}
			sb.append("]");
			System.out.println(sb.toString());
		}
	}
}
