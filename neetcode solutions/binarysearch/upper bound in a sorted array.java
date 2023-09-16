class Solution {
    public static int solve(int []arr, int x) {
        //lower bound in a sorted arr of x is the smallest index where a[index] > x
        int lo = 0, hi = arr.length - 1, ans = -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] > x) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }
}