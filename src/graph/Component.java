package graph;

public class Component<E extends Graph> {
	//运用深度遍历来求连通分量的个数
	private E graph;
	private boolean[] visited;
	private int ccount;//连通分量的个数
	private int[] id;//求两个基点是不是相连，相连的节点的id相同，不相连的不同
	//所以同一个连通分量中的节点的id相同，当在第一个连通分量中id都是1，在第二个中都是2，依此类推。0代表初始值
	
	public Component(E graph) {
		this.graph=graph;
		visited=new boolean[graph.vertex()];
		ccount=0;
		id=new int[graph.vertex()];
		
		//深度遍历
		for(int i=0; i<graph.vertex(); i++) {
			if(!visited[i]) {
				dfs(i);
				ccount++;
			}
		}
	}
	
	//对节点v进行深度优先遍历
	private void dfs(int v) {
		visited[v]=true;
		//ccount的初始值为0，所以要+1
		id[v]=ccount+1;
		if(graph.getClass()==AdjacencyMatrix.class) {
			AdjacencyMatrix.adjIterator adj=new AdjacencyMatrix(1, false).new adjIterator((AdjacencyMatrix) graph, v);
			for(int i=adj.begin(); !adj.end(); i=adj.next()) {
				if( !visited[i] ) {
					dfs(i);
				}
			}
		}
		if(graph.getClass()==AdjacencyList.class) {
			AdjacencyList.adjIterator adj=new AdjacencyList(1, false).new adjIterator((AdjacencyList) graph, v);
			for(int i=adj.begin(); !adj.end(); i=adj.next()) {
				if( !visited[i] ) {
					dfs(i);
				}
			}
		}
	}
	
	 public boolean isConnevted(int v, int w) {
		 if(v<0 || v>=graph.vertex() || w<0 || w>=graph.vertex()) {
				throw new IllegalArgumentException("Error");
			}
		 return id[v]==id[w];
	 }
	
	public int count() {
		return ccount;
	}
	
	public static void main(String[] args) {

	}

}
