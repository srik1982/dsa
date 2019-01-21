package graphs.v2;

public class Edge {
	int src;
	int dest;
	int weight = 0;
	
	Edge(int src, int dest){
		this.src = src;
		this.dest = dest;
	}
	Edge(int src, int dest, int weight){
		this(src,dest);
		this.weight = weight;
	}
	
	/**
	 * Need to figure out the best hashcode technique
	 */
	public int hashCode() {
		return 19*src+31*dest;
	}
	
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Edge))
			return false;
		
		Edge e = (Edge)o;
		return src == e.src && dest == e.dest;
	}
	
	public String toString() {
		return src+"-->"+dest;
	}
}
