package weightedgraph;

public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {
	 
	private int a, b;//对于有向图来说，是从a指向b的边
	private T weight;
	
	public Edge(int a, int b, T weight) {
		this.a=a;
		this.b=b;
		this.weight=weight;
	}
	
	public Edge() {
		
	}
	
	//获得边的起始点
	public int v() {
		return a;
	}
	
	//获得边的终止点
	public int w() {
		return b;
	}
	
	public T weight() {
		return weight;
	}
	
	//知道这个边的一个节点，返回另一个节点
	public int other(int x) {
		if(x != a && x != b) {
			throw new IllegalArgumentException("Index Error");
		}
		return x==a ? b : a; 
	}
	
	@Override
	public int compareTo(Edge<T> edge1) {
		return edge1.weight.compareTo(this.weight);
	}

	@Override
	public String toString() {
		return "{ "+this.a+"--"+this.b+" Weight: "+this.weight+" }";
	}

	public static void main(String[] args) {

	}


}
