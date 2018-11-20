package skiena.graphs;

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
	private LinkedList<VertexNode<T>> vertices;

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


	public LinkedList<VertexNode<T>> getVertices() {
		return vertices;
	}


	public void setVertices(LinkedList<VertexNode<T>> vertices) {
		this.vertices = vertices;
	}


	public void initGraph(int nVertices, boolean directed) {
		this.directed = directed;
		this.nVertices = nVertices;
		vertices = new LinkedList<VertexNode<T>>();
	}
//	public void initVertexNodes(T v) {
//		vertices.add(new VertexNode<T>(v));
//	}
	
	public void insertEdge(T x,T y) {
		insertEdge(x,y,this.directed);
	}
	public void insertEdge(T x, T y, boolean directed) {
		insertEdge(x,y,directed,0);
	}
	
	public void insertEdge(T x, T y, boolean directed, int weight) {
		EdgeNode<T> edge = new EdgeNode<T>(getVertexNode(y),weight);
		getVertexNode(x).addEdge(edge);
		getVertexNode(x).incrementDegree();
		if(!directed) {
			insertEdge(y,x,true,weight);
		}
		
	}
	
	private VertexNode<T> getVertexNode(T value){
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
	
//	public List<Integer> getAdjacentVertices(int vertex){
//		List<Integer> adjacentVertices = null;
//		for(VertexNode _v : vertices) {
//			if(_v.getValue() == vertex) {
//				adjacentVertices = _v.getAdjacencyList();
//			}
//		}
//		return adjacentVertices;
//	}
	
	public void printGraph() {
		for(int i=0;i<vertices.size();i++) {
			System.out.println(vertices.get(i));
		}
	}
	
	
	public static void main(String[] args) {
		
		Graph<Integer> g = new Graph<Integer>();
		g.initGraph(5, false);
//		
//		g.initVertexNodes(0);
//		g.initVertexNodes(1);
//		g.initVertexNodes(2);
//		g.initVertexNodes(3);
//		g.initVertexNodes(4);
		
		g.insertEdge(0, 1);
		g.insertEdge(0, 2);
		g.insertEdge(1, 2);
		g.insertEdge(1, 3);
		g.insertEdge(2, 3);
		g.insertEdge(3, 4);
		g.insertEdge(4, 0);
		
		g.printGraph();		
	}

}
