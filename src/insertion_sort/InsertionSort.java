package insertion_sort;

public class InsertionSort<E extends Comparable<E>> {
	
	private E[] arr;
	
	public InsertionSort(E[] arr) {
		this.arr=arr;
	}
	
	public void insertionSort(E[] arr, int n) {
		//插入排序中第一个元素不需要考虑，本身已经有序了
		for(int i=1; i<n; i++) {
			//寻找元素arr[i]在它之前的子数组中的合适的插入位置（从小到大排序)
			E e=arr[i];
			int j; //j保存元素e最终应该插入的位置
			//只有同时满足这两个条件，循环才会继续
			for(j=i; j>0 && arr[j-1].compareTo(e)>0; j--){
					arr[j]=arr[j-1];
					//只要循环能继续，就需要把前一个元素移到当前位置，来给e的最后插入空出一个位置
			}
			arr[j]=e;
			//插入排序的内层循环可以提前终止，这是它的最大优势
			//当数组已经大致有序的时候，插入排序的效率会比一些高级排序算法还要快
			//比如说针对一个系统日志，本身就是有序生成的，只不过因为一些特殊情况有几个无序的地方，这种时候插入排序就非常有效
		}
	}

	public static void main(String[] args) {

	}

}
