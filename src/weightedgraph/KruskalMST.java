package weightedgraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import weightedgraph.AdjacencyList.adjIterator;

public class KruskalMST<E extends Graph<T>, T extends Comparable<T>> {

	private ArrayList<Edge<T>> mst;

	public KruskalMST(E graph) {
		ArrayList<Edge<T>> edges = new ArrayList<>(graph.edge());
		mst=new ArrayList<>(graph.vertex()-1);
		for (int i = 0; i < graph.vertex(); i++) {
			if (graph.getClass() == AdjacencyMatrix.class) {
				AdjacencyMatrix.adjIterator adj = new AdjacencyMatrix(1, false).new adjIterator((AdjacencyMatrix) graph,
						i);
				for (Edge<T> j = adj.begin(); !adj.end(); j = adj.next()) {
					//由于在无向图中每个边被存了两遍（1-2和2-1），所以在添加的时候需要判断一下，只添加一半
					if(j.v() < j.w()) {
						edges.add(j);
					}
				}
			}
			if (graph.getClass() == AdjacencyList.class) {
				AdjacencyList.adjIterator adj = new AdjacencyList(1, false).new adjIterator((AdjacencyList) graph, i);
				for (Edge<T> j = adj.begin(); !adj.end(); j = adj.next()) {
					//由于在无向图中每个边被存了两遍（1-2和2-1），所以在添加的时候需要判断一下，只添加一半
					if(j.v() < j.w()) {
						edges.add(j);
					}
				}
			}
		}
		Collections.sort(edges, new Comparator<Edge<T>>(){
			@Override
			public int compare(Edge<T> o1, Edge<T> o2) {
				return o1.weight().compareTo(o2.weight());
			}
		});
		UnionFind5 uf=new UnionFind5(graph.vertex());
		for(int i=0; i<edges.size() && mst.size()<graph.vertex()-1; i++) {
			Edge<T> e=edges.get(i);
			if(uf.isConnected(e.v(), e.w())) {
				continue;
			}
			mst.add(e);
			uf.unionElements(e.v(), e.w());
		}
		
	}

	public ArrayList<Edge<T>> mstEdges() {
		return mst;
	}

	public static void main(String[] args) {

	}

}
