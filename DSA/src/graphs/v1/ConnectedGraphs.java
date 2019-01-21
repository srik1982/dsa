package graphs.v1;

public class ConnectedGraphs {
	
public static void main(String[] args) {
		
		Graph<Integer> g = new Graph<Integer>();
		g.initGraph(8, false);
		g.addEdge(0, 1);
		g.addEdge(0, 5);
		g.addEdge(1, 2);
		g.addEdge(5, 4);
		g.addEdge(5, 3);
		g.addEdge(4, 3);
		g.addEdge(2, 3);
		g.addEdge(6, 7);
		
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
