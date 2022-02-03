package weightedgraph;

import java.util.ArrayList;
import java.util.Random;

public class IndexMaxHeap<E extends Comparable<E>> {

	// �ö�̬����ʵ��һ���������ѣ������Ǵ�1��ʼ��
	private ArrayList<E> data;
	private ArrayList<Integer> indexes;
	private ArrayList<Integer> reverse;
	private int capacity;// �ɼӿɲ��ӵĳ�Ա����
	private int count;//�洢����Ŀǰ�м���Ԫ�أ�ָ��indexes���������һ��Ԫ��

	public IndexMaxHeap(int capacity) {
		this.capacity = capacity;
		data = new ArrayList<>(capacity+1);
		indexes = new ArrayList<>(capacity+1);
		reverse= new ArrayList<>(capacity+1);
		this.count=0;
		for(int i=0; i<capacity+1; i++) {
			//���ĳ�����������ڵĻ���Ĭ��ֵΪ-1
			reverse.add(0);
			data.add(null);
			indexes.add(0);
		}
	}
//		���capacity����֮��Ϊ����Ӳ����İ�ȫ�Զ�ֹͣʹ��
//		public IndexMaxHeap() {
//			data=new ArrayList<>();
//			indexes=new ArrayList<>();
//			this.capacity=data.size();
//		}

	// ��һ������ת��Ϊ�ѣ������޸�Ϊ���������ѵĴ���
//		public IndexMaxHeap(E[] arr) {
//			this.capacity=arr.length;
//			data=new ArrayList<>();
//			indexes=new ArrayList<>();
//			for(int i=0; i<arr.length; i++) {
//				data.set(i, arr[i]);
//				indexes.set(i, i);
//			}
//			//�����һ����Ҷ�ӽڵ����siftDown
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

	// ��������ȫ�������������ʾ��һ��������Ԫ�صĸ��׽ڵ��indexֵ
	private int parent(int index) {
		if (index == 0) {
			throw new IllegalArgumentException("no parent");
		}
		return index / 2;
	}

	// ��������ȫ�������������ʾ��һ��������Ԫ�ص����ӽڵ��indexֵ
	private int leftChild(int index) {
		return index * 2;
	}

	// ��������ȫ�������������ʾ��һ��������Ԫ�ص����ӽڵ��indexֵ
	private int rightChild(int index) {
		return index * 2 + 1;
	}

	// ����indexes����������Ԫ�ص�λ��
	private void swap(int i, int j) {
		if (i < 0 || i >= data.size() || j < 0 || j >= data.size()) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		int temp = indexes.get(i);
		indexes.set(i, indexes.get(j));
		indexes.set(j, temp);
	}

	// ��������Ԫ��
	// index�Ǵ�1��ʼ�����ģ��ⲿ�û�����Ĳ����Ǵ�0��ʼ������
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

	// ������Ϊi�����������ϴ��ķ���
	private void siftUp(int i) {
		// ֻ�нڵ㲻�Ǹ��ڵ㲢�ұȸ��׽ڵ����ϸ�
		while (i > 1 && data.get(indexes.get(parent(i))).compareTo(data.get(indexes.get(i))) < 0) {
			swap(indexes.get(i), indexes.get(parent(i)));
			//��û��̫�����������
			reverse.set(indexes.get(parent(i)), parent(i));
			reverse.set(indexes.get(i), i);
			i = parent(i);
			// ���µ�λ���ϻ�Ҫ�����������
		}
	}

	// ��һ�¶��е����Ԫ��
	public E findMax() {
		if (data.size() == 0) {
			throw new IllegalArgumentException("Heap is empty");
		}
		return data.get(indexes.get(1));
	}

	// ȡ�����е����Ԫ��
	public E extractMax() {
		E ret = findMax();
		swap(0, count);
		reverse.set(indexes.get(1), 1);
		//�Ѷ���Ԫ��֮����Ҫ��ɾ��������ֱ�Ӹĳ�Ĭ��ֵ
		reverse.set(indexes.get(count), 0);
		data.remove(count);
		count--;
		siftDown(1);
		return ret;
	}

	// ȡ�����е����Ԫ�ص�����
	public int extractMaxIndex() {
		int ret = indexes.get(0);
		swap(0, count);
		reverse.set(indexes.get(1), 1);
		//�Ѷ���Ԫ��֮����Ҫ��ɾ��������ֱ�Ӹĳ�Ĭ��ֵ
		reverse.set(indexes.get(count), 0);
		count--;
		siftDown(1);
		return ret;
	}

	// ����iλ��Ԫ�ؽ����³�
	private void siftDown(int i) {
		// ѭ������������1. ��iλ��Ҷ�ӽڵ㣬ֻҪ����������������п��ܼ����³�����Ҫ��ѭ�����ж�
		// ����˼·����ȡ���������ӽڵ����Сֵ��֮�����ڵĽڵ�����Сֵ���бȽ�
		while (leftChild(i) < count+1) {
			int j = leftChild(i);
			System.out.println(i);
			System.out.println(indexes.get(j + 1));
			System.out.println("b"+data.get(indexes.get(j + 1)));
			System.out.println("c"+data.get(indexes.get(j)));
			// j�������ӽڵ��нϴ��߶�Ӧ��������j+1���ܴ���ڵ�䵽�ҽڵ�
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
			// ֮�����λ�ü���ѭ��
			i = j;
		}
	}

	// ȡ�����е����Ԫ�ز����滻��Ԫ��val
	public E replace(E val) {
		E ret = findMax();
		data.set(0, val);
		siftDown(0);
		return ret;
	}
	
	//����һ��Ԫ�ض�Ӧ��index���ó����Ԫ��
	public E getData(int index) {
		if(!contain(index)) {
			throw new IllegalArgumentException("index out of bound");
		}
		return data.get(index);
	}
	
	//����һ��Ԫ�ض�Ӧ��index���޸����Ԫ��
	//����һ��O(logn)�����
	public void change(int index, E newVal) {
		if(!contain(index)) {
			throw new IllegalArgumentException("index out of bound");
		}
		data.set(index, newVal);
		//Ϊ��ά���ѵ����ʣ���Ҫ�ҵ�indexes[j]=i��j��ʾdata[i]�ڶ��е�λ��
		//֮����siftUp(j)����siftDown(j)
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
	
	//�ж�ĳ��index��Ӧ��Ԫ���ǲ��Ƕ��е�Ԫ�أ���0-capacity֮���index��һ���ڶ��ڣ�
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
			//�öѵġ�ȡ�����ֵ����������
			int[] arr=new int[n];
			for(int i=0; i<n; i++) {
				System.out.println("a "+i);
				arr[i]=imh.extractMax();
			}
			//ÿ�ο����ڵ�����ֵ��ֻ�ñȽ�n-1��
			for(int i=1; i<n; i++) {
				if(arr[i-1] < arr[i]) {
					throw new IllegalArgumentException("Error");
				}
			}
			System.out.println("Test MaxHeap completed");
			
	}
}
