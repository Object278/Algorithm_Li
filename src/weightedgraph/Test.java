package weightedgraph;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		String filename="C:\\Users\\12591\\JAVA\\FileTest\\GraphTest\\MSTTest.TXT";
		AdjacencyList<Double> graph1=new AdjacencyList<>(8, false);
		ReadGraph<AdjacencyList<Double>, Double> readgraph1=new ReadGraph<>(graph1, filename);
		LazyPrimMST<AdjacencyList<Double>, Double> lazyprim=new LazyPrimMST<>(graph1);
		ArrayList<Edge<Double>> mst=new ArrayList<>();
		mst=lazyprim.mstEdges();
		System.out.println("Lazy Prim: ");
		for(int i=0; i<mst.size(); i++) {
			System.out.println(mst.get(i).toString());
		}
		System.out.println();
		System.out.println("Prim: ");
		PrimMST<AdjacencyList<Double>, Double> prim=new PrimMST<>(graph1);
		ArrayList<Edge<Double>> mst1=new ArrayList<>();
		mst1=prim.mstEdges();
		for(int i=0; i<mst1.size(); i++) {
			System.out.println(mst1.get(i).toString());
		}
		System.out.println();
		KruskalMST<AdjacencyList<Double>, Double> kruskal=new KruskalMST<>(graph1);
		ArrayList<Edge<Double>> mst2=new ArrayList<>();
		mst2=kruskal.mstEdges();
		System.out.println("Kruskal: ");
		for(int i=0; i<mst.size(); i++) {
			System.out.println(mst2.get(i).toString());
		}
	}

}
