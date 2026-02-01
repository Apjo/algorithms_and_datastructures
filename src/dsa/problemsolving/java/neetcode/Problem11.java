package dsa.problemsolving.java.neetcode;

/*
You are given an array of integers nums, sort the array in ascending order and return it.

You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
 */
//date:07/23/2025
public class Problem11 {
    private static void merge(int[]arr, int lo, int mid, int hi) {
        int[] temp = new int[hi - lo + 1];
        int le=lo, ri = mid + 1, ctr=0;
        while(le <= mid && ri <= hi) {
            if(arr[le] <= arr[ri]) {
                temp[ctr++] = arr[le++];
            } else {
                temp[ctr++] = arr[ri++];
            }
        }
        while(le <= mid) {
            temp[ctr++] = arr[le++];
        }
        while(ri <= hi) {
            temp[ctr++] = arr[ri++];
        }
        int j=0;
        for(int i=lo; i <= hi; i++) {
            arr[i] = temp[j++];
        }
    }
    private static void mergeSort(int[]arr, int lo, int hi) {
        //if (lo > hi) {
        //return;
        //}
        if (hi > lo) {
            int mid = lo + (hi - lo) / 2;
            mergeSort(arr, lo, mid);
            mergeSort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }
    }
    public int[] sortArray(int[] nums) {
        int L = nums.length;
        mergeSort(nums, 0, L - 1);
        return nums;
    }
    public static void main(String[] args) {
    }
}
