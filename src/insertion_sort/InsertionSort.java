package insertion_sort;

public class InsertionSort<E extends Comparable<E>> {
	
	private E[] arr;
	
	public InsertionSort(E[] arr) {
		this.arr=arr;
	}
	
	public void insertionSort(E[] arr, int n) {
		//���������е�һ��Ԫ�ز���Ҫ���ǣ������Ѿ�������
		for(int i=1; i<n; i++) {
			//Ѱ��Ԫ��arr[i]����֮ǰ���������еĺ��ʵĲ���λ�ã���С��������)
			E e=arr[i];
			int j; //j����Ԫ��e����Ӧ�ò����λ��
			//ֻ��ͬʱ����������������ѭ���Ż����
			for(j=i; j>0 && arr[j-1].compareTo(e)>0; j--){
					arr[j]=arr[j-1];
					//ֻҪѭ���ܼ���������Ҫ��ǰһ��Ԫ���Ƶ���ǰλ�ã�����e��������ճ�һ��λ��
			}
			arr[j]=e;
			//����������ڲ�ѭ��������ǰ��ֹ�����������������
			//�������Ѿ����������ʱ�򣬲��������Ч�ʻ��һЩ�߼������㷨��Ҫ��
			//����˵���һ��ϵͳ��־����������������ɵģ�ֻ������ΪһЩ��������м�������ĵط�������ʱ���������ͷǳ���Ч
		}
	}

	public static void main(String[] args) {

	}

}
