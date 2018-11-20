package skiena.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFS {

	public static void main(String[] args) {
		Graph<Integer> g = new Graph<Integer>();
		g.initGraph(5, false);
		
//		g.initVertexNodes(0);
//		g.initVertexNodes(1);
//		g.initVertexNodes(2);
//		g.initVertexNodes(3);
//		g.initVertexNodes(4);
		
		g.insertEdge(0, 1);
		g.insertEdge(0, 2);
		g.insertEdge(1, 2);
		g.insertEdge(1, 3);
		g.insertEdge(2, 3);
		g.insertEdge(3, 4);
		g.insertEdge(4, 0);
		
		BFS bfs = new BFS();
		bfs.traverse(g,0);
		
	}
	
	public Set<Integer> visited = new HashSet<Integer>();
	public Set<Integer> processed = new HashSet<Integer>();
	
	
	public Set<Integer> getVisited() {
		return visited;
	}

	public void setVisited(Set<Integer> visited) {
		this.visited = visited;
	}

	public Set<Integer> getProcessed() {
		return processed;
	}

	public void setProcessed(Set<Integer> processed) {
		this.processed = processed;
	}

	public void visit(int node) {
		visited.add(node);
		System.out.println("Visited "+node);
	}
	
	public void process(int node) {
		processed.add(node);
		System.out.println("Processed "+node);
	}
	
	public void traverse(Graph<Integer> g, int startingNode) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(startingNode);
		visit(startingNode);
		
		while(!q.isEmpty()) {
			int v = q.poll();
			List<VertexNode<Integer>> adjList = g.getVertices().get(v).getAdjacencyList();
			
			for(VertexNode<Integer> _v: adjList) {
				if(!visited.contains(_v.getValue())) {
					visit(_v.getValue());
					q.offer(_v.getValue());
				}
			}
			process(v);
		}
	}

}
