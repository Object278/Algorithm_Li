package graph;

import java.util.ArrayList;
import java.util.Stack;

import graph.AdjacencyList.adjIterator;

public class Path<E extends Graph> {
	
	private E graph;
	int s;//求出从起源点source到任意一个点的路径
	boolean[] visited;
	int[] from;
	
	public Path(E graph, int s) {
		if(s<0 || s>=graph.vertex()) {
			throw new IllegalArgumentException("Index");
		}
		this.graph=graph;
		visited=new boolean[graph.vertex()];
		from=new int[graph.vertex()];
		for(int i=0; i<graph.vertex(); i++) {
			from[i]=-1;
		}
		this.s=s;
		
		//寻路算法：从s开始dfs，找到所有和s相连的点就好了
		dfs(s);
	}
	
	private void dfs(int v) {
		visited[v]=true;
		if(graph.getClass()==AdjacencyMatrix.class) {
			AdjacencyMatrix.adjIterator adj=new AdjacencyMatrix(1, false).new adjIterator((AdjacencyMatrix) graph, v);
			for(int i=adj.begin(); !adj.end(); i=adj.next()) {
				if( !visited[i] ) {
					//如果i节点没有被访问，那么它将要被从v节点访问
					from[i]=v;
					dfs(i);
				}
			}
		}
		if(graph.getClass()==AdjacencyList.class) {
			AdjacencyList.adjIterator adj=new AdjacencyList(1, false).new adjIterator((AdjacencyList) graph, v);
			for(int i=adj.begin(); !adj.end(); i=adj.next()) {
				if( !visited[i] ) {
					from[i]=v;
					dfs(i);
				}
			}
		}
	}
	
	//求从源点s到w有没有路径
	public boolean hasPath(int w) {
		if(w<0 || w>=graph.vertex()) {
			throw new IllegalArgumentException("Index");
		}
		//如果从s访问到了w，那它们两个之间一定有路了
		return visited[w];
	}
	
	//将从s到w的路径存储到一个数组之中
	public void path(int w, ArrayList<Integer> pathList) {
		//由于这是一个倒推的过程，所以需要使用一个Stack
		Stack<Integer> stack=new Stack<>();
		int p=w;//注意：源点s在from数组中的值应该是-1
		while(p != -1) {
			stack.push(p);
			p=from[p];
		}
		pathList.clear();
		while(!stack.isEmpty()) {
			pathList.add(stack.peek());
			stack.pop();
		}
	}
	
	//直接打印输出从s到w的路径
	public void showPath(int w) {
		ArrayList<Integer> pathList=new ArrayList<>();
		path(w, pathList);
		for(int i=0; i<pathList.size(); i++) {
			System.out.print(pathList.get(i));
			if(i != pathList.size()-1) {
				System.out.print(" -> ");
			}
		}
	}
	
	public static void main(String[] args) {

	}

}
