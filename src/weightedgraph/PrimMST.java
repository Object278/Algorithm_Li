package weightedgraph;

import java.util.ArrayList;

import weightedgraph.AdjacencyList.adjIterator;

public class PrimMST<E extends Graph<T>, T extends Comparable<T>> {
	
	private E graph;
	private IndexMaxHeap<T> ipq;
	private ArrayList<Edge<T>> edgeTo;
	private boolean[] marked;
	private ArrayList<Edge<T>> mst;
	
	public PrimMST(E graph) {
		this.graph=graph;
		ipq=new IndexMaxHeap<>(graph.vertex());
		edgeTo=new ArrayList<>(graph.vertex());
		marked=new boolean[graph.vertex()];
		mst=new ArrayList<>(graph.vertex());
		for(int i=0; i<graph.vertex(); i++) {
			edgeTo.add(null);
		}
		
		//Prim
		visit(0);
		while(!ipq.isEmpty()) {
			//如果还有需要考虑的横切边，那就先把权值最小的那个对应的索引取出来
			int v=ipq.extractMaxIndex();
			//这个索引就对应图中的一个节点，可以用这个索引找到相应的横切边（有可能找不到为null）
			//如果这个节点有加入索引堆的最小横切边，那就已经找到了最小生成树中的一个边
			if(edgeTo.get(v) != null) {
				mst.add(edgeTo.get(v));
			}
			visit(v);
		}
	}
	
	private void visit(int v) {
		if(marked[v]) {
			throw new IllegalArgumentException();
		}
		marked[v]=true;
		if(graph.getClass()==AdjacencyMatrix.class) {
			AdjacencyMatrix.adjIterator adj=new AdjacencyMatrix(1, false).new adjIterator((AdjacencyMatrix) graph, v);
			for(Edge<T> i=adj.begin(); !adj.end(); i=adj.next()) {
				int w=i.other(v);
				//只对横切边进行处理
				if(!marked[w]) {
					//看看之前有没有找到和w节点相连的横切边
					if(edgeTo.get(w)==null) {
						//没有找到的话需要添加
						ipq.add(w, i.weight());
						edgeTo.set(w, i);
					}else if(i.weight().compareTo(edgeTo.get(w).weight()) < 0) {
						//如果新的横切边的权值更小，那就用新的替代旧的
						edgeTo.set(w, i);
						ipq.change(w, i.weight());
					}
				}
			}
		}
		if(graph.getClass()==AdjacencyList.class) {
			AdjacencyList.adjIterator adj=new AdjacencyList(1, false).new adjIterator((AdjacencyList) graph, v);
			for(Edge<T> i=adj.begin(); !adj.end(); i=adj.next()) {
				int w=i.other(v);
				//只对横切边进行处理
				if(!marked[w]) {
					//看看之前有没有找到和w节点相连的横切边
					if(edgeTo.get(w)==null) {
						//没有找到的话需要添加
						ipq.add(w, i.weight());
						edgeTo.set(w, i);
					}else if(i.weight().compareTo(edgeTo.get(w).weight()) < 0) {
						//如果新的横切边的权值更小，那就用新的替代旧的
						edgeTo.set(w, i);
						ipq.change(w, i.weight());
					}
				}
			}
		}
	}
	
	public ArrayList<Edge<T>> mstEdges(){
		return mst;
	}
	
	public static void main(String[] args) {

	}

}
