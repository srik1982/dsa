package linkedlist;

public class ReverseEveryKElements {

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
		sll.add(9);
		sll.add(11);
		sll.add(13);
		
		sll.traverse();
		ReverseEveryKElements obj = new ReverseEveryKElements();
//		SLLNode head = obj.reverseIterative(sll.getHead());
//		sll.setList(head);
//		sll.traverse();
		
		SLLNode head = obj.reverseKNodesIterative(sll.getHead(), 4);
		sll.setList(head);
		sll.traverse();
	}
	
	public SLLNode reverse(SLLNode head) {
		return reverse(null,head);
	}

	private SLLNode reverse(SLLNode prev, SLLNode current) {
		if(current == null) {
			return prev;
		}else if(current.next == null){
			current.next = prev;
			return current;
		}else {
			SLLNode head = reverse(current,current.next);
			current.next = prev;
			return head;
		}
	}
	
	public SLLNode reverseIterative(SLLNode head) {
		SLLNode prev = null;
		SLLNode curr = head;
		SLLNode next = head.next;
		
		while(next!=null) {
			curr.next = prev;
			
			prev = curr;
			curr = next;
			next = next.next;
		}
		curr.next = prev;
		return curr;
	}
	
	public SLLNode reverseIterative(SLLNode head, SLLNode targetNode) {
		SLLNode prev = null;
		SLLNode curr = head;
		SLLNode next = head.next;
		
		while(curr!= targetNode && next!=null) {
			curr.next = prev;
			
			prev = curr;
			curr = next;
			next = next.next;
		}
		if(curr == targetNode) {
			curr.next = prev;
		}
		if(next==null) {//target node comes after the end?
			throw new IllegalStateException();
		}
		return curr;
	}
	
	public SLLNode reverseKNodesIterative(SLLNode head, int k) {
		SLLNode start = head;
		SLLNode end = head;
		SLLNode newHead = null;
		SLLNode groupEnd = null;
		int index = 1;
		while(end!=null) {
			end = end.next;
			index++;
			if(end == null){
				SLLNode node = reverseIterative(start);
				if(newHead == null) {
					newHead = node;
				}else {
					groupEnd.next = node;
				}
				break;
			}else if(index % k == 0) {
				SLLNode tempNode = end.next;
				SLLNode node = reverseIterative(start, end);				
				if(newHead == null) {
					newHead = node;
				}else {
					groupEnd.next = node;
				}
				groupEnd = start;
				start = tempNode;
				end = tempNode;
				index++;
			} 
		}
		return newHead;
	}
	/*******************************************************************************************************************/
	/********************************The below ones don't work**********************************************************/
	/*******************************************************************************************************************/
	/*
	 * Update - doesn't work for all cases
	 * O(n * |n/k|) - not good. but atleast i didn't copy this solution.
	 * Now lets find out the optimal solution. 
	 */
	public SLLNode reverseKNodes(SLLNode head, int k) {
		int counter = 1;
		SLLNode targetNode = head;
		SLLNode startNode = head;
		SLLNode newHead = null;
		while(targetNode!=null) {
			targetNode = targetNode.next;
			counter++;
			if(counter %k == 0 || targetNode.next == null) {
				SLLNode nextBlock = targetNode.next;
				SLLNode node = reverseKNodes(null,startNode, targetNode);
				if(startNode !=null) {
					startNode.next = node;
				}
				if(newHead == null) {
					newHead = node;
				}
				startNode = nextBlock;
				targetNode = startNode;
				counter = 1;
			}
		}
		return newHead;
	}

	private SLLNode reverseKNodes(SLLNode prev, SLLNode current, SLLNode targetNode) {
		if(current == null) {
			return prev;
		}else if(current == targetNode){
			current.next = prev;
			return current;
		}else {
			SLLNode head = reverseKNodes(current,current.next,targetNode);
			current.next = prev;
			return head;
		}
	}
	
	public SLLNode reverseKNodes2(SLLNode head, int k) {
		return reverseKNodes(head,null,head,1,k);
	}
	
	/*
	 * O(n)
	 */
	public SLLNode reverseKNodes(SLLNode firstNode, SLLNode prev, SLLNode current, int counter, int k) {
		if(current == null) {
			return prev;
		}else if(counter % k ==0) {
			SLLNode nextRoot = current.next!=null ? reverseKNodes(current.next, null, current.next, counter+1,k) : null;
			current.next = prev;
			firstNode.next = nextRoot;
			return current;
		}else {
			SLLNode nextRoot = reverseKNodes(firstNode,current,current.next,counter+1,k);
			if(prev!=null) {
				current.next = prev;
			}
			return nextRoot;
		}
	}

}
