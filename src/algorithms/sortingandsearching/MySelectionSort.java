package algorithms.sortingandsearching;

import java.util.*;

//O(n^2)
public class MySelectionSort {
    public static int[] selectionSort(int[] a) {
        if (a.length == 0 || a == null) {
            return null;
        }
        int N = a.length;
        for (int i = 0; i < N - 1; i++) {
            int currMin = i;
            for (int j = i + 1; j < N; j++) {
                if (a[j] < a[currMin]) {
                    currMin = j;
                }
            }
            int temp = a[currMin];
            a[currMin] = a[i];
            a[i] = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = new int[] { 2, 44, 5, 2444, -2 };
        System.out.println(Arrays.toString(selectionSort(a)));
    }
}