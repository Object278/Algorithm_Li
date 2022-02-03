package weightedgraph;

import java.util.ArrayList;

	public class MaxHeap<E  extends Comparable<E>> {
	//用动态数组实现一个二叉最大堆
		private ArrayList<E> data;
		
		public MaxHeap(int capacity) {
			data=new ArrayList<>(capacity);
		}
		
		public MaxHeap() {
			data=new ArrayList<>();
		}
		//将一个数组转换为堆
		public MaxHeap(E[] arr) {
			data=new ArrayList<>();
			for(int i=0; i<arr.length; i++) {
				data.set(i, arr[i]);
			}
			//从最后一个非叶子节点进行siftDown
			for(int i=parent(arr.length-1); i>=0; i++) {
				siftDown(i);
			}
		}
		
		public int size() {
			return data.size();
		}
		
		public boolean isEmpty() {
			return data.isEmpty();
		}
		//返回在完全二叉树的数组表示中一个索引的元素的父亲节点的index值
		private int parent(int index) {
			if(index==0) {
				throw new IllegalArgumentException("no parent");
			}
			return  (index-1)/2;
		}
		//返回在完全二叉树的数组表示中一个索引的元素的右子节点的index值
		private int leftChild(int index) {
			return index*2+1;
		}
		//返回在完全二叉树的数组表示中一个索引的元素的左子节点的index值
		private int rightChild(int index) {
			return index*2+2;
		}
		//交换数组中两个元素的位置
		
		private void swap(int i, int j) {
			if(i<0 || i>=data.size() || j<0 || j>=data.size()) {
				throw new IllegalArgumentException("Illegal Argument");
			}
			E temp=data.get(i);
			data.set(i, data.get(j));
			data.set(j, temp);
		}
		
		//向堆中添加元素
		public void add(E val) {
			data.add(val);
			siftUp(data.size()-1);
		}
		//将索引为i的元素进行上传的方法
		private void siftUp(int i) {
			//只有节点不是根节点并且比父亲节点大才上浮
			while(i>0 && data.get(parent(i)).compareTo(data.get(i)) < 0) {
				swap(i, parent(i));
				i=parent(i);
				//在新的位置上还要继续这个过程
			}
		}
		//看一下堆中的最大元素
		public E findMax() {
			if(data.size()==0) {
				throw new IllegalArgumentException("Heap is empty");
			}
			return data.get(0);
		}
		//取出堆中的最大元素
		public E extractMax() {
			E ret=findMax();
			swap(0, data.size()-1);
			data.remove(data.size()-1);
			siftDown(0);
			return ret;
		}
		//将第i位的元素进行下沉
		private void siftDown(int i) {
			//循环结束条件：1. 第i位是叶子节点，只要不满足这个条件就有可能继续下沉，需要在循环里判断
			//核心思路：先取出来左右子节点的最大值，之后将现在的节点与最大值进行比较
			while(leftChild(i) < data.size()) {
				int j=leftChild(i);
				//j是左右子节点中较大者对应的索引，j+1就能从左节点变到右节点
				if(j+1 < data.size() && data.get(j+1).compareTo(data.get(j)) > 0) {
					j=rightChild(i);
				}
				if(data.get(i).compareTo(data.get(j)) > 0) {
					break;
				}else {
					swap(i, j);
				}
				//之后从新位置继续循环
				i=j;
			}
		}
		//取出堆中的最大元素并且替换成元素val
		public E replace(E val) {
			E ret=findMax();
			data.set(0, val);
			siftDown(0);
			return ret;
		}
	
	public static void main(String[] args) {
		
	}
}

