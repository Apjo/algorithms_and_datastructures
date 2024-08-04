package dsa.problemsolving.leetcode.dailychallenge;

/*
You are given the array nums consisting of n positive integers. You computed the sum of all non-empty continuous subarrays from the array and then sorted them in non-decreasing order, creating a new array of n * (n + 1) / 2 numbers.

Return the sum of the numbers from index left to index right (indexed from 1), inclusive, in the new array. Since the answer can be a huge number return it modulo 109 + 7.

Example 1:

Input: nums = [1,2,3,4], n = 4, left = 1, right = 5
Output: 13
Explanation: All subarray sums are 1, 3, 6, 10, 2, 5, 9, 3, 7, 4. After sorting them in non-decreasing order we have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 1 to ri = 5 is 1 + 2 + 3 + 3 + 4 = 13.
Example 2:

Input: nums = [1,2,3,4], n = 4, left = 3, right = 4
Output: 6
Explanation: The given array is the same as example 1. We have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 3 to ri = 4 is 3 + 3 = 6.
Example 3:

Input: nums = [1,2,3,4], n = 4, left = 1, right = 10
Output: 50


Constraints:

n == nums.length
1 <= nums.length <= 1000
1 <= nums[i] <= 100
1 <= left <= right <= n * (n + 1) / 2
 */

import java.util.*;

public class Solution_2024_08_03 {
    private static void printL(List<Integer> ll ) {
        for(Integer r: ll) {
            System.out.print(r + " ");
        }
        System.out.println();
    }
    int MOD=1000000007;
    public int rangeSum(int[] nums, int n, int left, int right) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> res2 = new ArrayList<>();
        for(int i=0; i < n; i++) {
            solve2(nums,i, 0, res2);
        }

        Collections.sort(res2);
        System.out.println("subarrays=" + res2.size());
        printL(res2);

        int ans=0;
        for(int i=left - 1; i < right; i++) {
            ans= (ans + res2.get(i)) % MOD;
        }
        return ans;
    }
    private static List<Integer> getSum(List<List<Integer>> res) {
        List<Integer> op = new ArrayList<>();
        for(List<Integer> rr : res) {
            int ss = rr.stream().mapToInt(Integer::intValue).sum();
            //System.out.println("SUM= "+ss);
            op.add(ss);
        }
        Collections.sort(op);
        return op;
    }
    private static void solve(int[] arr, int st, List<List<Integer>> res, List<Integer>coll) {
        if (st >= arr.length) {

            return;
        }
        // for(int i=st; i < arr.length; i++) {
        // coll.add(arr[i]);
        coll.add(arr[st]);
        res.add(new ArrayList<>(coll));
        solve(arr,st + 1, res, coll);
        // solve(arr, i + 1, res, coll);
        coll.remove(coll.size() - 1);
        // }
    }
    private static void solve2(int[] arr, int st, int currSum, List<Integer>coll) {
        if (st >= arr.length) {
            return;
        }
        //int ss = 0;
        //for(int i=st; i < arr.length; i++) {
        // coll.add(arr[i]);
        currSum += arr[st];
        coll.add(currSum);
        solve2(arr,st + 1, currSum, coll);
    }
}
