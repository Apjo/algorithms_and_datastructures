package dsa.problemsolving.neetcode;

/*
You are given an integer array nums and an integer val. Your task is to remove all occurrences of val from nums in-place.

After removing all occurrences of val, return the number of remaining elements, say k, such that the first k elements of nums do not contain val.

Note:

The order of the elements which are not equal to val does not matter.
It is not necessary to consider elements beyond the first k positions of the array.
To be accepted, the first k elements of nums must contain only elements not equal to val.
Return k as the final result.

Example 1:

Input: nums = [1,1,2,3,4], val = 1

Output: [2,3,4]
Explanation: You should return k = 3 as we have 3 elements which are not equal to val = 1.

Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2

Output: [0,1,3,0,4]
Explanation: You should return k = 5 as we have 5 elements which are not equal to val = 2.
 */
public class Problem7 {
    public int removeElement(int[] nums, int val) {
        int pos = 0;
        for(int i=0; i < nums.length; ) {
            if(nums[i] != val) {
                nums[pos] = nums[i];
                pos++;
            }
            i++;
        }
        return pos;
    }
    public static void main(String[] args) {
    }
}
