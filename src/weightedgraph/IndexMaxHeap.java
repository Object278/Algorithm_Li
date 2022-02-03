package weightedgraph;

import java.util.ArrayList;
import java.util.Random;

public class IndexMaxHeap<E extends Comparable<E>> {

	// 用动态数组实现一个二叉最大堆，索引是从1开始的
	private ArrayList<E> data;
	private ArrayList<Integer> indexes;
	private ArrayList<Integer> reverse;
	private int capacity;// 可加可不加的成员属性
	private int count;//存储堆中目前有几个元素，指向indexes数组中最后一个元素

	public IndexMaxHeap(int capacity) {
		this.capacity = capacity;
		data = new ArrayList<>(capacity+1);
		indexes = new ArrayList<>(capacity+1);
		reverse= new ArrayList<>(capacity+1);
		this.count=0;
		for(int i=0; i<capacity+1; i++) {
			//如果某个索引不存在的话，默认值为-1
			reverse.add(0);
			data.add(null);
			indexes.add(0);
		}
	}
//		添加capacity变量之后为了添加操作的安全性而停止使用
//		public IndexMaxHeap() {
//			data=new ArrayList<>();
//			indexes=new ArrayList<>();
//			this.capacity=data.size();
//		}

	// 将一个数组转换为堆，仍需修改为符合索引堆的代码
//		public IndexMaxHeap(E[] arr) {
//			this.capacity=arr.length;
//			data=new ArrayList<>();
//			indexes=new ArrayList<>();
//			for(int i=0; i<arr.length; i++) {
//				data.set(i, arr[i]);
//				indexes.set(i, i);
//			}
//			//从最后一个非叶子节点进行siftDown
//			for(int i=parent(arr.length-1); i>=0; i++) {
//				siftDown(i);
//			}
//		}

	public int size() {
		return data.size();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	// 返回在完全二叉树的数组表示中一个索引的元素的父亲节点的index值
	private int parent(int index) {
		if (index == 0) {
			throw new IllegalArgumentException("no parent");
		}
		return index / 2;
	}

	// 返回在完全二叉树的数组表示中一个索引的元素的右子节点的index值
	private int leftChild(int index) {
		return index * 2;
	}

	// 返回在完全二叉树的数组表示中一个索引的元素的左子节点的index值
	private int rightChild(int index) {
		return index * 2 + 1;
	}

	// 交换indexes数组中两个元素的位置
	private void swap(int i, int j) {
		if (i < 0 || i >= data.size() || j < 0 || j >= data.size()) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		int temp = indexes.get(i);
		indexes.set(i, indexes.get(j));
		indexes.set(j, temp);
	}

	// 向堆中添加元素
	// index是从1开始索引的，外部用户传入的参数是从0开始索引的
	public void add(int index, E val) {
		if (index < 0 || index > capacity - 1) {
			throw new IllegalArgumentException("index out of bound");
		}
		index+=1;
		count++;
		data.set(index, val);
		indexes.set(count, index);
		reverse.set(index, count);
		siftUp(count);
	}

	// 将索引为i的索引进行上传的方法
	private void siftUp(int i) {
		// 只有节点不是根节点并且比父亲节点大才上浮
		while (i > 1 && data.get(indexes.get(parent(i))).compareTo(data.get(indexes.get(i))) < 0) {
			swap(indexes.get(i), indexes.get(parent(i)));
			//并没有太理解下面两行
			reverse.set(indexes.get(parent(i)), parent(i));
			reverse.set(indexes.get(i), i);
			i = parent(i);
			// 在新的位置上还要继续这个过程
		}
	}

	// 看一下堆中的最大元素
	public E findMax() {
		if (data.size() == 0) {
			throw new IllegalArgumentException("Heap is empty");
		}
		return data.get(indexes.get(1));
	}

	// 取出堆中的最大元素
	public E extractMax() {
		E ret = findMax();
		swap(0, count);
		reverse.set(indexes.get(1), 1);
		//堆顶的元素之后需要被删除，所以直接改成默认值
		reverse.set(indexes.get(count), 0);
		data.remove(count);
		count--;
		siftDown(1);
		return ret;
	}

	// 取出堆中的最大元素的索引
	public int extractMaxIndex() {
		int ret = indexes.get(0);
		swap(0, count);
		reverse.set(indexes.get(1), 1);
		//堆顶的元素之后需要被删除，所以直接改成默认值
		reverse.set(indexes.get(count), 0);
		count--;
		siftDown(1);
		return ret;
	}

	// 将第i位的元素进行下沉
	private void siftDown(int i) {
		// 循环结束条件：1. 第i位是叶子节点，只要不满足这个条件就有可能继续下沉，需要在循环里判断
		// 核心思路：先取出来左右子节点的最小值，之后将现在的节点与最小值进行比较
		while (leftChild(i) < count+1) {
			int j = leftChild(i);
			System.out.println(i);
			System.out.println(indexes.get(j + 1));
			System.out.println("b"+data.get(indexes.get(j + 1)));
			System.out.println("c"+data.get(indexes.get(j)));
			// j是左右子节点中较大者对应的索引，j+1就能从左节点变到右节点
			if (j + 1 < count + 1 && data.get(indexes.get(j + 1)).compareTo(data.get(indexes.get(j))) > 0) {
				j = rightChild(i);
			}
			if (data.get(indexes.get(i)).compareTo(data.get(indexes.get(j))) > 0) {
				break;
			} else {
				swap(i, j);
				reverse.set(indexes.get(i), i);
				reverse.set(indexes.get(j), j);
			}
			// 之后从新位置继续循环
			i = j;
		}
	}

	// 取出堆中的最大元素并且替换成元素val
	public E replace(E val) {
		E ret = findMax();
		data.set(0, val);
		siftDown(0);
		return ret;
	}
	
	//给定一个元素对应的index，拿出这个元素
	public E getData(int index) {
		if(!contain(index)) {
			throw new IllegalArgumentException("index out of bound");
		}
		return data.get(index);
	}
	
	//给定一个元素对应的index，修改这个元素
	//这是一个O(logn)级别的
	public void change(int index, E newVal) {
		if(!contain(index)) {
			throw new IllegalArgumentException("index out of bound");
		}
		data.set(index, newVal);
		//为了维护堆的性质，需要找到indexes[j]=i，j表示data[i]在堆中的位置
		//之后尝试siftUp(j)，和siftDown(j)
//		for(int j=0; j<data.size()-1; j++) {
//			if(indexes.get(j)==index) {
//				siftUp(j);
//				siftDown(j);
//				return;
//			}
//		}
		int j=reverse.get(index);
		siftUp(j);
		siftUp(j);
	}
	
	//判断某个index对应的元素是不是堆中的元素（在0-capacity之间的index不一定在堆内）
	public boolean contain(int index) {
		if(index < 0 || index > count) {
			throw new IllegalArgumentException("Index out of bound");
		}
		return reverse.get(index) != 0;
	}
	
	public static void main(String[] args) {
			int n=10;
			IndexMaxHeap<Integer> imh=new IndexMaxHeap<>(10);
			Random random=new Random();
			for(int i=0; i<n; i++) {
				imh.add(i, random.nextInt(Integer.MAX_VALUE));
			}
			//用堆的“取出最大值”进行排序
			int[] arr=new int[n];
			for(int i=0; i<n; i++) {
				System.out.println("a "+i);
				arr[i]=imh.extractMax();
			}
			//每次看相邻的两个值，只用比较n-1次
			for(int i=1; i<n; i++) {
				if(arr[i-1] < arr[i]) {
					throw new IllegalArgumentException("Error");
				}
			}
			System.out.println("Test MaxHeap completed");
			
	}
}
