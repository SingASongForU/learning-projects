package com.tfc.learn;


public class QuickSort {
	private static final int LENGTH = 30;
	private static final int ROUND = 100;

	public static void quickSort(long[] arr, int low, int high) {
		int i, j;
		long base, t;
		if (low > high) {
			return;
		}
		i = low;
		j = high;
		// base���ǻ�׼λ
		base = arr[low];

		while (i < j) {
			// �Ӻ���ǰ��
			while (base <= arr[j] && i < j) {
				j--;
			}
			// ��ǰ������
			while (base >= arr[i] && i < j) {
				i++;
			}
			// ��ʱ��С������������׼����ߣ��������������׼���ұ�
			if (i < j) {
				t = arr[j];
				arr[j] = arr[i];
				arr[i] = t;
			}

		}
		// ��󽫻�׼Ϊ��i��j���λ�õ����ֽ���
		arr[low] = arr[j];
		arr[j] = base;
		// �ݹ�����������
		quickSort(arr, low, j - 1);
		// �ݹ�����Ұ�����
		quickSort(arr, j + 1, high);
	}
	

	/**
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
