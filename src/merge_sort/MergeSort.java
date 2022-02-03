package merge_sort;

public class MergeSort<E extends Comparable<E>> {

	private E[] arr;
	// 所有区间采用前闭后闭的定义

	public MergeSort(E[] arr) {
		this.arr = arr;
	}

	public void mergeSort(E[] arr, int n) {
		__mergeSort(arr, 0, n - 1);
	}
	//n是数组中的元素个数
	public void mergeSortBU(E[] arr, int n) {
		int m = 0;
		while (m < arr.length - 1) {
			__insertionSort(arr, m, m+15);
			m+=15;
		}
		for (int size = 16; size <= n; size += size) {
			//因为每次必须合并两个区间才有意义，所以内层循环的终止条件是i+size<n，确保第二个区间的下界存在
			//第二个区间的上界需要在i+size+size-1和n-1之间取最小值，防止数组越界错误
			for(int i=0; i+size<n; i+=size+size) {
				if(arr[i+size-1].compareTo(arr[i+size])>0) {
					__merge(arr, i, i+size-1, Math.min(i+2*size-1, n-1));
				}
			}
		}
	}

	// 对arr数组[lo, hi]前闭后闭的范围进行归并排序，
	private void __mergeSort(E[] arr, int lo, int hi) {
		if (hi - lo <= 15) {
			__insertionSort(arr, lo, hi);
			return;
		}
		// 此函数无副作用
		// 防止lo与hi直接相加导致溢出
		int mid = lo + (hi - lo) / 2;
		__mergeSort(arr, lo, mid);
		__mergeSort(arr, mid + 1, hi);
		// 如果处理的数据有可能非常有序就需要这个优化
		// 这里如果arr中mid位的元素小于mid+1位的元素的话，那这两个部分就已经有序了，不需要进入__merge
		if (arr[mid].compareTo(arr[mid + 1]) > 0) {
			__merge(arr, lo, mid, hi);
		}
	}

	// 将两个排好序的数组进行归并，所有的副作用由这个函数产生
	private void __merge(E[] arr, int lo, int mid, int hi) {
		// 辅助空间
		E[] aux = (E[]) new Object[hi - lo + 1];
		// 注意辅助空间与arr数组可能有一个lo大小的偏移量
		for (int i = lo; i <= hi; i++) {
			aux[i - lo] = arr[i];
		}
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			// 这里需要先判断i，j索引的合法性，如果一个不合法的话说明另一个排好序的数组中的元素还没有被完全放入原来的数组
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
