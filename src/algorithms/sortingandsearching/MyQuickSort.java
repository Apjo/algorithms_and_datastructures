package algorithms.sortingandsearching;

//A simple demonstration of quicksort on Integers
public class MyQuickSort {
    public void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int part = partition(arr, low, high);
            quicksort(arr, low, part - 1);
            quicksort(arr, part + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
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