package weightedgraph;

public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {
	 
	private int a, b;//��������ͼ��˵���Ǵ�aָ��b�ı�
	private T weight;
	
	public Edge(int a, int b, T weight) {
		this.a=a;
		this.b=b;
		this.weight=weight;
	}
	
	public Edge() {
		
	}
	
	//��ñߵ���ʼ��
	public int v() {
		return a;
	}
	
	//��ñߵ���ֹ��
	public int w() {
		return b;
	}
	
	public T weight() {
		return weight;
	}
	
	//֪������ߵ�һ���ڵ㣬������һ���ڵ�
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
