package quick_sort;

import java.util.Random;

public class QuickSort3Ways<E extends Comparable<E>> {
	private E[] arr;
	private Random rand;
	
	public QuickSort3Ways(E[] arr) {
		this.arr=arr;
		rand=new Random();
	}

	public void quickSort3Ways(E[] arr) {
		__quickSort3Ways(arr, 0, arr.length-1);
	}
	
	private void __quickSort3Ways(E[] arr, int lo, int hi) {
		if(lo>=hi) {
			return;
		}
		//partition
		//��arr��Ϊ<v; ==v; >v�����֣�����ұգ���֮���С��v�ʹ���v�������ֽ��еݹ�
		swap(lo, rand.nextInt(hi-lo+1)+lo);
		E e=arr[lo];
		int lt=lo;//��֤arr[lo+1, lt]<v�ڳ�ʼ״̬��Ҳ����
		int gt=hi+1;//��֤arr[gt, hi]>v�ڳ�ʼ״̬��Ҳ����
		int i=lo+1;//��֤arr[lt+1, i-1]<v�ڳ�ʼ״̬��Ҳ����
		while(i<gt) {
			if(arr[i].compareTo(e)<0) {
				swap(i, lt+1);
				lt++;
				i++;
			}else if(arr[i].compareTo(e)>0) {
				swap(i, gt-1);
				gt--;
			}else {//arr[i].equals(e)==true
				i++;
			}
		}
		swap(lo, lt);
		//����д��lt--;
		__quickSort3Ways(arr, lo, lt-1);
		__quickSort3Ways(arr, gt, hi);
	}
	
	public void swap(int a, int b) {
		E temp=arr[b];
		arr[b]=arr[a];
		arr[a]=temp;
	}
	
	public static void main(String[] args) {
		Integer[] arr= {8,6,4,5,7,2,9,3,1,0,2,5,4,7,2,7};
		QuickSort3Ways<Integer> sort3=new QuickSort3Ways<>(arr);
		sort3.quickSort3Ways(arr);
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
	}


}
