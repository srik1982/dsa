package skiena.graphs;

public class ConnectedGraphs {
	
public static void main(String[] args) {
		
		Graph<Integer> g = new Graph<Integer>();
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
		BFS bfs = new BFS();
		System.out.println("Is connected? : "+isConnectedGraph(g,bfs));
		
		
	}

	private static boolean isConnectedGraph(Graph<Integer> g, BFS bfs) {
		int count = 0;
		for(int i=0;i<g.getnVertices();i++) {
			int x = g.getVertices().get(i).getValue();
			
			if(!bfs.getVisited().contains(x)) {
				count++;
				bfs.traverse(g,x);
			}
		}
		
		return count == 1;
	}

}
