package graphs.v1;

public class EdgeNode<T extends Comparable> {
	public VertexNode<T> target;                                                                                                                                                                                      
	public int weight;

	public EdgeNode(VertexNode<T> targetNode, int weight) {
		this.target = targetNode;
		this.weight=weight;
	}
	
	public VertexNode<T> getTargetVertex() {
		return target;
	}


	public void setTarget(VertexNode<T> target) {
		this.target = target;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public String toString() {
		return "target = "+target+" , weight= "+weight;
	}
}