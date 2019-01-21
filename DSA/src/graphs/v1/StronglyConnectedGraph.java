package graphs.v1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class StronglyConnectedGraph<T extends Comparable> {

	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<Integer>();
		graph.initGraph(7, true);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);
        graph.addEdge(5, 6);
        
        StronglyConnectedGraph<Integer> test = new StronglyConnectedGraph<Integer>();
        List<Set<VertexNode<Integer>>> results = test.scc(graph);
        for(Set<VertexNode<Integer>> cg : results) {
        	System.out.print("\n Connected : ");
        	for(VertexNode<Integer> v : cg) {
        		System.out.print(v.getValue()+" ");
        	}
        }
	}
	
	public List<Set<VertexNode<T>>> scc(Graph<T> graph){
		List<VertexNode<T>> vertices = graph.getVertices();
		Deque<VertexNode<T>> stack = new ArrayDeque<VertexNode<T>>();
		Set<VertexNode<T>> visited = new HashSet<VertexNode<T>>();
		
		for(VertexNode<T> v : vertices) {
			if(!visited.contains(v)) {
				dfs(v,stack,visited);
			}
		}
		List<Set<VertexNode<T>>> connectedGraphs = new ArrayList<Set<VertexNode<T>>>();
		Graph<T> reversedGraph = graph.reverseGraph(graph.isDirected(),graph.getnVertices());
		visited.clear();
		while(!stack.isEmpty()) {
			VertexNode<T> node = stack.pop();
			if(!visited.contains(node)) {
				VertexNode<T> _v = reversedGraph.getVertexNode(node.getValue());
				Set<VertexNode<T>> connectedGraph = new HashSet<VertexNode<T>>();
				findConnectedComponents(_v, connectedGraph, visited);
				connectedGraphs.add(connectedGraph);
			}
		}
		
		return connectedGraphs;
	}
	public void dfs(VertexNode<T> vertex, Deque<VertexNode<T>> stack, Set<VertexNode<T>> visited) {
		visited.add(vertex);
		for(VertexNode<T> adjNode : vertex.getAdjacencyList()) {
			if(!visited.contains(adjNode)) {
				dfs(adjNode,stack,visited);
			}
		}
		stack.push(vertex);
	}
	
	public void findConnectedComponents(VertexNode<T> vertex, Set<VertexNode<T>> connectedGraph, Set<VertexNode<T>> visited) {
		visited.add(vertex);
		connectedGraph.add(vertex);
		for(VertexNode<T> adjNode : vertex.getAdjacencyList()) {
			if(!visited.contains(adjNode)) {
				findConnectedComponents(adjNode, connectedGraph, visited);
			}
		}
	}

}
