package dsa.java.sortingandsearching;

//A simple demonstration of Mergesort on Integers
public class MyMergeSort {
    // this technique creates Left and Right arrays for saving the Left and Right
    // parts after division
    void merge_sort(int[] A, int N) {
        if (N < 2) {
            return;
        }
        int mid = N / 2;
        int[] L = new int[mid];
        int[] R = new int[N - mid];
        int k = 0;
        for (int i = 0; i < N; i++) {
            if (i < mid) {
                L[i] = A[i];
            } else {
                R[k] = A[i];
            }
        }
        merge_sort(A, mid);
        merge_sort(A, N - mid);
        merge4(L, R, A, mid, N - mid);
    }

    void merge4(int[] L, int[] R, int[] A, int lo, int hi) {
        int leftPtr = 0;
        int rightPtr = 0;
        int curr = 0;
        while (leftPtr <= lo && rightPtr <= hi) {
            if (L[leftPtr] <= R[rightPtr]) {
                A[curr++] = L[leftPtr++];
            } else {
                A[curr++] = R[rightPtr++];
            }
        }
        while (leftPtr <= lo) {
            A[curr++] = L[leftPtr++];
        }

        while (rightPtr <= hi) {
            A[curr++] = R[rightPtr++];
        }
    }

    // another approach by making use of a buffer that is passed from the caller.
    // The size of the buffer is the same as the input array
    private void merge3(int[] arr, int low, int mid, int high, int[] helper) {
        for (int i = low; i <= high; i++) {
            helper[i] = arr[i];
        }
        int leftIter = low;
        int rightIter = mid + 1;
        int currIter = 0;

        while (leftIter <= mid && rightIter <= high) {
            if (arr[leftIter] <= arr[rightIter]) {
                helper[currIter++] = arr[leftIter++];
            } else {
                helper[currIter++] = arr[rightIter++];
            }
        }
        int delta = mid - leftIter;

        for (int i = 0; i <= delta; i++) {
            arr[currIter + i] = helper[leftIter + i];
        }
    }

    private void merge1(int[] arr, int low, int mid, int high) {
        int leftIter = low;
        int rightIter = mid + 1;
        int currIter = 0;
        int[] merged = new int[high - low + 1];
        while (leftIter <= mid && rightIter <= high) {
            if (arr[leftIter] <= arr[rightIter]) {
                merged[currIter++] = arr[leftIter++];
            } else {
                merged[currIter++] = arr[rightIter++];
            }
        }
        while (leftIter <= mid) {
            merged[currIter++] = arr[leftIter++];
        }
        while (rightIter <= high) {
            merged[currIter++] = arr[rightIter++];
        }

        for (int i = low, j = 0; i <= high; i++, j++) {
            arr[i] = merged[j];
        }
    }

    // The reason to add this method is only to replicate/implement the method
    // provided in the CLRS book
    private void merge2(int[] arr, int low, int mid, int high) {
        // number of elements in the left array
        int n1 = mid - low + 1;
        // number of elements in the right array
        int n2 = high - mid;
        // A new array to hold the left part
        int[] L = new int[n1];
        // A new array to hold the right part
        int[] R = new int[n2];

        // copy the left part into the new L array
        for (int i = 0; i < n1; i++) {
            L[i] = arr[low + i];
        }
        // copy the right part into the new R array
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = low;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        // traverse remaning left array
        while (i < n1) {
            arr[k++] = L[i++];
        }
        // traverse remaining right array
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    public void mergeSort1(int[] arr, int low, int high) {
        if (high > low) {
            int mid = low + (high - low) / 2;
            mergeSort1(arr, low, mid);
            mergeSort1(arr, mid + 1, high);
            merge1(arr, low, mid, high);
        }
    }

    public void mergeSort2(int[] arr, int low, int high) {
        if (high > low) {
            int mid = low + (high - low) / 2;
            mergeSort2(arr, low, mid);
            mergeSort2(arr, mid + 1, high);
            merge2(arr, low, mid, high);
        }
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 23, 45, 1, 2, 3, 89, -90, -90, 101 };
        int n = arr.length;
        int low = 0;
        int high = n - 1;

        MyMergeSort mergeSort = new MyMergeSort();

        mergeSort.mergeSort1(arr, low, high);
        System.out.println("Array after mergesort1");
        mergeSort.printArray(arr);

        mergeSort.mergeSort2(arr, low, high);
        System.out.println("Array after mergesort2");
        mergeSort.printArray(arr);
    }
}