package graphs.v2;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {

	public static void main(String[] args) {
		 DisjointSet ds = new DisjointSet();
	        ds.makeSet(1);
	        ds.makeSet(2);
	        ds.makeSet(3);
	        ds.makeSet(4);
	        ds.makeSet(5);
	        ds.makeSet(6);
	        ds.makeSet(7);

	        ds.union(1, 2);
	        ds.union(2, 3);
	        ds.union(4, 5);
	        ds.union(6, 7);
	        ds.union(5, 6);
	        ds.union(3, 7);

	        System.out.println(ds.findSet(1));
	        System.out.println(ds.findSet(2));
	        System.out.println(ds.findSet(3));
	        System.out.println(ds.findSet(4));
	        System.out.println(ds.findSet(5));
	        System.out.println(ds.findSet(6));
	        System.out.println(ds.findSet(7));

	}
	
	private Map<Integer, Node> map = new HashMap<Integer,Node>();
	
	public class Node{
		int data;
		Node parent;
		int rank;
		
		public String toString() {
			return data+" , "+rank;
		}
	}
	
	/**
     * Create a set with only one element.
     * O(1)
     */
	public Node makeSet(int data) {
		Node newNode = new Node();
		newNode.data = data;
		newNode.rank = 0;
		newNode.parent = newNode;
		
		map.put(data,newNode);
		
		return newNode;
	}
	
	/**
     * Combines two sets together to one.
     * Does union by rank
     *
     * @return true if data1 and data2 are in different set before union else false.
     */
	public boolean union(int data1, int data2) {
		Node node1 = map.get(data1);
		Node node2 = map.get(data2);
		
		return union(node1,node2);
	}
	
	/**
     * Combines two sets together to one.
     * Does union by rank
     *
     * @return true if data1 and data2 are in different set before union else false.
     * Same as findSet - log(n)
     */
	public boolean union(Node node1, Node node2) {
		
		Node parent1 = findSet(node1);
		Node parent2 = findSet(node2);
		boolean retVal = false;
		if(parent1!=parent2) {
			retVal = true;
			if(parent1.rank >= parent2.rank) {
				parent2.parent = parent1;
				parent1.rank = parent2.rank + 1;
			}else {
				parent1.parent = parent2;
				parent2.rank = parent1.rank + 1;
			}
		}
		return retVal;
	}
	
	
	public Node findSet(int data) {
		return findSet(map.get(data));
	}
	
	/**
	 * worst case log(n)
	 * but most average cases are O(1) due to path compression
	 * 
	 * @param node
	 * @return
	 */
	public Node findSet(Node node) {
		Node parent = node.parent;
		if(parent == node) {
			return node;
		}
		Node parentNode = findSet(node.parent);
		node.parent = parentNode;
		return parentNode;
	}

}
