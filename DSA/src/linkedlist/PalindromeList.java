package linkedlist;

/*
 * Check if the values in the Singly linked list form a palindrome
 * Approach 1: Time - O(n) , Space O(n)
 * Add each node to a stack by traversing SLL
 * Traverse again and pop an element from stack each time and look for match
 * 
 * Approach 2: Time O(n) , Space O(1)
 * Go to the midpoint of the list.
 * reverse the second half
 * check if first half and second half are same by traversing a second time.
 */
public class PalindromeList {

	public static void main(String[] args) {
		SinglyLinkedList sll = new SinglyLinkedList();
		sll.add(1);
		sll.add(3);
		sll.add(5);
		sll.add(7);
		sll.add(2);
		sll.add(101);
		sll.add(2);
		sll.add(7);
		sll.add(5);
		sll.add(3);
		sll.add(1);
		
		PalindromeList obj = new PalindromeList();
		System.out.println(obj.isPalindrome(sll.getHead()));
	}
	
	public boolean isPalindrome(SLLNode head) {
		SLLNode middleNode = findMiddleNode(head);		
		SLLNode secondHalfStart = reverseIterative(middleNode.next);
		
		SLLNode temp1 = head, temp2 = secondHalfStart;
		boolean palindrome = true;
		while(temp1 != middleNode && temp2!=null) {
			if(temp1.data == temp2.data) {
				temp1 = temp1.next;
				temp2 = temp2.next;
			}else {
				palindrome = false;
				break;
			}
		}
		
		if(palindrome && (temp1 == middleNode && temp2!=null)) {
			palindrome = temp1.data == temp2.data;
		}else if(palindrome && (temp1 != middleNode && temp2==null)) {
			palindrome = false;
		}
		return palindrome;
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
}
