package graphs.v1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.Comparable;

public class VertexNode<T extends Comparable> implements Comparable{
	private T value;
	private int degree;
	private ArrayList<EdgeNode<T>> edgeNodes = new ArrayList<EdgeNode<T>>();
	
	public VertexNode(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}

	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public void incrementDegree() {
		degree++;
	}
	
	public void addEdge(EdgeNode<T> edgeNode) {
		edgeNodes.add(edgeNode);
	}
	
	public List<VertexNode<T>> getAdjacencyList(){
		List<VertexNode<T>> _list = new ArrayList<VertexNode<T>>();
		for(EdgeNode<T> edge : edgeNodes) {
			_list.add(edge.getTargetVertex());
		}
		return _list;
	}
	
	public int hashCode() {
		return value.hashCode();
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof VertexNode)) return false;
		return value.compareTo(((VertexNode<T>)o).getValue()) == 0;
	}
	
	
	public String toString() {
		return value.toString();
	}

	@Override
	public int compareTo(Object o) {
		if(o == null || !(o instanceof VertexNode))
			return 1;
		Integer val = (Integer)((VertexNode)o).getValue();
		return value.compareTo(val);
	}
	
	public List<T> getEdgesTarget(){
		List<T> edges = new ArrayList<T>();
		for(VertexNode v : getAdjacencyList()) {
			edges.add((T) v.getValue());
		}
		return edges;
	}
	
	
}
