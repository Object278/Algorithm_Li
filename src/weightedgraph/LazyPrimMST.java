package weightedgraph;

import java.util.ArrayList;

public class LazyPrimMST<E extends Graph<T>, T extends Comparable<T>> {
	
	private E graph;
	//��Edge���compareTo�������Ѿ������ѱ����С����
	private MaxHeap<Edge<T>> pq;
	private boolean[] marked;//��ʾĳ���ڵ��ǲ������зֺ���С���������ڵ���һ����
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
			//�ж��ǲ��Ǻ��бߣ����Ǿ�ֱ������
			if(marked[e.v()]==marked[e.w()]) {
				continue;
			}
			 mst.add(e);
			 //����һ���ֵĶ˵�
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
					//�������ߵ���һ������û�б���ǣ��Ǿ��ҵ���Ǳ�ڵĺ��б�
					pq.add(i);
				}
			}
		}
		if(graph.getClass()==AdjacencyList.class) {
			AdjacencyList.adjIterator adj=new AdjacencyList(1, false).new adjIterator((AdjacencyList) graph, v);
			for(Edge<T> i=adj.begin(); !adj.end(); i=adj.next()) {
				if(!marked[i.other(v)]) {
					//�������ߵ���һ������û�б���ǣ��Ǿ��ҵ���Ǳ�ڵĺ��б�
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
