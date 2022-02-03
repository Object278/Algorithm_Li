package quick_sort;

import java.util.Random;

public class QuickSort<E extends Comparable<E>> {
	//优化1，当到达接近底层的时候直接使用希尔排序
	//优化2，随机化标记点，因为快递排序每次不一定均等的把数组分为两半，在应对近乎于有序的数组的排序问题上会退化为O(n2)
	//因为递归树的平衡性会很差，甚至退化为链表
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
		//之后是求partition的过程，要保持arr[lo, j]<e，arr[j+1,i-1]>=e，最后将arr[lo]与arr[j]位的元素进行交换最后返回j
		//j指的是小于e的区域与大于e的区域的分界点，i指的是当前访问的节点，在本次partition结束后i应该在hi+1的位置上
		//这里定义j的初始值为lo，i的初始值为lo+1，保证了初始的时候arr[lo, j]与arr[j+1,i-1]均为空集合，空集合和e没有大小关系一说，也满足了条件
		int j=lo;
		for(int i=lo+1; i<=hi; i++) {
			//如果当前访问的元素大于e，那么只要大于区的范围加1（i++）就好了
			//如果当前访问的元素小于e，那么需要把大于区的最后一个元素（arr[j+1]）与当前访问的元素交换，之后小于区的范围加1（j++）
			//这里写++j是因为++j先执行自加，同步完成了修改j的变量值和传j+1为参数进入swap函数两个动作
			/*
			 * 附上一个详细的解释：
			 * 在这里JVM里面有两个存储区，一个是暂存区（以下称为堆栈），另一个是变量区。j=j++是先将j的值（0，原始值）存入堆栈中（对应图中分配一块新的内存空间），
			 * 然后对变量区中j自加1，这时j的值确实是1，但随后将堆栈中的值赋给变量区的j，所以最后j=0;
			 * 
				而j=++j，是先对变量区中的j加1，再将变量区中的j值（1）存入堆栈，最后将堆栈中的值赋给自变量区的j，所以j=1;
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
