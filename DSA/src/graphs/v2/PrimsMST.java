package graphs.v2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graphs.v2.MinHeap.HeapNode;

/**
 * Can be updated to use BinaryMinHeap instead of MinHeap.
 * @author srikanthrao
 *
 */
public class PrimsMST {

	public static void main(String[] args) {
		Graph g = new Graph(false);
		g.addEdge(0, 1, 1);
		g.addEdge(0, 3, 3);
		g.addEdge(1, 3, 3);
		g.addEdge(1, 4, 1);
		g.addEdge(4, 3, 1);
		g.addEdge(1, 2, 6);
		g.addEdge(4, 2, 5);
		g.addEdge(4, 5, 4);
		g.addEdge(5, 2, 2);
		
		PrimsMST obj = new PrimsMST();
		Set<Edge> results = obj.mst(g);
		results.forEach(edge -> System.out.println(edge.src+" --> "+edge.dest+" : "+edge.weight));
		
	}
	
	
	public Set<Edge> mst(Graph g){
		
		// first we initialize all weight to Infinity, and set the first node's weight to 0 so that we can start from there with extractMin
		Vertex prev = g.getAllVertices().iterator().next();
		Map<Vertex, Edge> vertex2edge = new HashMap<>();
		Set<Edge> results = new HashSet<>();
		Map<Integer,Vertex> vNo2VMap = g.vertices;
		Map<Integer,Set<Edge>> edgeSrc2edge = g.edges;
		MinHeap minHeap = new MinHeap();
		
		for(Integer vData : g.vertices.keySet()) {
			minHeap.add(vData, Integer.MAX_VALUE);
		}
		
		minHeap.decreaseWeight(prev.data, 0);
		
		//Greedy approach. In each iteration, we pick up the least weighted node
		//that is : we basically pickup all the least weight ones.
		while(!minHeap.isEmpty()) {
			int vNo =  minHeap.extractMinNode().key;
			Vertex current = vNo2VMap.get(vNo); 
			
			//we got the least weight entry from the min heap, if there is already an entry in vertex2edge map, it means
			// that we have already considered this node and the corresponding edge in vertex2edge map is the least weighted edge to that particular node
			//so we add that to the results
			//NOTE that vertex2edge is considered for all edges TERMINATING at the current vertex.
			if(vertex2edge.containsKey(current)) {
				results.add(vertex2edge.get(current));
			}
			
			//Now we are done considering all edges TERMINATING at the current node
			//lets consider the edges that START from the current node
			// so get edges from
			Set<Edge> adjEdges = edgeSrc2edge.get(current.data); 
			
			
			// For each edge STARTING from current node, call decreaseWeight() on the dest node
			// minHeap.decreaseWeight() only decreases the weight if the supplied weight is less than the existing weight.
			// This way we keep finding the LEAST WEIGHT WAY to reach that node.
			for(Edge e: adjEdges) {
				boolean decreased = minHeap.decreaseWeight(e.dest, e.weight);
				if(decreased) {
					vertex2edge.put(g.getVertex(e.dest), e);
				}
			}
			
		}
		
		return results;
		
	}
}
