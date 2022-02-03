package graph;

import java.util.ArrayList;

public class AdjacencyList implements Graph {
	//n�Ƕ�������m�Ǳߵ�����
	private int n, m;
	boolean directed;
	ArrayList<ArrayList<Integer>> list;
	
	public class adjIterator{
		private AdjacencyList adjList;
		int v;//v�Ǳ������Ľڵ�
		int index;//��ǰ�������Ľڵ�
		
		public adjIterator(AdjacencyList adjList, int v) {
			this.adjList=adjList;
			this.v=v;
			this.index=0;
		}
		
		public int begin() {
			//ÿ�ε���begin��ʱ��index��Ҫ����
			index=0;
			if(adjList.list.get(v).size() != 0) {
				return (int)adjList.list.get(v).get(index);
			}
			return -1;
		}
		
		public int next() {
			index++;
			if( index<adjList.list.get(v).size() ) {;
				return (int)adjList.list.get(v).get(index);
			}
			return -1;
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
			ArrayList<Integer> addList=new ArrayList<Integer>();
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
	public void addEdge(int v, int w) {
		if(v<0 || v>=n || w<0 || w>=n) {
			throw new IllegalArgumentException("Error");
		}
		//����Ҫ�����Ի��ߺ�ƽ�бߵ����⣬ֻ����������ƽ�б�
		list.get(v).add(w);
		//������Ի��߲��Ҳ�������ͼ�Ż�˫�����
		if(v != w && !directed) {
			list.get(w).add(v);
		}
		m++;
	}
	
	public boolean hasEdge(int v, int w) {
		if(v<0 || v>=n || w<0 || w>=n) {
			throw new IllegalArgumentException("Error");
		}
		for(int i=0; i<list.get(v).size(); i++) {
			if((int)list.get(v).get(i) == w) {
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
