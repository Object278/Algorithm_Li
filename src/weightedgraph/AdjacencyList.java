package weightedgraph;

import java.util.ArrayList;

public class AdjacencyList<T extends Comparable<T>> implements Graph<T> {
	//n�Ƕ�������m�Ǳߵ�����
	private int n, m;
	boolean directed;
	ArrayList<ArrayList<Edge<T>>> list;
	
	public class adjIterator{
		private AdjacencyList<T> adjList;
		int v;//v�Ǳ������Ľڵ�
		int index;//��ǰ�������Ľڵ�
		
		public adjIterator(AdjacencyList<T> adjList, int v) {
			this.adjList=adjList;
			this.v=v;
			this.index=0;
		}
		
		public Edge<T> begin() {
			//ÿ�ε���begin��ʱ��index��Ҫ����
			index=0;
			if(adjList.list.get(v).size() != 0) {
				return adjList.list.get(v).get(index);
			}
			return null;
		}
		
		public Edge<T> next() {
			index++;
			if( index<adjList.list.get(v).size() ) {;
				return adjList.list.get(v).get(index);
			}
			return null;
		}
		
		public boolean end() {
			return index >= adjList.list.get(v).size();
		}
	}
	
	public AdjacencyList(int n, boolean directed) {
		this.n=n;
		this.m=0;
		this.directed=directed;
		list=new ArrayList<>();
		for(int i=0; i<n; i++) {
			ArrayList<Edge<T>> addList=new ArrayList<>();
			list.add(addList);
		}
	}
	
	@Override
	public int vertex() {
		return n;
	}
	
	@Override
	public int edge() {
		return m;
	}
	
	@Override
	//��ʾv��w�ڵ�������
	public void addEdge(int v, int w, T weight) {
		if(v<0 || v>=n || w<0 || w>=n) {
			throw new IllegalArgumentException("Error");
		}
		//������ƽ�б�
		//����Ҫ�����Ի��ߺ�ƽ�бߵ����⣬ֻ����������ƽ�б�
		list.get(v).add(new Edge<T>(v, w, weight));
		//������Ի��߲��Ҳ�������ͼ�Ż�˫�����
		if(v != w && !directed) {
			list.get(w).add(new Edge<T>(v, w, weight));
		}
		m++;
	}
	
	public boolean hasEdge(int v, int w) {
		if(v<0 || v>=n || w<0 || w>=n) {
			throw new IllegalArgumentException("Error");
		}
		for(int i=0; i<list.get(v).size(); i++) {
			if(list.get(v).get(i).other(v) == w) {
				return true;
			}
		}
		return false;
	}
	
	public void show() {
		for(int i=0; i<n; i++) {
			System.out.print("vertex"+i+": ");
			for(int j=0; j<list.get(i).size(); j++) {
				System.out.print(list.get(i).get(j)+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
	}

}
