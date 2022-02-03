package weightedgraph;

import java.util.ArrayList;

public class LazyPrimMST<E extends Graph<T>, T extends Comparable<T>> {
	
	private E graph;
	//在Edge类的compareTo方法中已经把最大堆变成最小堆了
	private MaxHeap<Edge<T>> pq;
	private boolean[] marked;//表示某个节点是不是在切分后最小生成树所在的那一部分
	private ArrayList<Edge<T>> mst;
	
	public LazyPrimMST(E graph) {
		this.graph=graph;
		pq=new MaxHeap<>(graph.edge());
		marked=new boolean[graph.vertex()];
		mst=new ArrayList<>(graph.edge()-1);
		
		//Lazy Prim
		visit(0);
		while(!pq.isEmpty()) {
			Edge<T> e=pq.extractMax();
			//判断是不是横切边，不是就直接跳过
			if(marked[e.v()]==marked[e.w()]) {
				continue;
			}
			 mst.add(e);
			 //找另一部分的端点
			 if(!marked[e.v()]) {
				 visit(e.v());
			 }else {
				 visit(e.w());
			 }
		}
	}
	
	private void visit(int v) {
		if( marked[v] ) {
			throw new IllegalArgumentException("Index");
		}
		marked[v]=true;
		if(graph.getClass()==AdjacencyMatrix.class) {
			AdjacencyMatrix.adjIterator adj=new AdjacencyMatrix(1, false).new adjIterator((AdjacencyMatrix) graph, v);
			for(Edge<T> i=adj.begin(); !adj.end(); i=adj.next()) {
				if(!marked[i.other(v)]) {
					//如果这个边的另一个顶点没有被标记，那就找到了潜在的横切边
					pq.add(i);
				}
			}
		}
		if(graph.getClass()==AdjacencyList.class) {
			AdjacencyList.adjIterator adj=new AdjacencyList(1, false).new adjIterator((AdjacencyList) graph, v);
			for(Edge<T> i=adj.begin(); !adj.end(); i=adj.next()) {
				if(!marked[i.other(v)]) {
					//如果这个边的另一个顶点没有被标记，那就找到了潜在的横切边
					pq.add(i);
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
