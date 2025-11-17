package prep.meta;

public class FirstAndLastPosInArr {
    //find first and last positions of an element in a sorted array
    public int[] searchRange(int[] nums, int target) {
        //does target always lie in the array?
        //if array is empty, or null
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if(nums.length == 1 && nums[0] == target) {
            return new int[]{0,0};
        }
        //do binary search for finding a lower bound or the first position
        int le = findLower(nums, target, 0, nums.length - 1, -1);
        //do binary search for finding a upper bound or the last position
        int ri = findUpper(nums, target, 0, nums.length - 1, -1);
        //return the positions in an array
        return new int[]{le, ri};
    }
    private static int findLower(int[] arr, int target, int lo, int hi, int idx) {
        while(lo <= hi) {
            int mid = lo + (hi - lo / 2);
            if (arr[mid] == target)  {
                idx=mid;
                hi = mid - 1;
            } else if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return idx;
    }
    private static int findUpper(int[] arr, int target, int lo, int hi, int idx) {
        while(lo <= hi) {
            int mid = lo + (hi - lo / 2);
            if (arr[mid] == target)  {
                idx=mid;
                lo = mid + 1;
            } else if(arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return idx;
    }
    //variant1: count all unique elements
    public int countUnique(int[] arr) {
        int N = arr.length, i = 0, count = 0;
        //for each index in arr, we need to find if there exists duplicate elements.We will use a binary search
        //once this inner binary search completes, the hi pointer will always point to the last location of this element at index i
        //so the next time we iterate we start i from hi + 1, while we increment our counter to count the elements after the inner binary search completes
        while(i < N) {
            count++;
            int target = arr[i];
            int lo=0, hi = arr.length - 1;
            while(lo <= hi) {
                int mid = lo + ((hi - lo) / 2);
                if (arr[mid] <= target) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            count++;
            i = hi + 1;
        }
        return count;
    }
    //variant2: find number of occurrences of the target element
    public int countOccurrences(int[]arr, int target) {
        int from = searchRange(arr, target)[0];
        int to = searchRange(arr, target)[1];
        return to - from  + 1;
    }
    public static void main(String[] args) {
    }
}
