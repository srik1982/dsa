package graphs.v2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ArticulationPointsAndBridges {

	public static void main(String[] args) {
		Graph graph = new Graph(false);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        graph.addEdge(5, 7);
        
        ArticulationPointsAndBridges obj = new ArticulationPointsAndBridges();
        obj.printArticulationPointsAndBridges(graph);
	}
	
	int time = 0;
	
	public void printArticulationPointsAndBridges(Graph g) {
		Set<Vertex> visited = new HashSet<>();
		Map<Vertex,Integer> lowTime = new HashMap<>();
		Map<Vertex,Vertex> parent = new HashMap<>();
		Vertex current = g.getAllVertices().iterator().next();
		Set<Vertex> articulationPoints = new HashSet<>();
		Set<Edge> bridges = new HashSet<>();
		
		dfs(visited,current,lowTime,parent,articulationPoints);
		
		for(Edge e : g.getAllEdges()) {
			int u = e.src;
			int v = e.dest;
			
			int diff = Math.abs(lowTime.get(new Vertex(u)) - lowTime.get(new Vertex(v)));
			if(diff > 0) {
				bridges.add(e);
			}
		}
		
		System.out.println("Articulation points = "+articulationPoints);
		System.out.println("Bridges = "+bridges);
		
	}

	private void dfs(Set<Vertex> visited, Vertex current, Map<Vertex, Integer> lowTime, Map<Vertex, Vertex> parent,
			Set<Vertex> articulationPoints) {
		lowTime.put(current, time++);
		visited.add(current);
		
		boolean articulationPoint = false;
		int children = 0;
		
		for(Vertex adjNode : current.adjacencyList) {
			if(parent.get(current) == adjNode) {
				continue;
			}
			if(!visited.contains(adjNode)) {
				children++;
				parent.put(adjNode, current);
				
				dfs(visited, adjNode,lowTime,parent,articulationPoints);
				
				if(lowTime.get(adjNode) <= lowTime.get(current)) {
					lowTime.put(current, min(lowTime.get(current),lowTime.get(adjNode)));
				}else {
					articulationPoint = true;
				}
			}else {
				lowTime.put(current, min(lowTime.get(current),lowTime.get(adjNode)));
			}
		}
		
		if(!parent.containsKey(current) && children>= 2 || parent.containsKey(current) && articulationPoint) {
			articulationPoints.add(current);
		}
		
	}

	private Integer min(Integer i1, Integer i2) {
		// TODO Auto-generated method stub
		return i1 <= i2 ? i1 : i2;
	}

}
