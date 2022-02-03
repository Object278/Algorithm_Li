package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import graph.AdjacencyList.adjIterator;

public class ShortestPath<E extends Graph> {
	
	private E graph;
	private int s;//起始点source
	private boolean[] visited;//某一个节点是否被加入了队列
	private int[] from;//某个节点是从哪一个节点过来的
	private int[] ord;//某个节点距离source的最短距离
 
	
	public ShortestPath(E graph, int s) {
		if(s < 0 || s >= graph.vertex()) {
			throw new IllegalArgumentException("Index");
		}
		visited=new boolean[graph.vertex()];
		from=new int[graph.vertex()];
		ord=new int[graph.vertex()];
		for(int i=0; i<graph.vertex(); i++) {
			from[i]=-1;
			ord[i]=-1;
		}
		this.s=s;
		//Queue是个接口，不能实例化，可以实例化一个子类的对象
		Queue<Integer> q=new LinkedList<>();
		//广度优先遍历
		q.add(s);
		visited[s]=true;
		ord[s]=0;
		while(!q.isEmpty()) {
			int v=q.poll();
			if(graph.getClass()==AdjacencyMatrix.class) {
				AdjacencyMatrix.adjIterator adj=new AdjacencyMatrix(1, false).new adjIterator((AdjacencyMatrix) graph, v);
				for(int i=adj.begin(); !adj.end(); i=adj.next()) {
					if( !visited[i] ) {
						//如果i节点没有被访问，那么它将要被从v节点访问
						q.add(i);
						visited[i]=true;
						from[i]=v;
						ord[i]=ord[v]+1;
					}
				}
			}
			if(graph.getClass()==AdjacencyList.class) {
				AdjacencyList.adjIterator adj=new AdjacencyList(1, false).new adjIterator((AdjacencyList) graph, v);
				for(int i=adj.begin(); !adj.end(); i=adj.next()) {
					if( !visited[i] ) {
						from[i]=v;
						q.add(i);
						visited[i]=true;
						ord[i]=ord[v]+1;
					}
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
	
	public int length(int w) {
		return ord[w];
	}
		
	public static void main(String[] args) {

	}

}
