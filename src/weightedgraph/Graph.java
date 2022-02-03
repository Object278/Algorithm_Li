package weightedgraph;

public interface Graph<T> {
	int vertex();
	int edge();
	void addEdge(int v, int w, T weight );
}
