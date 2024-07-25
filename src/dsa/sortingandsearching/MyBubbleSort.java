package dsa.sortingandsearching;

import java.util.*;

//O(n^2) time complexity
public class MyBubbleSort {
    public static int[] bubbleSort(int[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= N - i - 1; j++) {
                //ith smallest element bubbles up into A[i] at the end of ith iteration
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { -190, 19, 56, 2, 3, 4, 56 };
        System.out.println(Arrays.toString(bubbleSort(arr)));
    }
}