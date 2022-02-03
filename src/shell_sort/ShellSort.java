package shell_sort;

import java.util.ArrayList;

public class ShellSort<E extends Comparable<E>> {
	
	private E[] arr;
	
	public ShellSort(E[] arr) {
		this.arr=arr;
	}
	
	public void shellSort() {
		__shellSort(arr);
	}
	
	private void __shellSort(E[] arr) {
		ArrayList<Integer> stepArr=getSedgewickStepArr(arr.length);
		for(int i=stepArr.size()-1; i>=0; i--) {
			for(int j=stepArr.get(i); j<arr.length; j++) {
				E e=arr[j];
				int cur=j, step=stepArr.get(i);
				while(cur>=step) {
					if(arr[cur-step].compareTo(e)>0) {
						arr[cur]=arr[cur-step];
						cur-=step;
					}else {
						break;
					}
				}
				arr[cur]=e;
			}
		}
	}
	/*
	 * Sedgewick增量序列
	 *D=9*4^i-9*2^i+1(i为偶数？)或 4^(i+2)-3*2^(i+2)+1(i为奇数？) , i>=0
	 *稍微变一下形：D=9*(2^(2i)-2^i)+1 或 2^(2i+4)-3*2^(i+2)+1 , i>=0
	 */
	private ArrayList<Integer> getSedgewickStepArr(int n) {
		ArrayList<Integer> arr=new ArrayList<>();
		int i=0;
		while(true) {
			int temp=9*((1 << 2*i)-(1<<i))+1;
			if(temp<=n) {
				arr.add(temp);
			}else {
				break;
			}
			temp=(1<<(2*i+4))-3*(1<<(i+2))+1;
			if(temp<=n) {
				arr.add(temp);
			}else {
				break;
			}
			i++;
		}
		return arr;
	}
	
	public static void main(String[] args) {
		Integer[] arr= {3,6,2,7,9,3,9,5,9,1,5,3,7,3,6,3,8,3,8,4,7,3,7};
		ShellSort<Integer> sort=new ShellSort<>(arr);
		sort.shellSort();
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
