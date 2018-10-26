package graphs.adjlist;

public class EdgeNode {
	public int y;
	public int weight;

	public EdgeNode(int y, int weight) {
		this.y=y;
		this.weight=weight;
	}
	
	
	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public String toString() {
		return "Y = "+y+" , weight= "+weight;
	}
}