package graphs.v2;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	int data;
	List<Vertex> adjacencyList = new ArrayList<Vertex>();
	
	Vertex(){}
	Vertex(int data){
		this.data = data;
	}
	
	public int hashCode() {
		return data;
	}
	
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Vertex))
			return false;
		Vertex v2 = (Vertex) o;
		return data == v2.data;
	}
	
	public String toString() {
		return String.valueOf(data);
	}
}
