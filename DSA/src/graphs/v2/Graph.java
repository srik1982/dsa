package graphs.v2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {

	Map<Integer,Vertex> vertices = new HashMap<Integer,Vertex>();
	Map<Integer, Set<Edge>> edges = new HashMap<Integer,Set<Edge>>();
	boolean directed;
	
	public Graph(boolean directed) {
		this.directed = directed;
	}
	
	public void addEdge(int x, int y) {
		Vertex v1 = getVertex(x);
		Vertex v2 = getVertex(y);
		v1.adjacencyList.add(v2);
		if(!directed) {
			v2.adjacencyList.add(v1);
		}
		Set<Edge> edgesFrom = edges.get(x);
		if(edgesFrom == null) {
			edgesFrom = new HashSet<Edge>();
			edges.put(x, edgesFrom);
		}
		edgesFrom.add(new Edge(x,y));
	}
	
	public void addEdge(int x, int y, int weight) {
		Vertex v1 = getVertex(x);
		Vertex v2 = getVertex(y);
		v1.adjacencyList.add(v2);
		if(!directed) {
			v2.adjacencyList.add(v1);
		}
		Set<Edge> edgesFrom = edges.get(x);
		if(edgesFrom == null) {
			edgesFrom = new HashSet<Edge>();
			edges.put(x, edgesFrom);
		}
		edgesFrom.add(new Edge(x,y,weight));
	}
	
	public Collection<Vertex> getAllVertices(){
		return vertices.values();
	}
	
	public List<Edge> getAllEdges(){
		List<Edge> _edges = new ArrayList<>();
		for(Set<Edge> eSet : edges.values()) {
			_edges.addAll(eSet);
		}
		return _edges;
	}
	

//	public Map<Integer, Vertex> getVertexNumber2VertexMap(){
//		HashMap<Integer,Vertex> vn2v = new HashMap<>();
//		for(Vertex _v : vertices) {
//			vn2v.put(_v.data, _v);
//		}
//		return vn2v;
//	}
//	
//	public Map<Integer,Set<Edge>> getEdgeSrc2EdgeMap(){
//		HashMap<Integer,Set<Edge>> edgeSrc2Edge = new HashMap<Integer,Set<Edge>>();
//		for(Edge _e : edges) {
//			Set<Edge> edgesFrom = edgeSrc2Edge.get(_e.src);
//			if(edgesFrom == null) {
//				edgesFrom = new HashSet<Edge>();
//				edgeSrc2Edge.put(_e.src,edgesFrom);
//			}
//			edgesFrom.add(_e);
//		}
//		return edgeSrc2Edge;
//	}
	
	public Map<Integer, Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(Map<Integer, Vertex> vertices) {
		this.vertices = vertices;
	}

	public Map<Integer, Set<Edge>> getEdges() {
		return edges;
	}

	public void setEdges(Map<Integer, Set<Edge>> edges) {
		this.edges = edges;
	}

	public Edge getEdge(int src, int dest) {
		Set<Edge> edgesFrom = edges.get(src);
		Edge matchingEdge = null;
		if(edgesFrom!=null) {
			for(Edge edge : edgesFrom) {
				if(edge.dest == dest) {
					matchingEdge = edge;
					break;
				}
			}
		}
		return matchingEdge;
		
	}
	public Vertex getVertex(int vData) {
		Vertex v = vertices.get(vData);
		
		if(v == null) {
			v = new Vertex(vData);
			vertices.put(vData,v);
		}
		return v;
	}
	
	public Graph reverseGraph() {
		Graph g = new Graph(this.directed);
		for(Set<Edge> edges : edges.values()) {
			for(Edge e : edges)
				g.addEdge(e.dest, e.src);
		}
		return g;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Edges :");
		for(Set<Edge> edges : edges.values()) {
			for(Edge e : edges)
				sb.append(e.toString()).append("\n");
		}
		return sb.toString();
	}
}
