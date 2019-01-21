package graphs.v1;

import java.util.List;

import graphs.v2.DisjointSet;
import graphs.v2.DisjointSet.Node;

public class CycleDetectUndirectedGraph {

	public static void main(String[] args) {
		CycleDetectUndirectedGraph cycle = new CycleDetectUndirectedGraph();
        Graph<Integer> graph = new Graph<Integer>(4,false);
        
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
//        graph.addEdge(5, 1);
        
        System.out.println("Cycle detected = "+detectCycleByDS(graph));

	}
	
	static boolean detectCycleByDS(Graph<Integer> graph) {
		DisjointSet ds = new DisjointSet();
		
		for(VertexNode<Integer> v : graph.getVertices()) {
			int src = v.getValue();
			ds.makeSet(src);
		}
		boolean retVal = false;
		for(VertexNode<Integer> v : graph.getVertices()) {
			int src = v.getValue();
			Node node1 = ds.findSet(src);
			
			for(VertexNode<Integer> tgt : v.getAdjacencyList()) {
				Node node2 = ds.findSet(tgt.getValue());
				
				if(node1 == node2) {
					retVal = true;
					break;
				}
				ds.union(v.getValue(), tgt.getValue());
			}
		}
		
		return retVal;
		
	}

}
