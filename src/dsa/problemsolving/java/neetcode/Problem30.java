package dsa.problemsolving.java.neetcode;

import java.util.*;

//date:07/30/2025
public class Problem30 {
    /*
You are given an integer array nums of size n, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

Note: [1,0,3,2] and [3,0,1,2] are considered as same quadruplets.

Example 1:

Input: nums = [3,2,3,-3,1,0], target = 3

Output: [[-3,0,3,3],[-3,1,2,3]]
Example 2:

Input: nums = [1,-1,1,-1,1,-1], target = 2

Output: [[-1,1,1,1]]
Constraints:
1 <= nums.length <= 200
-1,000,000,000 <= nums[i] <= 1,000,000,000
-1,000,000,000 <= target <= 1,000,000,000
     */
    //date:07/30/2025
    //tc:O(n^3)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> res = new HashSet<>();
        int L = nums.length;
        Arrays.sort(nums);
        //NOTE: 2 for loops, in case of 3 sum we needed just 1
        for(int i=0; i < L; i++) {
            for(int j=i+1; j < L; j++) {
                int le=j+1, ri = L - 1;
                while(le < ri) {
                    long ss = ((long)nums[i]+(long)nums[j]+(long)nums[le]+(long)nums[ri]);
                    if (ss == target) {
                        res.add(Arrays.asList(nums[i],nums[j],nums[le],nums[ri]));
                        le++;
                        ri--;
                    } else if (ss < target) {
                        le++;
                    } else {
                        ri--;
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }
    //no extra space, tc: O(N^3)
    public List<List<Integer>> fourSumNoExtraSpace(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int L = nums.length;
        Arrays.sort(nums);
        for(int i=0; i < L; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for(int j=i+1; j < L; j++) {
                if (j > i + 1 && nums[j]==nums[j-1]) {
                    continue;
                }
                int le=j+1, ri = L - 1;
                while(le < ri) {
                    long ss = ((long)nums[i]+(long)nums[j]+(long)nums[le]+(long)nums[ri]);
                    if (ss == target) {
                        res.add(Arrays.asList(nums[i],nums[j],nums[le],nums[ri]));
                        le++;
                        ri--;
                    } else if (ss < target) {
                        le++;
                    } else {
                        ri--;
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
    }
}
