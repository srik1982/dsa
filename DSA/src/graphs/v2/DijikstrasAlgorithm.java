package graphs.v2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * This is also how Dijikstra's algorithm works
 */
public class DijikstrasAlgorithm {

	public static void main(String[] args) {
		Graph graph = new Graph(false);
		graph.addEdge(0, 1, 5);
		graph.addEdge(1, 2, 2);
		graph.addEdge(0, 3, 9);
		graph.addEdge(0, 4, 3);
		graph.addEdge(4, 5, 2);
		graph.addEdge(5, 3, 2);
		graph.addEdge(2, 3, 3);
		
		DijikstrasAlgorithm obj = new DijikstrasAlgorithm();
		obj.findShortestPath(graph, 0).forEach((key,value) -> System.out.println(key+" : "+value));
	}
	
	public Map<Integer,Integer> findShortestPath(Graph g, int source){
		Map<Integer,Integer> distances = new HashMap<Integer, Integer>();
		for(Vertex v : g.getAllVertices()) {
			distances.put(v.data, Integer.MAX_VALUE);
		}
		
		distances.put(source, 0);
		
		Queue<Integer> nextNodes = new LinkedList<Integer>();
		nextNodes.offer(source);
		
		while(!nextNodes.isEmpty()) {
			int node = nextNodes.poll();
			Set<Edge> edges = g.getEdges().get(node);
			if(edges!=null)
			for(Edge e : edges) {
				int distance = distances.get(node) + e.weight;
				if(distance < distances.get(e.dest)) {
					distances.put(e.dest, distance);
				}
				nextNodes.offer(e.dest);
			}
		}
		
		return distances;
	}
	

}
