package graph;

public class Test {

	public static void main(String[] args) {
		String filename="C:\\Users\\12591\\JAVA\\FileTest\\GraphTest\\Graph2.TXT";
		AdjacencyList graph=new AdjacencyList(7, false);
		ReadGraph readgraph1=new ReadGraph(graph, filename);
		
		ShortestPath<AdjacencyList> bfs=new ShortestPath<>(graph, 0);
		System.out.print("BFS:  ");
		bfs.showPath(6);
		
		System.out.println();
		
		Path<AdjacencyList> dfs=new Path<>(graph, 0);
		System.out.print("DFS:  ");
		dfs.showPath(6);
		
//		String filename="C:\\Users\\12591\\JAVA\\FileTest\\GraphTest\\DirectedGraph With Hoop1.TXT";
//		AdjacencyList graph=new AdjacencyList(4, true);
//		ReadGraph readgraph=new ReadGraph(graph, filename);
//		FindHoop fh=new FindHoop(graph);
//		System.out.println(fh.HoopNum());
		
		
		
	}

}
