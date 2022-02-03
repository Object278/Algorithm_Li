package quick_sort;

import java.util.Random;

public class QuickSort2<E extends Comparable<E>> {
	//�Ż�1��������ӽ��ײ��ʱ��ֱ��ʹ��ϣ������
		//�Ż�2���������ǵ㣬��Ϊ�������ÿ�β�һ�����ȵİ������Ϊ���룬��Ӧ�Խ������������������������ϻ��˻�ΪO(n2)
		//��Ϊ�ݹ�����ƽ���Ի�ܲ�����˻�Ϊ����
		private E[] arr;
		private Random rand;
		
		public QuickSort2(E[] arr) {
			this.arr=arr;
			rand=new Random();
		}

		public void quickSort2(E[] arr) {
			__quickSort2(arr, 0, arr.length-1);
		}
		
		private void __quickSort2(E[] arr, int lo, int hi) {
			if(lo>=hi) {
				//����дһ��ϣ���������Ż�����
				return;
			}
			int p=__partition2(arr, lo, hi);
			__quickSort2(arr, lo, p-1);
			__quickSort2(arr, p+1, hi);
		}
		
		private int __partition2(E[] arr, int lo, int hi) {
			swap(lo, rand.nextInt(hi-lo+1)+lo);
			E e=arr[lo];
			//֮������partition�Ĺ��̣�Ҫ����arr[lo+1, i)<=e��arr(j, hi]>=e�����arr[lo]��arr[j]λ��Ԫ�ؽ��н�������Ϊj���һ���Լ�֮��ָ�����һ��С�ڵ���e��Ԫ�أ�����e�ڽ���ǰ����С�ڵ������ڣ�
			//��󷵻�j
			//jָ���Ǵ����������еĴ��ڵ��������ĵ�һ��Ԫ�أ�iָ���Ǵ����������е�С�ڵ������Ҳ�ĵ�һ��Ԫ�أ�partition����������i>j����i�Լ�֮��ָ���˴��ڵ�����������Ԫ�أ������е�����Ԫ�ض���ɨ����һ��
			//��ʼ��ʱ���������϶��ǿռ��ϣ�����������Ȼ����
			int i=lo+1, j=hi;
			while(true) {
				while(i<=hi && arr[i].compareTo(e)<0) {
					i++;
				}
				while(j>=lo+1 && arr[j].compareTo(e)>0){
					j--;
				}
				if(i>j) {
					break;
				}
				swap(i, j);
				i++;
				j--;
			}
			swap(lo, j);
			return j;
		}
		
		public void swap(int a, int b) {
			E temp=arr[b];
			arr[b]=arr[a];
			arr[a]=temp;
		}
		
		public static void main(String[] args) {
			Integer[] arr= {8,6,4,5,7,2,9,3,1,0,2,5,4,7,2,7};
			QuickSort2<Integer> sort2=new QuickSort2<>(arr);
			sort2.quickSort2(arr);
			for(int i=0; i<arr.length; i++) {
				System.out.println(arr[i]);
			}
		}

	}
