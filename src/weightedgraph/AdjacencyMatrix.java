package weightedgraph;

public class AdjacencyMatrix<T extends Comparable<T>> implements Graph<T> {
	//n是顶点数，m是边的数量
	private int n, m;
	boolean directed;
	Edge<T>[][] matrix;
	
	//遍历一个节点的相邻结点的迭代器
	public class adjIterator{
		private AdjacencyMatrix<T> adjMatrix;
		int v;//v是被遍历的节点
		int index;//当前遍历到的节点
		
		public adjIterator(AdjacencyMatrix<T> adjMatrix, int v) {
			this.adjMatrix=adjMatrix;
			this.v=v;
			this.index=0;
		}
		
		public Edge<T> begin() {
			//应该从矩阵的某一行中第一个为true的index开始遍历
			index=-1;
			return next();
		}
		
		public Edge<T> next() {
			//从当前点找到下一个值为true的点（当前的点的值可以为true或者false）
			for(index += 1; index<adjMatrix.matrix[v].length; index++) {
				if(hasEdge(v, index)) {
					return matrix[v][index];
				}
			}
			return null;
		}
		
		public boolean end() {
			return index >= adjMatrix.matrix.length;
		}
	}
	
	public AdjacencyMatrix(int n, boolean directed) {
		this.n=n;
		this.m=0;
		this.directed=directed;
		matrix=new Edge[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				matrix[i][j]=null;
			}
		}
	}
	
	@Override
	public int vertex() {
		return n;
	}
	
	@Override
	public int edge() {
		return m;
	}
	
	@Override
	//从v到w节点添加一条边，如果是无向图就自动把从w到v的也添加上
	public void addEdge(int v, int w, T weight) {
		if(v<0 || v>=n || w<0 || w>=n) {
			throw new IllegalArgumentException("Error");
		}
		//如果边重复了话，那么新的边替代旧的边
		if(hasEdge(v, w)) {
			matrix[v][w]=null;
			if(!directed) {
				matrix[w][v]=null;
			}
		}
		matrix[v][w]=new Edge<T>(v, w, weight);
		if(!directed) {
			matrix[w][v]=new Edge<T>(w, v, weight);
		}
		m++;
	}
	
	public boolean hasEdge(int v, int w) {
		if(v<0 || v>=n || w<0 || w>=n) {
			throw new IllegalArgumentException("Error");
		}
		return matrix[v][w] != null;
	}
	
	public void show() {
		System.out.print("    ");
		for(int i=0; i<n; i++) {
			System.out.print(i+"  ");
		}
		System.out.println();
		for(int i=0; i<n; i++) {
			System.out.print(i+"  ");
			for(int j=0; j<n; j++) {
				if(hasEdge(i, j)) {
					System.out.print(matrix[i][j].weight()+"  ");
				}else {
					System.out.print("null  ");
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
//		int N=20, M=300;
//		Random random=new Random();
//		AdjacencyMatrix am=new AdjacencyMatrix(N, false);
//		for(int i=0; i<M; i++) {
//			int a=random.nextInt(N);
//			int b=random.nextInt(N);
//			am.addEdge(a, b);
//		}
//		for(int j=0; j<N; j++) {
//			System.out.println(j+" : ");
//			adjMatrixIterator adj=new adjMatrixIterator(am, j);
//			for(int w=adj.begin(); !adj.end(); w=adj.next()) {
//				System.out.println(w+" ");
//			}
//		}
	}

}
