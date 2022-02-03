package quick_sort;

import java.util.Random;

public class QuickSort2<E extends Comparable<E>> {
	//优化1，当到达接近底层的时候直接使用希尔排序
		//优化2，随机化标记点，因为快递排序每次不一定均等的把数组分为两半，在应对近乎于有序的数组的排序问题上会退化为O(n2)
		//因为递归树的平衡性会很差，甚至退化为链表
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
				//可以写一个希尔排序来优化这里
				return;
			}
			int p=__partition2(arr, lo, hi);
			__quickSort2(arr, lo, p-1);
			__quickSort2(arr, p+1, hi);
		}
		
		private int __partition2(E[] arr, int lo, int hi) {
			swap(lo, rand.nextInt(hi-lo+1)+lo);
			E e=arr[lo];
			//之后是求partition的过程，要保持arr[lo+1, i)<=e，arr(j, hi]>=e，最后将arr[lo]与arr[j]位的元素进行交换（因为j最后一次自加之后指向的是一个小于等于e的元素，并且e在交换前处在小于等于区内）
			//最后返回j
			//j指的是从右往左排列的大于等于区左侧的第一个元素，i指的是从左往右排列的小于等于区右侧的第一个元素，partition结束条件是i>j，即i自加之后指向了大于等于区最左侧的元素，数组中的所有元素都被扫描了一遍
			//初始的时候两个集合都是空集合，所以条件仍然成立
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
