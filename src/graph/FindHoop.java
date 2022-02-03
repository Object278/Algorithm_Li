package graph;

import java.util.List;

import graph.AdjacencyList.adjIterator;

public class FindHoop<E extends Graph> {
	// ����������㷨Ѱ������ͼ�еĻ�������������C:\Users\12591\JAVA\FileTest\GraphTest\DirectedGraph
	// With Hoop1.TXT
	private E directedGraph;
	private boolean[] visited;
	private int hoopNum;
	private List<List> circlePath;

	/// ����ͼƽ�б߳ɻ���
	public FindHoop(E graph) {
		this.directedGraph = graph;
		hoopNum = 0;
		visited = new boolean[directedGraph.vertex()];

		for (int i = 0; i < directedGraph.vertex(); i++) {
			dfs(i, i);
			for(int j=0; j < directedGraph.vertex(); j++) {
				visited[j]=false;
			}
		}
	}

	// �Խڵ�v����������ȱ�������ʼ����o
	private void dfs(int v, int o) {
		visited[v] = true;
		if (directedGraph.getClass() == AdjacencyMatrix.class) {
			AdjacencyMatrix.adjIterator adj = new AdjacencyMatrix(1, false).new adjIterator(
					(AdjacencyMatrix) directedGraph, v);
			for (int i = adj.begin(); !adj.end(); i = adj.next()) {
				if (i == o) {
					hoopNum++;
				}
				if (!visited[i]) {
					dfs(i, o);
				}
			}
		}
		if (directedGraph.getClass() == AdjacencyList.class) {
			AdjacencyList.adjIterator adj = new AdjacencyList(1, false).new adjIterator((AdjacencyList) directedGraph,
					v);
			for (int i = adj.begin(); !adj.end(); i = adj.next()) {
				if (i == o) {
					hoopNum++;
				}
				if (!visited[i]) {
					dfs(i, o);
				}
			}
		}
	}
	
	public int HoopNum() {
		return hoopNum;
	}

	public static void main(String[] args) {

	}

}
