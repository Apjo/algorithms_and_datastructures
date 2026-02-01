package dsa.problemsolving.java.neetcode;

/*
You are given an integer array nums of length n. Create an array ans of length 2n where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).

Specifically, ans is the concatenation of two nums arrays.

Return the array ans.

Example 1:

Input: nums = [1,4,1,2]

Output: [1,4,1,2,1,4,1,2]
 */
//date:07/23/2025
public class Problem1 {
    public int[] getConcatenation(int[] nums) {
        int[]ans2 = new int[nums.length * 2];
        int L = nums.length;
        for(int i=0; i < ans2.length; i++) {
            ans2[i] = nums[i % L];
        }
        return ans2;

    }
}
