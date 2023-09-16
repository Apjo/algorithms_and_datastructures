class Solution {
    public static int findFloor(int []arr, int x) {
        //floor is the largest value <=x
        int lo= 0, hi = arr.length - 1;
        int ans = -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] <= x) {
                ans = arr[mid];
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}