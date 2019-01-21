package graphs.v2;

import java.util.ArrayList;
import java.util.List;


public class BinaryMinHeap<T> {
	public class HeapNode<T> {
		T key;
		int weight;
	}
	
	public List<HeapNode<T>> heap = new ArrayList<HeapNode<T>>();
	
	public void add(T key, int weight) {
		HeapNode<T> heapNode = new HeapNode<T>();
		 heapNode.key = key;
		 heapNode.weight = weight;
		 
		 heap.add(heapNode);
		 
		 //Now lets compare with its parent and move to its rightful place
		 int current = heap.size() - 1;
		 int parent = (current - 1)/2;
		 
		 while(parent>=0 && (getWeight(current) < getWeight(parent))) {
			 swap(current,parent,heap);
			 current = parent;
			 parent = (parent-1)/2;
		 }
	}
	
	int getWeight(int index) {
		return heap.get(index).weight;
	}
	
	void swap(int pos1, int pos2, List<HeapNode<T>> heap) {
		HeapNode<T> node1 = heap.get(pos1);
		HeapNode<T> node2 = heap.get(pos2);
		
		heap.set(pos2, node1);
		heap.set(pos1,node2);
	}
	
	public HeapNode<T> extractMinNode() {
		HeapNode<T> minNode = heap.get(0);
		HeapNode<T> lastNode = heap.get(heap.size() - 1);
		heap.set(0, lastNode);
		heap.remove(heap.size() - 1);
		
		//now we move this node at 0 to the right place
		int lChildPos = 1; //2*i+1 and i=0
		int rChildPos = 2;
		int parentPos = 0;
		
		while(true) {
			HeapNode<T> lChild = null, rChild = null;
			if(lChildPos > heap.size() -1 && rChildPos > heap.size() -1) {
				break;
			}
			if (lChildPos <= heap.size()-1 ) {
				lChild = heap.get(lChildPos);
			}
			if(rChildPos <= heap.size()-1) {
				rChild = heap.get(rChildPos);
			}
		
			if(lChild!=null && rChild!=null) {
				if(lChild.weight < rChild.weight) {
					swap(parentPos,lChildPos,heap);
					parentPos = lChildPos;
				}else {
					swap(parentPos,rChildPos,heap);
					parentPos = rChildPos;
				}
			}else {
				if(lChild.weight < lastNode.weight) {
					swap(parentPos,lChildPos,heap);
				}
				break;
			}
			
			lChildPos = 2*parentPos+1;
			rChildPos = 2*parentPos+2;
			lastNode = heap.get(parentPos);
			
		}
		
		return minNode;
	}
	
	int extractMinWeight() {
		return extractMinNode().weight;
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	public boolean decreaseWeight(T key, int weight) {
		
		HeapNode<T> matchingNode = null;
		int index = -1;
		for(int i=0;i<heap.size();i++) {
			HeapNode<T> n = heap.get(i);
			if(n.key.equals(key)) {
				matchingNode = n;
				index = i;
				break;
			}
		}
		
		if(matchingNode == null) return false;
		
		boolean decreased = false;
		//decrease weight should only decrease only if the supplied weight is less than current weight
		if(weight < matchingNode.weight) {
			matchingNode.weight = weight;
			decreased = true;
			
			int current = index;
			int parent = (current - 1)/2;
			 
			 while(parent>=0 && (getWeight(current) < getWeight(parent))) {
				 swap(current,parent,heap);
				 current = parent;
				 parent = (parent-1)/2;
			 }
		}
		return decreased;
	}

}
