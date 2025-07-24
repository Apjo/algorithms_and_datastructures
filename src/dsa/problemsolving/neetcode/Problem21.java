package dsa.problemsolving.neetcode;
/*
You are given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.

You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

Example 1:

Input: nums = [-2,-1,0]

Output: 1
Example 2:

Input: nums = [1,2,4]

Output: 3
Example 3:

Input: nums = [1,2,4,5,6,3,1]

Output: 7
 */
public class Problem21 {
    public int firstMissingPositive(int[] nums) {
        int L = nums.length;
        int i=0;
        while(i < L) {
            while(nums[i] != i+1) {
                int destIndex = nums[i] - 1;
                if (destIndex >= 0 && destIndex < L && nums[i] != nums[destIndex]) {
                    int tt = nums[i];
                    nums[i] = nums[destIndex];
                    nums[destIndex] = tt;
                } else {
                    break;
                }
            }
            i++;
        }
        for(int idx=0; idx < L; idx++) {
            if(nums[idx] != idx + 1) {
                return idx + 1;
            }
        }
        return L + 1;
    }
    public static void main(String[] args) {
    }
}
