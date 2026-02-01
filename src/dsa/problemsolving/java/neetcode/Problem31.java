package dsa.problemsolving.java.neetcode;

public class Problem31 {
    /*
    You are given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: nums = [1,2,3,4,5,6,7,8], k = 4

Output: [5,6,7,8,1,2,3,4]
Explanation:
rotate 1 steps to the right: [8,1,2,3,4,5,6,7]
rotate 2 steps to the right: [7,8,1,2,3,4,5,6]
rotate 3 steps to the right: [6,7,8,1,2,3,4,5]
rotate 4 steps to the right: [5,6,7,8,1,2,3,4]

Example 2:

Input: nums = [1000,2,4,-3], k = 2

Output: [4,-3,1000,2]
Explanation:
rotate 1 steps to the right: [-3,1000,2,4]
rotate 2 steps to the right: [4,-3,1000,2]

Constraints:

1 <= nums.length <= 100,000
-(2^31) <= nums[i] <= ((2^31)-1)
0 <= k <= 100,000
Follow up: Could you do it in-place with O(1) extra space?
     */
    //date: 07/31/2025
    public void rotate(int[] nums, int k) {
        int L = nums.length;
        k%=L;
        rev(nums, 0, L - 1);
        rev(nums, 0, k - 1);
        rev(nums, k, L - 1);
    }
    private static void rev(int[]arr, int i, int j) {
        while(i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
    //using extra space
    public void rotateExtraSpace(int[] nums, int k) {
        int[]B = new int[nums.length];
        for(int i=0; i < nums.length; i++) {
            int nextIndex = (i + k) % nums.length;
            B[nextIndex] = nums[i];
        }
        System.arraycopy(B, 0, nums, 0, nums.length);
    }
    public static void main(String[] args) {
    }
}
