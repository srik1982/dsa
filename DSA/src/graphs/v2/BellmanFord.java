package graphs.v2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class BellmanFord {

	public static void main(String[] args) {
		Graph graph = new Graph(false);
        graph.addEdge(0, 3, 8);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, -3);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 3, 1);
        
        BellmanFord obj = new BellmanFord();
        Map<Integer,Integer> shortestPaths = obj.findShortestPaths(graph);
        shortestPaths.entrySet().forEach(entry -> System.out.println(entry.getKey() +" : "+entry.getValue()));
	}
	
	private static int INFINITY = 10000000;
	class NegativeWeightCycleException extends RuntimeException{
		
	}
	
	public Map<Integer,Integer> findShortestPaths(Graph g){
		Map<Integer,Vertex> vertexMap = g.vertices;
		Map<Vertex, Vertex> parent = new HashMap<>();
		Map<Integer,Integer> distance = new HashMap<>();
		
		for(Vertex v : g.getAllVertices()) {
			distance.put(v.data, INFINITY);
		}
		distance.put(g.getAllVertices().iterator().next().data, 0);
		
		for(int i=0;i<g.vertices.size()-1;i++) {
			for(Edge edge : g.getAllEdges()) {
				int u = edge.src;
				int v = edge.dest;
				
				if(distance.get(u)+edge.weight < distance.get(v)) {
					distance.put(v, distance.get(u)+edge.weight);
					parent.put(vertexMap.get(v), vertexMap.get(u));
				}
			}
			
		}
		
		return distance;
		
	}

}
