package selection_sort;

public class SelectionSort<E extends Comparable<E>> {
	
	private E[] arr;
	
	public SelectionSort(E[] arr) {
		this.arr=arr;
	}
	
	public void selectionSort(E[] arr, int n) {
		for(int i=0; i<n; i++) {
			//寻找[i, n)区间内的最小值
			int minIndex=i;
			for(int j=i+1; j<n; j++) {
				if(arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex=j;
				}
			}
			swap(i, minIndex);
		}
	}

	private void swap(int i, int j) {
		E temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}

	public static void main(String[] args) {
		SortTestHelper sth=new SortTestHelper();
		Integer[] arr=sth.generateRandomArray(100, 10, 1000);
		SelectionSort<Integer> ss=new SelectionSort<>(arr);
		ss.selectionSort(arr, arr.length);
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
