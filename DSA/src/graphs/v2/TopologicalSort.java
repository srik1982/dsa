package graphs.v2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class TopologicalSort {

	public static void main(String[] args) {
		Graph graph = new Graph(false);
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(6, 0);
        graph.addEdge(6, 1);
        graph.addEdge(6, 2);
        graph.addEdge(1, 7);
        graph.addEdge(3, 2);
        graph.addEdge(3, 7);
        graph.addEdge(3, 8);        
        graph.addEdge(3, 4);
        graph.addEdge(8, 2);
        graph.addEdge(4, 8);
        graph.addEdge(9, 4);
        
        TopologicalSort sort = new TopologicalSort();
        
        System.out.println(sort.topologicalSort(graph));
	}
	
	public List<Integer> topologicalSort(Graph g){
		
		Stack<Integer> stack = new Stack<Integer>();
		Set<Vertex> visited = new HashSet<Vertex>();
		
		for(Vertex v : g.getAllVertices()) {
			if(!visited.contains(v)) {
				topologicalSort(v,stack,visited);
			}
		}
		
		List<Integer> topoOrder = new ArrayList<Integer>();
		while(!stack.isEmpty()) {
			topoOrder.add(stack.pop());
		}
		
		return topoOrder;
	}

	private void topologicalSort(Vertex v, Stack<Integer> stack, Set<Vertex> visited) {
		visited.add(v);
		List<Vertex> adjVertices = v.adjacencyList;;
		for(Vertex _v : adjVertices) {
			if(!visited.contains(_v))
			topologicalSort(_v, stack, visited);
		}
		stack.push(v.data);
	}
	
	

}
