package dsa.java.sortingandsearching;



import java.util.Random;

import static dsa.java.DSAUtils.swapIntArr;


//A simple demonstration of quicksort on Integers
public class MyQuickSort {

    public void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int part = partition(arr, low, high);
            quicksort(arr, low, part - 1);
            quicksort(arr, part + 1, high);
        }
    }
    //This gives a TLE, and O(N^2) solution if all the elements are same. To prevent a TLE we need to use 3-way partitioning solution for this
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        /*
        The variable i is used to track the "boundary" of the region of elements that are less than or equal to the pivot.
        Initially, this region is empty.
        Initialization: By setting i to lo - 1,
        we ensure that the first increment of i (when the first element less than or equal to the pivot is found) will place i at lo,
        correctly positioning the first element that satisfies the condition.

        Boundary Management: The elements from lo to i (inclusive) will always be less than or equal to the pivot.
        Starting i at lo - 1 ensures that this invariant holds from the beginning.
         */
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        /*
        Final Swap of Pivot with i + 1
After the for loop completes, all elements less than or equal to the pivot are to the left of index i.
The pivot element is still at the hi index, and all elements from i+1 to hi-1 are greater than the pivot.
To place the pivot in its correct position in the sorted array, you swap it with the element at i + 1.
This ensures that:
All elements to the left of i + 1 are less than or equal to the pivot.
The pivot is now at index i + 1.
All elements to the right of i + 1 are greater than the pivot.
This final swap ensures that the pivot is correctly positioned in the array, which is crucial for the recursive steps of the quicksort algorithm to work correctly.
         */
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
    public void sortArr(int[] arr) {
        solveWithThreeWayPartition(arr, 0, arr.length - 1);
    }
    private static void solveWithThreeWayPartition(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int[]partitions = findThreeWayPartition(arr, lo, hi);
            solveWithThreeWayPartition(arr, lo, partitions[0] - 1);
            solveWithThreeWayPartition(arr, partitions[1] + 1, hi);
        }
    }
    private static int[] findThreeWayPartition(int[]arr, int lo, int hi) {
        Random ran = new Random();
        int pivotIndex = lo + ran.nextInt(hi - lo + 1);
        swapIntArr(arr, pivotIndex, hi);
        int pivot = arr[hi];
        /*
        lt: Tracks the boundary of elements less than the pivot.
gt: Tracks the boundary of elements greater than the pivot.
i: Iterates through the array.
When an element is less than the pivot, it is swapped into the lt region.
When an element is greater than the pivot, it is swapped into the gt region.
When an element is equal to the pivot, i is simply incremented.
         */
        int i= lo;// iterator over the array
        int lt = lo; //less than pivot
        int gt = hi; //greater than pivot

        while(i <= gt) {
            //swap
            if (arr[i] < pivot) {
                swapIntArr(arr, lt, i);
                lt++;
                i++;
            } else if(arr[i] > pivot) {
                swapIntArr(arr, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        return new int[]{lt, gt};
    }

    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = { 23, -90, 0, 87, 23, 4, 65, 69, -9, 9 };
        int n = arr.length;
        int low = 0;
        int high = n - 1;

        MyQuickSort qs = new MyQuickSort();
        qs.quicksort(arr, low, high);

        printArray(arr);
    }
}