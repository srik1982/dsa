package graphs.v1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class TopologicalSort {

	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<Integer>();
		graph.initGraph(8, true);
		graph.addEdge(0, 2);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        graph.addEdge(5, 2);
        graph.addEdge(2, 7);
        graph.addEdge(7, 10);
        
        TopologicalSort ts = new TopologicalSort();
        
        Deque<VertexNode<Integer>> topSortedNodes = ts.topSort(graph);
        
        while(!topSortedNodes.isEmpty()) {
        	System.out.println(topSortedNodes.removeFirst().getValue());
        }
        
	}
	/**
	 * Note that I am using Deque to implement a stack for 2 reasons -
	 * 1. Java Deque implementation says that we should preper this interface over Stack
	 * 2. Deque provides addFirst(e), removeFirst() and peekFirst() which are equivalent to push(), pop() and peek()
	 * @param g
	 * @return
	 */
	public Deque<VertexNode<Integer>> topSort(Graph<Integer> g){
		Deque<VertexNode<Integer>> stack = new ArrayDeque<VertexNode<Integer>>();
		Set<VertexNode<Integer>> visited = new HashSet<VertexNode<Integer>>();
		for(VertexNode<Integer> v : g.getVertices())
		{
			if(!visited.contains(v)) {
				topSortUtil(v,stack,visited);
			}
		}
		return stack;
	}
	
	public void topSortUtil(VertexNode<Integer> vertex, Deque<VertexNode<Integer>> stack, Set<VertexNode<Integer>> visited) {
		visited.add(vertex);
		for(VertexNode<Integer> adjNode : vertex.getAdjacencyList()) {
			if(!visited.contains(adjNode)) {
				topSortUtil(adjNode, stack, visited);
			}
		}
		stack.addFirst(vertex);
	}

}
