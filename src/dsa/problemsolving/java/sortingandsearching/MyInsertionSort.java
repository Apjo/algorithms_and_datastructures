package dsa.problemsolving.java.sortingandsearching;

import java.util.*;

//O(n^2) worst/average case, O(n) best case
public class MyInsertionSort {
    public static int[] insertionSortRec(int[] a, int n) {
        if (n <= 1) {
            return null;
        }
        insertionSortRec(a, n - 1);
        int j = n - 1;
        System.out.println("j= " + j + " Arr before = " + Arrays.toString(a));
        while (j > 0 && a[j] < a[j - 1]) {
            int tt = a[j - 1];
            a[j - 1] = a[j];
            a[j] = tt;
            j = j - 1;
            System.out.println("Arr now = " + Arrays.toString(a));
        }
        return a;
    }

    public static int[] insertionSortShiftRight(int[] a, int n) {
        if (n <= 1) {
            return null;
        }
        insertionSortShiftRight(a, n - 1);
        int last = a[n];

        int j = n - 1;
        System.out.println("j= " + j + " last= " + last + " Arr before = " + Arrays.toString(a));
        while (j > 0 && a[j] > last) {
            a[j + 1] = a[j];
            j = j - 1;
            System.out.println("Arr now = " + Arrays.toString(a));
        }
        a[j + 1] = last;
        return a;
    }

     public static int[] insertionSortIter(int[] a) {
         if (a.length <= 1) {
             return null;
         }
         for (int i = 2; i < a.length; i++) {
             int ith = a[i];
             int j = i - 1;
             while (j >= 1 && a[j] > ith) {
                 //shift right a[j] to a[j+1]
                 a[j+1] = a[j];
                 j = j - 1;
             }
             a[j + 1] = ith;
         }
         return a;
     }

    public static void main(String[] args) {
        int[] a = new int[] { 4, 5, 88, -1234, 56, 77, 99 };
        int N = a.length;
        // System.out.println(Arrays.toString(insertionSortRec(a, N)));
        System.out.println(Arrays.toString(insertionSortShiftRight(a, N)));
    }

}