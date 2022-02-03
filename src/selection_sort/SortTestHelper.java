package selection_sort;

import java.util.Random;

public class SortTestHelper<E extends Comparable<E>> {
	
	//生成一个有n个元素的数组，每个元素的范围在[rangeL, rangeR]
	public Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
		if(rangeL > rangeR) {
			throw new IllegalArgumentException("rangeL must less than rangeR");
		}
		Integer[] arr=new Integer[n];
		Random random=new Random();
		for(int i=0; i<n; i++) {
			arr[i]=random.nextInt(rangeR-rangeL+1)+rangeL;
		}
		return arr;
	}
	
	public void testSort(String name, SelectionSort<E> ss, E[] arr, int n) {
		long sTime=System.nanoTime();
		ss.selectionSort(arr, n);
		long eTime=System.nanoTime();
		if(!isSorted(arr, n)) {
			throw new IllegalArgumentException("Sort is falied");
		}
		System.out.println(name+" Time: "+(eTime-sTime)/1000000000+" s");
	}
	
	public boolean isSorted(E[] arr, int n) {
		for(int i=0; i<n-1; i++) {
			if(arr[i].compareTo(arr[i+1])>0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

	}

}
