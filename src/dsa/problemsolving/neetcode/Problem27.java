package dsa.problemsolving.neetcode;

public class Problem27 {
    /*
    date:07/27/2025
    You are given an integer array nums sorted in non-decreasing order. Your task is to remove duplicates from nums in-place so that each element appears only once.

After removing the duplicates, return the number of unique elements, denoted as k, such that the first k elements of nums contain the unique elements.

Note:

The order of the unique elements should remain the same as in the original array.
It is not necessary to consider elements beyond the first k positions of the array.
To be accepted, the first k elements of nums must contain all the unique elements.
Return k as the final result.

Example 1:

Input: nums = [1,1,2,3,4]

Output: [1,2,3,4]
Explanation: You should return k = 4 as we have four unique elements.

Example 2:

Input: nums = [2,10,10,30,30,30]

Output: [2,10,30]
Explanation: You should return k = 3 as we have three unique elements.

Constraints:

1 <= nums.length <= 30,000
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.
     */
    //date:07/28/2025
    //time: O(n), space:O(1)
    //NOTES: Observe the initial value of the pos variable keeping track of the duplicates in version 1 and version2 of the codes
    public int removeDuplicatesv1(int[] nums) {
        int pos = 0;
        for(int i=1; i < nums.length; i++) {
            if (nums[pos] != nums[i]) {
                pos++;
                nums[pos] = nums[i];
            }
        }
        return pos + 1;
    }
    public int removeDuplicatesv2(int[] nums) {
        int pos = 1;
        for(int i=0; i < nums.length; i++) {
            if (nums[pos - 1] != nums[i]) {
                nums[pos] = nums[i];
                pos++;
            }
        }
        return pos;
    }
    //time: O(n), space:O(1)
    public int removeDuplicatesv3(int[] nums) {
        int le=0, ri = 0;
        while(le < nums.length) {
            nums[le] = nums[ri];
            while(ri < nums.length && nums[le] == nums[ri]) {
                ri++;
            }
            le++;
        }
        return le;
    }
    public static void main(String[] args) {
    }
}
