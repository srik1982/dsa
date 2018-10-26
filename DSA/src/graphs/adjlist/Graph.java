package graphs.adjlist;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Graph {
	 
	private int nVertices;
	private boolean directed;
	private LinkedList<VertexNode> vertices;

	public int getnVertices() {
		return nVertices;
	}


	public void setnVertices(int nVertices) {
		this.nVertices = nVertices;
	}


	public boolean isDirected() {
		return directed;
	}


	public void setDirected(boolean directed) {
		this.directed = directed;
	}


	public LinkedList<VertexNode> getVertices() {
		return vertices;
	}


	public void setVertices(LinkedList<VertexNode> vertices) {
		this.vertices = vertices;
	}


	public void initGraph(int nVertices, boolean directed) {
		this.directed = directed;
		this.nVertices = nVertices;
		vertices = new LinkedList<VertexNode>();
		for(int i=0;i<nVertices;i++) {
			vertices.add(new VertexNode(i));
		}
	}
	public void insertEdge(int x,int y) {
		insertEdge(x,y,false);
	}
	public void insertEdge(int x, int y, boolean directed) {
		insertEdge(x,y,directed,0);
	}
	
	public void insertEdge(int x, int y, boolean directed, int weight) {
		EdgeNode edge = new EdgeNode(y,weight);
		vertices.get(x).addEdge(edge);
		vertices.get(x).incrementDegree();
		if(!directed) {
			insertEdge(y,x,true,weight);
		}
		
	}
	
	public void printGraph() {
		for(int i=0;i<vertices.size();i++) {
			System.out.println(vertices.get(i));
		}
	}
	public Set<Integer> visited = new HashSet<Integer>();
	public Set<Integer> processed = new HashSet<Integer>();
	
	public void visit(int node) {
		visited.add(node);
		System.out.println("Visited "+node);
	}
	
	public void process(int node) {
		processed.add(node);
		System.out.println("Processed "+node);
	}
	
	public void bfs(int startingNode) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(startingNode);
		visit(startingNode);
		
		while(!q.isEmpty()) {
			int v = q.poll();
			List<Integer> adjList = vertices.get(v).getAdjacencyList();
			
			for(int y: adjList) {
				if(!visited.contains(y)) {
					visit(y);
					q.offer(y);
				}
			}
			process(v);
		}
	}
	
	
	public static void main(String[] args) {
		
		Graph g = new Graph();
		g.initGraph(5, false);
		g.insertEdge(0, 1);
		g.insertEdge(0, 2);
		g.insertEdge(1, 2);
		g.insertEdge(1, 3);
		g.insertEdge(2, 3);
		g.insertEdge(3, 4);
		g.insertEdge(4, 0);
		
		g.printGraph();
		g.bfs(3);
		
	}

}
