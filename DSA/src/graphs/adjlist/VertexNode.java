package graphs.adjlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VertexNode {
	private int x;
	private int degree;
	private LinkedList<EdgeNode> edgeNodes = new LinkedList<EdgeNode>();
	
	public VertexNode(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
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
	
	public void addEdge(EdgeNode edgeNode) {
		edgeNodes.add(edgeNode);
	}
	
	public List<Integer> getAdjacencyList(){
		List<Integer> _list = new ArrayList<Integer>();
		for(EdgeNode edge : edgeNodes) {
			_list.add(edge.getY());
		}
		return _list;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("\n");
		
		for(int i=0;i<edgeNodes.size();i++) {
			sb.append(x).append("---").append(edgeNodes.get(i).getY());
			sb.append(" | ").append(edgeNodes.get(i).getWeight()).append("\n");
		}
		return sb.toString();
	}
	
	
	
}
