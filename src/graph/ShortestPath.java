package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import graph.AdjacencyList.adjIterator;

public class ShortestPath<E extends Graph> {
	
	private E graph;
	private int s;//��ʼ��source
	private boolean[] visited;//ĳһ���ڵ��Ƿ񱻼����˶���
	private int[] from;//ĳ���ڵ��Ǵ���һ���ڵ������
	private int[] ord;//ĳ���ڵ����source����̾���
 
	
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
		//Queue�Ǹ��ӿڣ�����ʵ����������ʵ����һ������Ķ���
		Queue<Integer> q=new LinkedList<>();
		//������ȱ���
		q.add(s);
		visited[s]=true;
		ord[s]=0;
		while(!q.isEmpty()) {
			int v=q.poll();
			if(graph.getClass()==AdjacencyMatrix.class) {
				AdjacencyMatrix.adjIterator adj=new AdjacencyMatrix(1, false).new adjIterator((AdjacencyMatrix) graph, v);
				for(int i=adj.begin(); !adj.end(); i=adj.next()) {
					if( !visited[i] ) {
						//���i�ڵ�û�б����ʣ���ô����Ҫ����v�ڵ����
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
	
	public int length(int w) {
		return ord[w];
	}
		
	public static void main(String[] args) {

	}

}
