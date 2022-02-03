package graph;

import java.util.ArrayList;
import java.util.Stack;

import graph.AdjacencyList.adjIterator;

public class Path<E extends Graph> {
	
	private E graph;
	int s;//�������Դ��source������һ�����·��
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
		
		//Ѱ·�㷨����s��ʼdfs���ҵ����к�s�����ĵ�ͺ���
		dfs(s);
	}
	
	private void dfs(int v) {
		visited[v]=true;
		if(graph.getClass()==AdjacencyMatrix.class) {
			AdjacencyMatrix.adjIterator adj=new AdjacencyMatrix(1, false).new adjIterator((AdjacencyMatrix) graph, v);
			for(int i=adj.begin(); !adj.end(); i=adj.next()) {
				if( !visited[i] ) {
					//���i�ڵ�û�б����ʣ���ô����Ҫ����v�ڵ����
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
	
	//���Դ��s��w��û��·��
	public boolean hasPath(int w) {
		if(w<0 || w>=graph.vertex()) {
			throw new IllegalArgumentException("Index");
		}
		//�����s���ʵ���w������������֮��һ����·��
		return visited[w];
	}
	
	//����s��w��·���洢��һ������֮��
	public void path(int w, ArrayList<Integer> pathList) {
		//��������һ�����ƵĹ��̣�������Ҫʹ��һ��Stack
		Stack<Integer> stack=new Stack<>();
		int p=w;//ע�⣺Դ��s��from�����е�ֵӦ����-1
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
	
	//ֱ�Ӵ�ӡ�����s��w��·��
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
