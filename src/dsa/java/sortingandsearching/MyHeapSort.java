package dsa.java.sortingandsearching;

/*
This class is a simple demonstration for a heapsort to sort elements(integers in our case) in ascending order
This class assumes a couple of things:
1. The type of elements stored are only of Integers
2. This class is for demonstration purposes only, please use it as per your needs.
Heapsort:
1. insert n elements into the heap
2. if max heap perform extractMax n times, else extractMin operation n times
since time complexity of inserting a single element is O(log N), inserting n elements will be O(nlogn)
also, deleting an element takes O(logn) time, so deleting n elements will be O(nlogn)
hence combined together time complexity for heapsort will be: O(nlogn)
doing sorting in place? yes
stable?since all ops in heapsort they all involved swapping nodes with children, which means that sorting will not be stable,
initial ordering of equal keys will be unstable

*/
public class MyHeapSort {
    public void heapsort(int[] arr) {
        int n = arr.length;
        buildHeap(arr, n);
        for (int i = n - 1; i >= 0; i--) {
            //swap the root element with the last one in the array, this is as good as deleting the root but element is still present in the array
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, 0, i);//need to heapify to maintain heap(max/min) property
        }
    }

    public void heapify(int[] arr, int pos, int n) {
        int left = (2 * pos) + 1;
        int right = (2 * pos) + 2;
        int largest = pos;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != pos) {
            int temp = arr[pos];
            arr[pos] = arr[largest];
            arr[largest] = temp;
            heapify(arr, largest, n);
        }
    }

    public void buildHeap(int[] arr, int n) {
        //start from rightmost position in the array that has a child. Hence n/2 is the index
        for (int i = (n / 2) - 1; i >= 0; i--) {
            //try to validate if the heap property is maintained
            heapify(arr, i, n);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 33, 456, 0, 3, -90 };
        MyHeapSort heapSort = new MyHeapSort();
        heapSort.heapsort(arr);
        System.out.print("Sorted array is ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}