package com.tfc.learn;


public class QuickSort {
	// 数组长度
	private static final int LENGTH = 30;
	// 随机数范围
	private static final int ROUND = 100;

	public static void quickSort(long[] arr, int low, int high) {
		int i, j;
		long base, t;
		if (low > high) {
			return;
		}
		i = low;
		j = high;
		// base就是基准位
		base = arr[low];

		while (i < j) {
			// 从后往前找
			while (base <= arr[j] && i < j) {
				j--;
			}
			// 从前往后找
			while (base >= arr[i] && i < j) {
				i++;
			}
			// 这时将小的数交换到基准数左边，大的数交换到基准数右边
			if (i < j) {
				t = arr[j];
				arr[j] = arr[i];
				arr[i] = t;
			}

		}
		// 最后将基准为与i和j相等位置的数字交换
		arr[low] = arr[j];
		arr[j] = base;
		// 递归调用左半数组
		quickSort(arr, low, j - 1);
		// 递归调用右半数组
		quickSort(arr, j + 1, high);
	}
	

	/**
	 * 生成测试数组
	 * @param length
	 * @return
	 */
	public static final long[] generateArrayByLength(int length) {
		long[] arr = new long[length];
		for (int i = 0; i < length; i++) {
			arr[i] = Math.round((Math.random() * ROUND));
		}
		return arr;
	}
	
	/**
	 *  打印数组
	 * @param arr
	 */
	private static void printArray(long[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.err.print(arr[i] + " ");
		}
	}

	public static void main(String[] args) {
		long[] arr = generateArrayByLength(LENGTH);
		quickSort(arr, 0, arr.length - 1);
		printArray(arr);
	}


}
