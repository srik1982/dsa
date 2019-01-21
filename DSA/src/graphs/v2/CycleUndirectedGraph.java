package graphs.v2;

import java.util.HashSet;
import java.util.Set;

import graphs.v2.DisjointSet.Node;

public class CycleUndirectedGraph {

	public static void main(String[] args) {
		CycleUndirectedGraph cycle = new CycleUndirectedGraph();
        Graph graph = new Graph(false);
        
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 1);
        
        System.out.println("Cycle exists = "+cycle.hasCycleByDFS(graph));
	}
	
	/**
	 * So O(E*log(v))
	 * @param g
	 * @return
	 */
	boolean hasCycleByDisjointSet(Graph g){
		DisjointSet ds = new DisjointSet();
		for(Integer vData : g.vertices.keySet()) {
			ds.makeSet(vData);
		}
		
		for(Edge e : g.getAllEdges()) { // |E|
			Node n1 = ds.findSet(e.src); // log(V)
			Node n2 = ds.findSet(e.dest); // log(V)
			
			if(n1 == n2)return true;
			
			ds.union(e.src, e.dest);
		}
		
		return false;
	}
	
	/**
	 * dfs runs once for each vertex in worst case -> |v|
	 * contains check is O(1) since it uses hashMap as backend.
	 * so, O(n)
	 * @param g
	 * @return
	 */
	boolean hasCycleByDFS(Graph g) {
		Set<Vertex> visited = new HashSet<Vertex>();
		boolean cycle = false;
		for(Vertex v : g.getAllVertices()) {
			if(!visited.contains(v)) {
				cycle = dfs(v,null,visited);
				if(cycle) {
					break;
				}
			}
		}
		return cycle;
	}
	
	public boolean dfs(Vertex vertex, Vertex parent, Set<Vertex> visited) {
		visited.add(vertex);
		boolean cycleFound = false;
		for(Vertex adjNode : vertex.adjacencyList) {
			if(!adjNode.equals(parent)) {
				if(visited.contains(adjNode)) {
					cycleFound = true;
				}else {
					cycleFound = dfs(adjNode,vertex,visited);
				}
				if(cycleFound) {
					break;
				}
			}
		}
		return cycleFound;
	}

}
