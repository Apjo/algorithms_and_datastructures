class Solution {
    int[] solve(int[] arr, int x) {
        int lo = 0, hi = arr.length - 1;
        int first = -1, last = -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            //to get the first index we can treat it as finding the lower bound of the element
            // i.e. the smallest index where a[index] >= x
            if (arr[mid] >= x) {
                first = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        //we need to make sure that the index returned is not a "hypothetical" index, and the element at that index == x
        if (first == arr.length || arr[first] != x) { return new int {-1, -1}; }
        //the last index
        lo = 0, hi = arr.length - 1;

        return first == -1 || last == -1 ? new int{-1, -1} : new int{first, last};
    }
}