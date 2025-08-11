package dsa.problemsolving.neetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem41 {
    /*
    You are given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
    The result should also be sorted in ascending order. An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
Example 1:

Input: arr = [2,4,5,8], k = 2, x = 6

Output: [4,5]
Example 2:

Input: arr = [2,3,4], k = 3, x = 1

Output: [2,3,4]
Constraints:

1 <= k <= arr.length <= 10,000.
-10,000 <= arr[i], x <= 10,000
arr is sorted in ascending order.
     */
    //2 pointer approach tc: O(n - k) space: O(k)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int lo=0, hi = arr.length - 1;
        //basically we are ruling out the furthest element each time either the leftmost, or the rightmost one.
        //When we get a window of size k, we found a solution, so if N is size of the input array, then we "rule out" N - k elements
        int ruleOut = arr.length - k;
        while(ruleOut-- > 0) {
            if (Math.abs(arr[lo] - x) <= Math.abs(arr[hi] - x)) {
                hi--;
            } else {
                lo++;
            }
        }
        for(int i=lo; i <= hi; i++) {
            res.add(arr[i]);
        }
        return res;
    }
    //without using "rule out" idea, and without using the abs, since we know "x" is always in the array, and arr is sorted
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int lo=0, hi = arr.length - 1;
        while(hi - lo >= k) {
            //this means arr[lo] is further away from x, than arr[hi], bring lo closer to x
            if (arr[lo] - x > arr[hi] - x) {
                lo++;
            } else {
                //else bring hi closer to x
                hi--;
            }
        }
        for(int i=lo; i <= hi; i++) {
            res.add(arr[i]);
        }
        return res;
    }
    //binary search + 2 pointers
    public List<Integer> findClosestElementsBin(int[] arr, int k, int x) {
        //first find the element x or an element closes to x
        //then set the locations of left = loc of le - 1, and right = loc of le + 1
        //continue expanding the window until you find elements closer to x
        int le=0, ri = arr.length - 1;
        while(le < ri) {
            int mid = (le + ri) / 2;
            if (arr[mid] < x) {
               le=mid + 1;
            } else {
                ri=mid;
            }
        }
        le = le - 1;
        ri = le + 1;
        while(ri - le - 1 < k) {
            //if le goes out of bounds or below 0
            if (le < 0) {
                ri++;
            } else if (ri >= arr.length) {
                //else if ri goes out of bounds or beyond the len of the input arr
                le--;
            } else if (Math.abs(arr[le] - x) <= Math.abs(arr[ri] - x)) {
                //else if you find a element closer to x i.e. arr[le] - x <= arr[ri] - x, go left
                le--;
            } else {
                //else continue going right
                ri++;
            }
        }
        //capture all the elements within the window of le and ri
        List<Integer> res = new ArrayList<>();
        for(int i=le; i <= ri; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
    }
}
