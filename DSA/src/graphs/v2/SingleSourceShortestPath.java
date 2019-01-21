package graphs.v2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class SingleSourceShortestPath {

	public static void main(String[] args) {
		Graph graph = new Graph(false);
		graph.addEdge(0, 1, 5);
		graph.addEdge(1, 2, 2);
		graph.addEdge(0, 3, 9);
		graph.addEdge(0, 4, 3);
		graph.addEdge(4, 5, 2);
		graph.addEdge(5, 3, 2);
		graph.addEdge(2, 3, 3);
		
		SingleSourceShortestPath obj = new SingleSourceShortestPath();
		
		Map<Vertex,Integer> shortestPaths = obj.findShortestPaths(graph);
		shortestPaths.entrySet().forEach(entry -> System.out.println(entry.getKey().data+" : "+entry.getValue()));
	}
	
	public Map<Vertex, Integer> findShortestPaths(Graph g){
		Map<Vertex, Integer> distances = new HashMap<>();
		Map<Vertex,Vertex> parent = new HashMap<>();
		BinaryMinHeap<Vertex> minHeap = new BinaryMinHeap<>();
		
		for(Vertex v : g.getAllVertices()) {
			minHeap.add(v, Integer.MAX_VALUE);
		}
		Vertex firstNode = g.vertices.values().iterator().next();
		minHeap.decreaseWeight(firstNode, 0);
		parent.put(firstNode, null);
		
		Map<Integer, Set<Edge>> edgesFrom = g.edges;
		Map<Integer,Vertex> vertexMap = g.vertices;
		
		//Time Complexity? 
		// The inner loop runs |E| times only because each vertex doesn't have an outgoing edge
		// And decreaseWeight runs log(v) times
		// So, it is approx log(v) * E* log(v) => E*log^2(v)
		while(!minHeap.isEmpty()) {//Runs |v| times?
			
			BinaryMinHeap<Vertex>.HeapNode<Vertex> node = minHeap.extractMinNode(); // log(v)
			Vertex current = (Vertex) node.key;
			
			distances.put(current, node.weight);
			
			Set<Edge> adjEdges = edgesFrom.get(current.data);
			if(adjEdges!=null)
			for(Edge adjEdge : adjEdges) { // |E| times
				int edgeWeight = adjEdge.weight;
				int dest = adjEdge.dest;
				//notice that we are not comparing here. Decrease weight does it conditionally
				minHeap.decreaseWeight(vertexMap.get(dest), node.weight + edgeWeight); //log(v) times
				parent.put(vertexMap.get(dest), current);
			}
			
		}
		
//		System.out.println("Shortest paths from first node : "+firstNode.data);
//		for(Map.Entry<Vertex, Vertex> entry : parent.entrySet()) {
//			StringBuilder path = new StringBuilder(String.valueOf(entry.getKey().data));
//			Vertex parentNode = entry.getValue();
//			while(parentNode!=null) {
//				path.append("<--").append(parentNode.data);
//				parentNode = parent.get(parentNode);
//			}
//			System.out.println("Shortest path to reach : "+entry.getKey().data+" is "+path.toString());
//		}
		
		return distances;
	}

}
