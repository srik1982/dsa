package graphs.adjlist;

public class ConnectedGraphs {
	
public static void main(String[] args) {
		
		Graph g = new Graph();
		g.initGraph(8, false);
		g.insertEdge(0, 1);
		g.insertEdge(0, 5);
		g.insertEdge(1, 2);
		g.insertEdge(5, 4);
		g.insertEdge(5, 3);
		g.insertEdge(4, 3);
		g.insertEdge(2, 3);
		g.insertEdge(6, 7);
		
		g.printGraph();
		System.out.println("Is connected? : "+isConnectedGraph(g));
		
		
	}

	private static boolean isConnectedGraph(Graph g) {
		int count = 0;
		for(int i=0;i<g.getnVertices();i++) {
			int x = g.getVertices().get(i).getX();
			
			if(!g.visited.contains(x)) {
				count++;
				g.bfs(x);
			}
		}
		
		return count == 1;
	}

}
