	package graphs.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS {

	public static void main(String[] args) {
		Graph<Integer> g = new Graph<Integer>();
		g.initGraph(8, true);
		
		g.addEdge(0, 1);
		g.addEdge(0, 5);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(5, 4);
		g.addEdge(5, 3);
		g.addEdge(5, 2);
		g.addEdge(4, 3);
		g.addEdge(3, 5);
		g.addEdge(6, 7);
		
//		g.printGraph();
//		BFS bfs = new BFS();
//		bfs.traverse(g, 0);
		
		DFS dfs = new DFS();
		dfs.parent = new int[g.getnVertices()];
		dfs.startTime = new int[g.getnVertices()];
		dfs.endTime = new int[g.getnVertices()];
		for(int i=0;i<g.getnVertices();i++) {
			dfs.parent[i] = -1;
		}
		
		dfs.traverse(g,0);
		
//		System.out.println("\n\n");
//		for(int i=0;i<g.getnVertices();i++) {
//			System.out.println("\n parent of "+i+" : "+dfs.parent[i]);
//			System.out.println(" startTime of "+i+" : "+dfs.startTime[i]);
//			System.out.println(" endTime of "+i+" : "+dfs.endTime[i]);
//		}
	}
	
	Set<Integer> visited = new HashSet<Integer>();
	Set<Integer> processed = new HashSet<Integer>();
	int[] parent;
	int[] startTime,endTime;
	int clock = 0;
	
	public void visit(int node) {
		visited.add(node);
		System.out.println("Visited "+node+" through edge: "+parent[node]+" -->"+node);
	}
	
	public void process(int node) {
		processed.add(node);
		System.out.println("Processed "+node+". Total children = "+((endTime[node]-startTime[node])/2));
	}
	
	//stupid code, we can just check if one of the nodes is visited earlier.
	public boolean isCycle(int x, int y) {
		boolean retVal = false;
		int temp = x;
		while(parent[temp]!=-1 && temp!=y) {
			temp = parent[temp];
		}
		retVal = temp == y;
		return retVal;
	}
	
	public void traverse(Graph<Integer> g, int x) {		
		
			startTime[x] = clock++;
			visit(x);
			
			VertexNode<Integer> vertexNode = g.getVertices().get(x);
			List<VertexNode<Integer>> adjList = vertexNode.getAdjacencyList();
			for(VertexNode<Integer> _v : adjList) {
				if(!visited.contains(_v.getValue())) {
					parent[_v.getValue()] = x;
					traverse(g,_v.getValue());
				}else if(isCycle(x,_v.getValue())) {
					System.out.println("Cycle detected : "+x+" --> "+_v);
				}
			}
			
			endTime[x] = clock++;
			process(x);
		}
	

}
