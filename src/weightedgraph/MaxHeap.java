package weightedgraph;

import java.util.ArrayList;

	public class MaxHeap<E  extends Comparable<E>> {
	//�ö�̬����ʵ��һ����������
		private ArrayList<E> data;
		
		public MaxHeap(int capacity) {
			data=new ArrayList<>(capacity);
		}
		
		public MaxHeap() {
			data=new ArrayList<>();
		}
		//��һ������ת��Ϊ��
		public MaxHeap(E[] arr) {
			data=new ArrayList<>();
			for(int i=0; i<arr.length; i++) {
				data.set(i, arr[i]);
			}
			//�����һ����Ҷ�ӽڵ����siftDown
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
		//��������ȫ�������������ʾ��һ��������Ԫ�صĸ��׽ڵ��indexֵ
		private int parent(int index) {
			if(index==0) {
				throw new IllegalArgumentException("no parent");
			}
			return  (index-1)/2;
		}
		//��������ȫ�������������ʾ��һ��������Ԫ�ص����ӽڵ��indexֵ
		private int leftChild(int index) {
			return index*2+1;
		}
		//��������ȫ�������������ʾ��һ��������Ԫ�ص����ӽڵ��indexֵ
		private int rightChild(int index) {
			return index*2+2;
		}
		//��������������Ԫ�ص�λ��
		
		private void swap(int i, int j) {
			if(i<0 || i>=data.size() || j<0 || j>=data.size()) {
				throw new IllegalArgumentException("Illegal Argument");
			}
			E temp=data.get(i);
			data.set(i, data.get(j));
			data.set(j, temp);
		}
		
		//��������Ԫ��
		public void add(E val) {
			data.add(val);
			siftUp(data.size()-1);
		}
		//������Ϊi��Ԫ�ؽ����ϴ��ķ���
		private void siftUp(int i) {
			//ֻ�нڵ㲻�Ǹ��ڵ㲢�ұȸ��׽ڵ����ϸ�
			while(i>0 && data.get(parent(i)).compareTo(data.get(i)) < 0) {
				swap(i, parent(i));
				i=parent(i);
				//���µ�λ���ϻ�Ҫ�����������
			}
		}
		//��һ�¶��е����Ԫ��
		public E findMax() {
			if(data.size()==0) {
				throw new IllegalArgumentException("Heap is empty");
			}
			return data.get(0);
		}
		//ȡ�����е����Ԫ��
		public E extractMax() {
			E ret=findMax();
			swap(0, data.size()-1);
			data.remove(data.size()-1);
			siftDown(0);
			return ret;
		}
		//����iλ��Ԫ�ؽ����³�
		private void siftDown(int i) {
			//ѭ������������1. ��iλ��Ҷ�ӽڵ㣬ֻҪ����������������п��ܼ����³�����Ҫ��ѭ�����ж�
			//����˼·����ȡ���������ӽڵ�����ֵ��֮�����ڵĽڵ������ֵ���бȽ�
			while(leftChild(i) < data.size()) {
				int j=leftChild(i);
				//j�������ӽڵ��нϴ��߶�Ӧ��������j+1���ܴ���ڵ�䵽�ҽڵ�
				if(j+1 < data.size() && data.get(j+1).compareTo(data.get(j)) > 0) {
					j=rightChild(i);
				}
				if(data.get(i).compareTo(data.get(j)) > 0) {
					break;
				}else {
					swap(i, j);
				}
				//֮�����λ�ü���ѭ��
				i=j;
			}
		}
		//ȡ�����е����Ԫ�ز����滻��Ԫ��val
		public E replace(E val) {
			E ret=findMax();
			data.set(0, val);
			siftDown(0);
			return ret;
		}
	
	public static void main(String[] args) {
		
	}
}

