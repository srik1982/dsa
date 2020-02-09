package priorityqueue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MaxInSlidingWindow {

	public static void main(String[] args) {
		int [] arr = new int[] {1,3,-1,-3,5,3,6,7};
		MaxInSlidingWindow obj = new MaxInSlidingWindow();
		Queue<Integer> results = obj.getMaxInWindow(arr, 3);
		while(!results.isEmpty()) {
			System.out.println(results.poll());
		}
	}

	Deque<Node> window = new LinkedList<Node>();
	
	/*
	 * Time Complexity:
	 * Window size is a constant and usually a small number.
	 * Time taken is O( n * w) but since w is a constant, we can say O(n)
	 */
	public Queue<Integer> getMaxInWindow(int [] arr, int w){
		
		Queue<Integer> results = new LinkedList<Integer>();
		Arrays.stream(arr).forEach(item ->{
			Node node = new Node(item, w);
			
			if(!window.isEmpty()) {
				for(int i = 0; i < window.size() ; i++) {
					Node n = window.poll();
					n.comparisionsLeft--;
					if(n.comparisionsLeft > 0) {
						window.offer(n);
					}
				}
			}
			
			 
			while(!window.isEmpty()) {
				Node n = window.peekLast();
				if(n.value > node.value) {
					break;
				}else {
					window.pollLast();
				}				
			}
			
			window.offerLast(node);
			results.offer(window.peekFirst().value);			
		});
		
		return results;
	}

	class Node {
		private int value;
		private int comparisionsLeft;

		public Node(int value, int comparisionsLeft) {
			super();
			this.value = value;
			this.comparisionsLeft = comparisionsLeft;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getComparisionsLeft() {
			return comparisionsLeft;
		}

		public void setComparisionsLeft(int comparisionsLeft) {
			this.comparisionsLeft = comparisionsLeft;
		}
	}

}
