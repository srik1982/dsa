package graphs.v2;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {

	public static void main(String[] args) {
		MinHeap minHeap = new MinHeap();
		//initial
		minHeap.add(0, 8);
		minHeap.add(1, -1);
		minHeap.add(2, 3);
		minHeap.add(3, 6);
		minHeap.add(4, 99);
		minHeap.add(5, -5);
		minHeap.add(5, 43);
		minHeap.add(5, 31);
		minHeap.add(5, 17);
		minHeap.add(5, 12);
		minHeap.printMinHeap();
		
		//change here
		System.out.println("Extracting: "+minHeap.extractMinNode().key);
//		minHeap.decreaseWeight(1, 1);
//		minHeap.decreaseWeight(3, 3);
//		minHeap.printMinHeap();
//		
//		System.out.println("Extracting: "+minHeap.extractMinNode().key);
//		minHeap.decreaseWeight(4, 1);
//		minHeap.decreaseWeight(2, 6);
//		minHeap.decreaseWeight(3, 3);
//		minHeap.printMinHeap();
//		
//		System.out.println("Extracting: "+minHeap.extractMinNode().key);
//		minHeap.decreaseWeight(3, 1);
//		minHeap.decreaseWeight(2, 5);
//		minHeap.decreaseWeight(5, 4);
//		minHeap.printMinHeap();
//		
//		System.out.println("Extracting: "+minHeap.extractMinNode().key);
//		minHeap.printMinHeap();
//		
//		System.out.println("Extracting: "+minHeap.extractMinNode().key);
//		minHeap.decreaseWeight(2, 2);
//		minHeap.printMinHeap();
//		
//		System.out.println("Extracting: "+minHeap.extractMinNode().key);
//		minHeap.printMinHeap();
		
		
		
//		while(!minHeap.isEmpty()) {
//			System.out.println(minHeap.extractMin());
//		}
	}
	
	public class HeapNode {
		int key;
		int weight;
	}
	
	public List<HeapNode> heap = new ArrayList<HeapNode>();
	
	public void printMinHeap() {
		for(int i=0;i<heap.size();i++) {
			int parent = (i-1)/2;
			HeapNode node = heap.get(i);
			HeapNode parentNode = heap.get(parent);
			System.out.println("Parent: "+parentNode.key+", child: "+node.key+" weight = "+node.weight);
		}
	}
	
	public void add(int key, int weight) {
		 HeapNode heapNode = new HeapNode();
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
	
	void swap(int pos1, int pos2, List<HeapNode> heap) {
		HeapNode node1 = heap.get(pos1);
		HeapNode node2 = heap.get(pos2);
		
		heap.set(pos2, node1);
		heap.set(pos1,node2);
	}
	
	public HeapNode extractMinNode() {
		HeapNode minNode = heap.get(0);
		HeapNode lastNode = heap.get(heap.size() - 1);
		heap.set(0, lastNode);
		heap.remove(heap.size() - 1);
		
		//now we move this node at 0 to the right place
		int lChildPos = 1; //2*i+1 and i=0
		int rChildPos = 2;
		int parentPos = 0;
		
		while(true) {
			HeapNode lChild = null, rChild = null;
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
				if(lChild !=null && lChild.weight < lastNode.weight) {
					swap(parentPos,lChildPos,heap);
				}else if(rChild!=null && rChild.weight < lastNode.weight) {
					swap(parentPos,rChildPos,heap);
				}
				break;
			}
			
			lChildPos = 2*parentPos+1;
			rChildPos = 2*parentPos+2;
			lastNode = heap.get(parentPos);
			
		}
		
		return minNode;
	}
	
	int extractMin() {
		return extractMinNode().weight;
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	public boolean decreaseWeight(int key, int weight) {
		
		HeapNode matchingNode = null;
		int index = -1;
		for(int i=0;i<heap.size();i++) {
			HeapNode n = heap.get(i);
			if(n.key == key) {
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
