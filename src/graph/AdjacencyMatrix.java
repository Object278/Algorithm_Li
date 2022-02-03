package graph;

import java.util.Random;

public class AdjacencyMatrix implements Graph {
	//n�Ƕ�������m�Ǳߵ�����
	private int n, m;
	boolean directed;
	boolean[][] matrix;
	
	public class adjIterator{
		private AdjacencyMatrix adjMatrix;
		int v;
		int index;
		
		public adjIterator(AdjacencyMatrix adjMatrix, int v) {
			this.adjMatrix=adjMatrix;
			this.v=v;
			this.index=0;
		}
		
		public int begin() {
			//Ӧ�ôӾ����ĳһ���е�һ��Ϊtrue��index��ʼ����
			index=-1;
			return next();
		}
		
		public int next() {
			//�ӵ�ǰ���ҵ���һ��ֵΪtrue�ĵ㣨��ǰ�ĵ��ֵ����Ϊtrue����false��
			for(index += 1; index<adjMatrix.matrix[v].length; index++) {
				if(adjMatrix.matrix[v][index]) {
					return index;
				}
			}
			return -1;
		}
		
		public boolean end() {
			return index >= adjMatrix.matrix.length;
		}
	}
	
	public AdjacencyMatrix(int n, boolean directed) {
		this.n=n;
		this.m=0;
		this.directed=directed;
		matrix=new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				matrix[i][j]=false;
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
	//��v��w�ڵ����һ���ߣ����������ͼ���Զ��Ѵ�w��v��Ҳ�����
	public void addEdge(int v, int w) {
		if(v<0 || v>=n || w<0 || w>=n) {
			throw new IllegalArgumentException("Error");
		}
		//�����ȡ����ƽ�б����ָ���
		if(!hasEdge(v, w)) {
			matrix[v][w]=true;
			if(!directed) {
				matrix[w][v]=true;
			}
			m++;
		}
	}
	
	public boolean hasEdge(int v, int w) {
		if(v<0 || v>=n || w<0 || w>=n) {
			throw new IllegalArgumentException("Error");
		}
		return matrix[v][w];
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
				if(matrix[i][j]) {
					System.out.print(1+"  ");
				}else {
					System.out.print(0+"  ");
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
