package graph;

import java.util.List;

import graph.AdjacencyList.adjIterator;

public class FindHoop<E extends Graph> {
	// 用深度优先算法寻找有向图中的环，测试用例：C:\Users\12591\JAVA\FileTest\GraphTest\DirectedGraph
	// With Hoop1.TXT
	private E directedGraph;
	private boolean[] visited;
	private int hoopNum;
	private List<List> circlePath;

	/// 有向图平行边成环便
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

	// 对节点v进行深度优先遍历，起始点是o
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
