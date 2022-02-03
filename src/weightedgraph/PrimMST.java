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
			//���������Ҫ���ǵĺ��бߣ��Ǿ��Ȱ�Ȩֵ��С���Ǹ���Ӧ������ȡ����
			int v=ipq.extractMaxIndex();
			//��������Ͷ�Ӧͼ�е�һ���ڵ㣬��������������ҵ���Ӧ�ĺ��бߣ��п����Ҳ���Ϊnull��
			//�������ڵ��м��������ѵ���С���бߣ��Ǿ��Ѿ��ҵ�����С�������е�һ����
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
				//ֻ�Ժ��б߽��д���
				if(!marked[w]) {
					//����֮ǰ��û���ҵ���w�ڵ������ĺ��б�
					if(edgeTo.get(w)==null) {
						//û���ҵ��Ļ���Ҫ���
						ipq.add(w, i.weight());
						edgeTo.set(w, i);
					}else if(i.weight().compareTo(edgeTo.get(w).weight()) < 0) {
						//����µĺ��бߵ�Ȩֵ��С���Ǿ����µ�����ɵ�
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
				//ֻ�Ժ��б߽��д���
				if(!marked[w]) {
					//����֮ǰ��û���ҵ���w�ڵ������ĺ��б�
					if(edgeTo.get(w)==null) {
						//û���ҵ��Ļ���Ҫ���
						ipq.add(w, i.weight());
						edgeTo.set(w, i);
					}else if(i.weight().compareTo(edgeTo.get(w).weight()) < 0) {
						//����µĺ��бߵ�Ȩֵ��С���Ǿ����µ�����ɵ�
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
