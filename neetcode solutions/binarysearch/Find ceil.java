class Solution {
    public static int findCeil(int[] arr, int x) {
        int lo = 0, hi = arr.length - 1, ans = -1;
        //ceil is the largest integer <=x
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] <= x) {
                ans = arr[mid];
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }
}