package graphs.v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Graph<T extends Comparable> {
	 
	private int nVertices;
	private boolean directed;
	private List<VertexNode<T>> vertices;
	Graph(){}
	Graph(int vCount, boolean directed){
		initGraph(vCount, directed);
	}
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


	public List<VertexNode<T>> getVertices() {
		return vertices;
	}


	public void setVertices(List<VertexNode<T>> vertices) {
		this.vertices = vertices;
	}


	public void initGraph(int nVertices, boolean directed) {
		this.directed = directed;
		this.nVertices = nVertices;
		vertices = new ArrayList<VertexNode<T>>();
	}
	
	public void addEdge(T x,T y) {
		addEdge(x,y,this.directed);
	}
	public void addEdge(T x, T y, boolean directed) {
		addEdge(x,y,directed,0);
	}
	
	public void addEdge(T x, T y, boolean directed, int weight) {
		EdgeNode<T> edge = new EdgeNode<T>(getVertexNode(y),weight);
		getVertexNode(x).addEdge(edge);
		getVertexNode(x).incrementDegree();
		if(!directed) {
			addEdge(y,x,true,weight);
		}
		
	}
	
	public VertexNode<T> getVertexNode(T value){
		VertexNode<T> node = null;
		for(VertexNode<T> v : vertices) {
			if(v.getValue().compareTo(value) == 0) {
				node = v;
				break;
			}
		}
		if(node == null) {
			node = new VertexNode(value);
			vertices.add(node);
			Collections.sort(vertices);
		}
		return node;
	}
	
	public void printGraph() {
		for(int i=0;i<vertices.size();i++) {
			System.out.println(vertices.get(i));
		}
	}
	
	public Graph<T> reverseGraph(boolean directed, int vCount) {
		Graph<T> newGraph = new Graph<T>(); 
		newGraph.initGraph(vCount, directed);
		for(VertexNode<T> v : getVertices()) {
			List<T> targetNodes = v.getEdgesTarget();
			for(T target: targetNodes) {
				newGraph.addEdge(target, v.getValue());
			}
		}
		return newGraph;
	}
	
	
	public static void main(String[] args) {
		
		Graph<Integer> g = new Graph<Integer>();
		g.initGraph(5, false);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 0);
		
		g.printGraph();		
	}

}
