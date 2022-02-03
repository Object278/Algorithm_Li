package weightedgraph;

public class UnionFind5 implements UF{
	//路径压缩优化方法：将比较高的树压缩成比较低的树，最理想的情况是高度为2
	//路径压缩方法一：发生在find函数中，parent[p]=parent[parent[p]];
	//一种经典和常用的优化
	private int[] parent;
	private int[] rank; 
	//在添加了路径压缩之后，rank就不在代表树的深度了，只是用它参考一下每一个树的“排名”
	//粗略的知道排名在并查集里里就够用了
	
	public UnionFind5(int size) {
		parent=new int[size];
		rank=new int[size];
		for(int i=0; i<size; i++) {
			parent[i]=i;
			//初始状况是，每一个元素自己的树的高度为1
			rank[i]=1;
		}
	}
	
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	@Override
	public void unionElements(int p, int q) {
		int pRoot=find(p);
		int qRoot=find(q);
		if(pRoot == qRoot) {
			return;
		}
		//如果两个元素不是一个集合的话，把它们的根节点合并
		if(rank[pRoot] < rank[qRoot]) {
			//深度低的树的根节点指向深度高的树的根节点
			parent[pRoot]=qRoot;
			//这样合并以后的树的深度仍然是深度比较深的树的深度，因为新产生的子树最多和深度比较深的树一样深
			//这样树的深度增加的不会那么快
		}else if(rank[qRoot] < rank[pRoot]){
			parent[qRoot]=pRoot;
		}else {
			//深度相同的时候，可以随便指，但是深度要加一
			parent[qRoot] = pRoot;
			rank[pRoot] += 1;
		}
	}
	
	private int find(int index) {
		if(index < 0 || index > parent.length) {
			throw new IllegalArgumentException("index is illegal");
		}
		while(index != parent[index]) {
			parent[index]=parent[parent[index]];
			index=parent[index];
		}
		return index;
	}

	@Override
	public int getSize() {
		return parent.length;
	}
	
	public static void main(String[] args) {
		
		
	}

}
