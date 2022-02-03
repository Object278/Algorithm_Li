package quick_sort;

import java.util.Random;

public class QuickSort<E extends Comparable<E>> {
	//�Ż�1��������ӽ��ײ��ʱ��ֱ��ʹ��ϣ������
	//�Ż�2���������ǵ㣬��Ϊ�������ÿ�β�һ�����ȵİ������Ϊ���룬��Ӧ�Խ������������������������ϻ��˻�ΪO(n2)
	//��Ϊ�ݹ�����ƽ���Ի�ܲ�����˻�Ϊ����
	private E[] arr;
	private Random rand;
	
	public QuickSort(E[] arr) {
		this.arr=arr;
		rand=new Random();
	}

	public void quickSort(E[] arr) {
		__quickSort(arr, 0, arr.length-1);
	}
	
	private void __quickSort(E[] arr, int lo, int hi) {
		if(lo>=hi) {
			return;
		}
		int p=__partition(arr, lo, hi);
		__quickSort(arr, lo, p-1);
		__quickSort(arr, p+1, hi);
	}
	
	private int __partition(E[] arr, int lo, int hi) {
		swap(lo, rand.nextInt(hi-lo+1)+lo);
		E e=arr[lo];
		//֮������partition�Ĺ��̣�Ҫ����arr[lo, j]<e��arr[j+1,i-1]>=e�����arr[lo]��arr[j]λ��Ԫ�ؽ��н�����󷵻�j
		//jָ����С��e�����������e������ķֽ�㣬iָ���ǵ�ǰ���ʵĽڵ㣬�ڱ���partition������iӦ����hi+1��λ����
		//���ﶨ��j�ĳ�ʼֵΪlo��i�ĳ�ʼֵΪlo+1����֤�˳�ʼ��ʱ��arr[lo, j]��arr[j+1,i-1]��Ϊ�ռ��ϣ��ռ��Ϻ�eû�д�С��ϵһ˵��Ҳ����������
		int j=lo;
		for(int i=lo+1; i<=hi; i++) {
			//�����ǰ���ʵ�Ԫ�ش���e����ôֻҪ�������ķ�Χ��1��i++���ͺ���
			//�����ǰ���ʵ�Ԫ��С��e����ô��Ҫ�Ѵ����������һ��Ԫ�أ�arr[j+1]���뵱ǰ���ʵ�Ԫ�ؽ�����֮��С�����ķ�Χ��1��j++��
			//����д++j����Ϊ++j��ִ���Լӣ�ͬ��������޸�j�ı���ֵ�ʹ�j+1Ϊ��������swap������������
			/*
			 * ����һ����ϸ�Ľ��ͣ�
			 * ������JVM�����������洢����һ�����ݴ��������³�Ϊ��ջ������һ���Ǳ�������j=j++���Ƚ�j��ֵ��0��ԭʼֵ�������ջ�У���Ӧͼ�з���һ���µ��ڴ�ռ䣩��
			 * Ȼ��Ա�������j�Լ�1����ʱj��ֵȷʵ��1������󽫶�ջ�е�ֵ������������j���������j=0;
			 * 
				��j=++j�����ȶԱ������е�j��1���ٽ��������е�jֵ��1�������ջ����󽫶�ջ�е�ֵ�����Ա�������j������j=1;
			 */
			if(arr[i].compareTo(e)<0) {
				swap(++j, i);
			}
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
		QuickSort<Integer> sort=new QuickSort<>(arr);
		sort.quickSort(arr);
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
