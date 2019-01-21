package graphs.v2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graphs.v2.DisjointSet.Node;

public class KruskalMinSpanTree{

	public static void main(String[] args) {
        Graph graph = new Graph(false);
        
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 2);
        
        KruskalMinSpanTree kmst = new KruskalMinSpanTree();        
        Set<Edge> mst = kmst.getKruskalMST(graph);
        mst.forEach(edge -> System.out.println(edge));
        
	}
	
	public class EdgeComparator implements Comparator<Edge>{

		@Override
		public int compare(Edge o1, Edge o2) {
			if(o1.weight <= o2.weight) {
				return -1;
			}
			return 1;
		}
		
	}
	
	/**
	 * O(n) + O(E * Log n) => O(E log n)
	 * @param g
	 * @return
	 */
	public Set<Edge> getKruskalMST(Graph g){
		List<Edge> edgesList = g.getAllEdges();
		Collections.sort(edgesList,new EdgeComparator());
		Set<Edge> mst = new HashSet<Edge>();
		DisjointSet djs = new DisjointSet();
		
		for(Vertex v : g.getAllVertices()) { // O(n) for n make set operations
			djs.makeSet(v.data);
		}
		
		for(Edge edge : edgesList) { // O(E * log n)
			Node n1 = djs.findSet(edge.src); // Constant time due to path compression. less than log(n)
			Node n2 = djs.findSet(edge.dest); // Constant time due to path compression. less than log(n) 
			
			if(n1!=n2) {
				mst.add(edge);
				djs.union(edge.src, edge.dest); // Constant time due to path compression. less than log(n)
			}
		}
		
		return mst;
	}

}
