package dsa.java.neetcode;

/*
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times in the array. You may assume that the majority element always exists in the array.

Example 1:

Input: nums = [5,5,1,1,1,5,5]

Output: 5
Example 2:

Input: nums = [2,2,2]

Output: 2
Constraints:

1 <= nums.length <= 50,000
-1,000,000,000 <= nums[i] <= 1,000,000,000
Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
//date:07/23/2025
public class Problem8 {
    //Boyer Moore Voting algo:https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
    public int majorityElement(int[] nums) {
        int candidate = nums[0], ctr=1;
        for(int ii : nums) {
            if(ctr == 0) {
                //When processing an element x, if the counter is zero, the algorithm stores x as its remembered sequence element and sets the counter to one
                candidate = ii;
                ctr=1;
            } else if(ii == candidate) {
                //Otherwise, it compares x to the stored element and either increments the counter (if they are equal)
                ctr++;
            } else {
                //or decrements the counter (otherwise).
                ctr-=1;
            }
        }
        return candidate;

    }
    public static void main(String[] args) {
    }
}
