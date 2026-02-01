package dsa.problemsolving.java.neetcode;

public class Problem40 {
    /*
    You are given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target.
    If there is no such subarray, return 0 instead.A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: target = 10, nums = [2,1,5,1,5,3]
Output: 3
Explanation: The subarray [5,1,5] has the minimal length under the problem constraint.

Example 2:
Input: target = 5, nums = [1,2,1]
Output: 0
Constraints:

1 <= nums.length <= 100,000
1 <= nums[i] <= 10,000
1 <= target <= 1,000,000,000
     */
    //date:08/04/2025
    public int minSubArrayLen(int target, int[] nums) {
        int currSum=0, left=0, ans=Integer.MAX_VALUE;
        for(int right=0; right < nums.length; right++) {
            currSum += nums[right];
            while(left <= right && currSum >= target) {
                currSum -= nums[left];
                ans = Math.min(ans, right - left + 1);
                left++;
            }
        }
        return ans==Integer.MAX_VALUE ? 0 : ans;
    }
    public static void main(String[] args) {
    }
}
