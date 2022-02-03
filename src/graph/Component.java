package graph;

public class Component<E extends Graph> {
	//������ȱ���������ͨ�����ĸ���
	private E graph;
	private boolean[] visited;
	private int ccount;//��ͨ�����ĸ���
	private int[] id;//�����������ǲ��������������Ľڵ��id��ͬ���������Ĳ�ͬ
	//����ͬһ����ͨ�����еĽڵ��id��ͬ�����ڵ�һ����ͨ������id����1���ڵڶ����ж���2���������ơ�0�����ʼֵ
	
	public Component(E graph) {
		this.graph=graph;
		visited=new boolean[graph.vertex()];
		ccount=0;
		id=new int[graph.vertex()];
		
		//��ȱ���
		for(int i=0; i<graph.vertex(); i++) {
			if(!visited[i]) {
				dfs(i);
				ccount++;
			}
		}
	}
	
	//�Խڵ�v����������ȱ���
	private void dfs(int v) {
		visited[v]=true;
		//ccount�ĳ�ʼֵΪ0������Ҫ+1
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
