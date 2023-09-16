class Solution {
    public static int searchInsertPosition(int[] arr, int x) {
        int lo = 0, hi = arr.length - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] == x) {
                return mid;
            } else if(arr[mid] < x) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;//basically trying to find the smallest index where a[index] <= x
    }
    //solved using the lower bound logic
    public static int solve(int[] arr, int x) {
        int lo=0, ans = -1, hi = arr.length - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] <= x) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }
}