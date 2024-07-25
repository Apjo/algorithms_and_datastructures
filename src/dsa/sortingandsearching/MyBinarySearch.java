package dsa.sortingandsearching;

public class MyBinarySearch {

    public int binarySearchRecursive(int[] arr, int k, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == k)
                return mid;
            else if (arr[mid] > k)
                return binarySearchRecursive(arr, k, low, mid - 1);
            else
                return binarySearchRecursive(arr, k, mid + 1, high);
        }
        return -1;
    }

    public int binarySearchIterative(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == k)
                return mid;
            else if (arr[mid] > k)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }

    public int mySqrt(int x) {
        int lo = 1;
        int hi = x;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return hi;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        MyBinarySearch bs = new MyBinarySearch();
        System.out.println("Element found at index(recursive): " + bs.binarySearchRecursive(arr, 2, 0, 4));
        int[] arr2 = { -99, 2, 3, 423, 51 };
        System.out.print("Element found at index(iterative): " + bs.binarySearchIterative(arr2, 423));
    }
}