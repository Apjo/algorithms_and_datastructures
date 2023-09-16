class Solution {
    public static int findLowerBound(int[] arr, int x) {
        int lo=0, hi = arr.length - 1;
        int ans = arr.length;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] >= x) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }
}
