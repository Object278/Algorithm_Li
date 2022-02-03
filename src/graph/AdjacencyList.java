package graph;

import java.util.ArrayList;

public class AdjacencyList implements Graph {
	//n是顶点数，m是边的数量
	private int n, m;
	boolean directed;
	ArrayList<ArrayList<Integer>> list;
	
	public class adjIterator{
		private AdjacencyList adjList;
		int v;//v是被遍历的节点
		int index;//当前遍历到的节点
		
		public adjIterator(AdjacencyList adjList, int v) {
			this.adjList=adjList;
			this.v=v;
			this.index=0;
		}
		
		public int begin() {
			//每次调用begin的时候index都要归零
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
	//表示v和w节点有连接
	public void addEdge(int v, int w) {
		if(v<0 || v>=n || w<0 || w>=n) {
			throw new IllegalArgumentException("Error");
		}
		//这里要考虑自环边和平行边的问题，只能先允许有平行边
		list.get(v).add(w);
		//如果非自环边并且不是有向图才会双向添加
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
