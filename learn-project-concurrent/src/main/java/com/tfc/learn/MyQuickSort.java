package com.tfc.learn;

public class MyQuickSort {
    public static void main(String[] args) {
        long arr[] = QuickSort.generateArrayByLength(10);
        quickSort(arr, 0, arr.length - 1);
        for (long ele : arr) {
            System.err.print(ele + " ");
        }
    }

    private static void quickSort(long[] arr, int begin, int end) {
        int i, j;
        long base, temp;
        if (begin > end) {
            return;
        }
        i = begin;
        j = end;
        base = arr[begin];
        while (i < j) {
            while (arr[j] >= base && j > i) {
                j--;
            }
            while (arr[i] <= base && i < j) {
                i++;
            }
            if (i < j) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        arr[begin] = arr[i];
        arr[i] = base;
        quickSort(arr, begin, j - 1);
        quickSort(arr, j + 1, end);
    }

}
