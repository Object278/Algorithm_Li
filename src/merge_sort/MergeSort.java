package merge_sort;

public class MergeSort<E extends Comparable<E>> {

	private E[] arr;
	// �����������ǰ�պ�յĶ���

	public MergeSort(E[] arr) {
		this.arr = arr;
	}

	public void mergeSort(E[] arr, int n) {
		__mergeSort(arr, 0, n - 1);
	}
	//n�������е�Ԫ�ظ���
	public void mergeSortBU(E[] arr, int n) {
		int m = 0;
		while (m < arr.length - 1) {
			__insertionSort(arr, m, m+15);
			m+=15;
		}
		for (int size = 16; size <= n; size += size) {
			//��Ϊÿ�α���ϲ���������������壬�����ڲ�ѭ������ֹ������i+size<n��ȷ���ڶ���������½����
			//�ڶ���������Ͻ���Ҫ��i+size+size-1��n-1֮��ȡ��Сֵ����ֹ����Խ�����
			for(int i=0; i+size<n; i+=size+size) {
				if(arr[i+size-1].compareTo(arr[i+size])>0) {
					__merge(arr, i, i+size-1, Math.min(i+2*size-1, n-1));
				}
			}
		}
	}

	// ��arr����[lo, hi]ǰ�պ�յķ�Χ���й鲢����
	private void __mergeSort(E[] arr, int lo, int hi) {
		if (hi - lo <= 15) {
			__insertionSort(arr, lo, hi);
			return;
		}
		// �˺����޸�����
		// ��ֹlo��hiֱ����ӵ������
		int mid = lo + (hi - lo) / 2;
		__mergeSort(arr, lo, mid);
		__mergeSort(arr, mid + 1, hi);
		// �������������п��ܷǳ��������Ҫ����Ż�
		// �������arr��midλ��Ԫ��С��mid+1λ��Ԫ�صĻ��������������־��Ѿ������ˣ�����Ҫ����__merge
		if (arr[mid].compareTo(arr[mid + 1]) > 0) {
			__merge(arr, lo, mid, hi);
		}
	}

	// �������ź����������й鲢�����еĸ������������������
	private void __merge(E[] arr, int lo, int mid, int hi) {
		// �����ռ�
		E[] aux = (E[]) new Object[hi - lo + 1];
		// ע�⸨���ռ���arr���������һ��lo��С��ƫ����
		for (int i = lo; i <= hi; i++) {
			aux[i - lo] = arr[i];
		}
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			// ������Ҫ���ж�i��j�����ĺϷ��ԣ����һ�����Ϸ��Ļ�˵����һ���ź���������е�Ԫ�ػ�û�б���ȫ����ԭ��������
			if (i > mid) {
				arr[k] = aux[j - lo];
				j++;
			} else if (j > hi) {
				arr[k] = aux[i - lo];
				i++;
			} else if (aux[i - lo].compareTo(aux[j - lo]) < 0) {
				arr[k] = aux[i - lo];
				i++;
			} else {
				arr[k] = aux[j - lo];
				j++;
			}
		}
	}

	private void __insertionSort(E[] arr, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++) {
			E e = arr[i];
			int j;
			for (j = i; j > lo && arr[j - 1].compareTo(e) > 0; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = e;
		}
	}

	public static void main(String[] args) {

	}

}
