package graphs.v1;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class CycleDetectionDirectedGraph<T extends Comparable> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph<Integer> graph = new Graph<Integer>();
		graph.initGraph(7, true);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(0, 4);
        graph.addEdge(4, 6);
        graph.addEdge(0, 5);
        graph.addEdge(5, 6);
        graph.addEdge(3, 7);
        graph.addEdge(7, 6);
        
        CycleDetectionDirectedGraph<Integer> obj = new CycleDetectionDirectedGraph<Integer>();
        System.out.println("Cycles present = "+obj.detectCycles(graph));
        
	}
	/**
	 * DFS runs as many times as the number of edges
	 * so O(E)
	 * @param graph
	 * @return
	 */
	public boolean detectCycles(Graph<T> graph) {
		Set<VertexNode<T>> visited = new HashSet<VertexNode<T>>();
		
		for(VertexNode<T> v : graph.getVertices()){
			if(!visited.contains(v)) {
				boolean retVal = dfs(v, visited);
				if(retVal) return true;
			}
		}
		return false;
	}
	
	public boolean dfs(VertexNode<T> vertex, Set<VertexNode<T>> visited) {
		visited.add(vertex);
		for(VertexNode<T> adjNode : vertex.getAdjacencyList()) {
			if(!visited.contains(adjNode)) {
				return dfs(adjNode,visited);
			}else {
				return true;
			}
		}
		visited.remove(vertex);
		return false;
	}

}
