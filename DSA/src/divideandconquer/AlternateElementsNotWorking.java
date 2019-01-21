package divideandconquer;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Problem description: Given an array of 2n integers
 *  in the following format a1 a2 a3 ...an b1 b2 b3...bn. 
 *  Shuffle the array to a1 b1 a2 b2 a3 b3 ... an bn without any extra memory  
 * @author srikanthrao
 *
 */
public class AlternateElementsNotWorking {

	public static void main(String[] args) {
		AlternateElementsNotWorking.shuffle3();
		
	}
	/**
	 * This doesn't work. Solution not provided in book. Let me try linear
	 * @param a
	 * @param firstHalfStart
	 * @param firstHalfEnd
	 * @param secondHalfStart
	 * @param secondHalfEnd
	 */
	public static void shuffle(String []a, int firstHalfStart, int firstHalfEnd, int secondHalfStart, int secondHalfEnd) {
		if(firstHalfStart < firstHalfEnd && secondHalfStart<secondHalfEnd) {
			int firstMid = (firstHalfStart+firstHalfEnd)/2;
			int secondMid = (secondHalfStart+secondHalfEnd)/2;
			
			swap(a, firstMid, firstHalfEnd, secondMid,secondHalfEnd);
			shuffle(a,firstHalfStart,firstMid,secondHalfStart,secondMid);
			shuffle(a,firstMid+1,firstHalfEnd,secondMid+1,secondHalfEnd);
		}
	}
	private static void swap(String[] a, int lowerMid, int mid, int higherMid, int high) {
		for(int i=lowerMid+1, j=higherMid+1;i<=mid && j<=high;i++,j++) {
			swap(a,i,j);
		}
	}
	private static void swap(String []a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
	}
	/**
	 * This produces [a1, b2, a3, b4, a5, b6, a7, b8, b1, a2, b3, a4, b5, a6, b7, a8]
	 * 
	 * [a1,a2,a3,a4,a5,a6,a7,a8,b1,b2,b3,b4,b5,b6,b7,b8]
	 * Not exactly what we want. But lets try again.
	 * a1 should go 0 places to right
	 * b1 should go 7 places to left.
	 * a2 should go 1 place to right
	 * b2 should go 6 places to left
	 * a3 should go 2 places to right
	 * b3 should go 5 places to left
	 * .
	 * .
	 * a8 goes 7 places to right
	 * b8 goes 0 places to left
	 * @param a
	 */
	public static void shuffle(String []a) {
		int half = (a.length + 1)/2;
		for(int i=1;i<half;i = i+2) {
			swap(a,i,i+half);
		}
	}
	
	/** [a1,a2,a3,a4,a5,a6,a7,a8,b1,b2,b3,b4,b5,b6,b7,b8]
			 * Not exactly what we want. But lets try again.
			 * a1 should go 0 places to right
			 * b1 should go 7 places to left.
			 * a2 should go 1 place to right
			 * b2 should go 6 places to left
			 * a3 should go 2 places to right
			 * b3 should go 5 places to left
			 * .
			 * .
			 * a8 goes 7 places to right
			 * b8 goes 0 places to left
			 * 
			 * This also doesn't work. Because when we shift b1 7 places to left, its basically a swap with a2 going in b1's place
			 * 
			 * We can solve this using Linked Lists or shift n/2 elements 
			 * */
	
	public static void shuffle2(String []a) {
		int half = a.length/2 -1; // last index of first half
		int ashift = 0;
		int bShift = half;
		for(int i=0, j=half+1;i<=half && j<a.length;i++,j++) {
			swap(a,i,i+(ashift++));
			swap(a,j,j-(bShift--));
		}
	}
	
	public static void shuffle3() {
		LinkedList<String> list = new LinkedList<>();
		for(int i=1;i<9;i++) {
			list.add("a"+i);
		}
		for(int i=1;i<9;i++) {
			list.add("b"+i);
		}
		int half = list.size()/2 - 1;
		int aShift = 0;
		int bShift = half;
		for(int i=0, j=half+1;i<=half && j<list.size();i++,j++) {
			list.add(i+(aShift++), list.remove(i));
			list.add(j-(bShift--), list.remove(j));
		}
		System.out.println(list);
	}
	
	class Node{
		String data;
		Node next;
	}
	public void shuffle4() {
		Node head = null;
		Node prev = null;
		for(int i=1;i<9;i++) {
			Node newNode = new Node();
			newNode.data = "a"+i;
			newNode.next = null;
			if(prev!=null) {
				prev.next = newNode;
			}
			if(head==null) {
				head = newNode;
			}
			prev = newNode;
		}
		for(int i=1;i<9;i++) {
			Node newNode = new Node();
			newNode.data = "b"+i;
			newNode.next = null;
			if(prev!=null) {
				prev.next = newNode;
			}
			prev = newNode;
		}
	}
	
	

}
