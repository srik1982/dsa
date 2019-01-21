package sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RadixSort {

	public static void main(String[] args) {
		int a[] = new int[] {12,33,567,34,89,123,87,60,45,91,196};
		RadixSort sorter = new RadixSort();
		
		List<RadixNode> sortedList = sorter.sort(a);
		System.out.println(sortedList);

	}
	
	class RadixNode{
		int number;
		int quotient;
		
		RadixNode(int number, int quotient){
			this.number = number;
			this.quotient = quotient;
		}
		
		public String toString() {
			return String.valueOf(number);
		}
	}
	
	/**
	 * https://www.youtube.com/watch?v=YXFI4osELGU
	 * 
	 * @param a
	 * @return
	 */
	public List<RadixNode> sort(int []a) {
		//find out the max number of passes
		int passCt = 0;
		for(int x : a) { // O(n)
			int length = (int) (Math.log10(x)+1);
			passCt = Math.max(passCt, length);
		}
		
		//initialize the buckets -- radix is 10 i.e., modules 10, so 10 buckets
		List<Queue<RadixNode>> buckets = new ArrayList<>();
		for(int i=0;i<10;i++) {// since its modulues 10, we have 10 buckets
			Queue<RadixNode> q = new LinkedList<>();
			buckets.add(q);
		}
		
		//create a aux array because we need to store the results of previous pass as well as original numbers
		// that's why we came up with RadixNode concept.
		// so we initialize the auxList with RadixNode with quotient initially same as the number
		List<RadixNode> auxList = new ArrayList<>();
		for(int x : a) { // O(n)
			RadixNode node = new RadixNode(x,x);
			auxList.add(node);
		}
		
		
		for(int i=0;i<passCt;i++) { // O( maxNumber % radix ) =>  constant as it is independent of n  => Total time complexity O(n) + some K
			for(RadixNode node : auxList) { // O(n)
				int remainder = node.quotient % 10;
				int quotient = node.quotient / 10;
				node.quotient = quotient;
				
				Queue<RadixNode> q = buckets.get(remainder); // remainder should be always 0 - 9, so we get bucket for that index
				q.offer(node);
			}
			auxList.clear();
			for(int j=0;j<10;j++) { // O(10) => O(1) 
				Queue<RadixNode> q = buckets.get(j);
				while(!q.isEmpty()) {
					auxList.add(q.poll());
				}
			}
		}
		return auxList;

	}

}
