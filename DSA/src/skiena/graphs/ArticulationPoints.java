package skiena.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * We shall reuse the Graph DataStructure already written - Graph, EdgeNode and VertexNode
 * But I am going to rewrite the DFS and cycle detection code ( already written in DFS class) as cycle detection was O(n) instead of O(1)
 * @author srikanthrao
 *
 */
public class ArticulationPoints {
	
	int time = 0;
	
	public <T extends Comparable>  Set<VertexNode<T>> findArticulationPoints(Graph<T> g){
		Set<VertexNode<T>> visited = new HashSet<VertexNode<T>>();
		Map<VertexNode<T>,VertexNode<T>> parent = new HashMap<VertexNode<T>,VertexNode<T>>();
//		Map<VertexNode<T>,Integer> visitedTime = new HashMap<VertexNode<T>,Integer>();
		Map<VertexNode<T>,Integer> lowTime = new HashMap<VertexNode<T>,Integer>();
		Set<VertexNode<T>> articulationPoints = new HashSet<VertexNode<T>>();
		VertexNode<T> vertex = g.getVertices().get(0);
		dfs(vertex, visited,parent,lowTime,articulationPoints);
		return articulationPoints;
	}
	
	public <T extends Comparable> void dfs(VertexNode<T> vertex, Set<VertexNode<T>> visited, Map<VertexNode<T>,VertexNode<T>> parent, Map<VertexNode<T>,Integer> lowTime, 
	Set<VertexNode<T>> articulationPoints){
		visited.add(vertex);
		lowTime.put(vertex, time);
		
		time++;
		
		int childCount = 0;
		boolean articulationPoint = false;
		
		List<VertexNode<T>> adjVertices = vertex.getAdjacencyList();
		for(VertexNode<T> adjVertex : adjVertices) {
			if(adjVertex.equals(parent.get(vertex))) { // undirected graph 1->2, 2->1 
				continue;
			}
			
			if(!visited.contains(adjVertex)) {
				parent.put(adjVertex, vertex);
				childCount++;
				
				dfs(adjVertex,visited,parent,lowTime,articulationPoints);
				
				if(lowTime.get(vertex) <= lowTime.get(adjVertex)) {
					articulationPoint = true;
				}else { //if adjacent node's low time is lower than current node, then copy it
					int min = getMin(lowTime.get(vertex),lowTime.get(adjVertex));
					lowTime.put(vertex, min);
				}
			}else { // same as above. need to test if this works.
				int min = getMin(lowTime.get(vertex),lowTime.get(adjVertex));
				lowTime.put(vertex, min);
			}
		}
		
		if(!parent.containsKey(vertex) && childCount>=2 || parent.containsKey(vertex) && articulationPoint) {
			articulationPoints.add(vertex);
		}
		
	}
	
	private int getMin(int x, int y) {
		return x < y ? x : y;
	}
	

	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<Integer>();
		graph.initGraph(8, false);
        graph.insertEdge(0, 1);
        graph.insertEdge(1, 2);
        graph.insertEdge(0, 2);
        graph.insertEdge(0, 3);
        graph.insertEdge(3, 4);
        graph.insertEdge(4, 5);
        graph.insertEdge(5, 6);
        graph.insertEdge(6, 4);
        graph.insertEdge(5, 7);
        
        ArticulationPoints ap = new ArticulationPoints();
        Set<VertexNode<Integer>> aPoints = ap.findArticulationPoints(graph);
        aPoints.forEach(point -> System.out.println(point));

	}

}
