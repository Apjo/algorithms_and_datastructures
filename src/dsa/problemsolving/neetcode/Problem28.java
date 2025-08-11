package dsa.problemsolving.neetcode;

public class Problem28 {
    /*
Given an array of integers numbers that is sorted in non-decreasing order.

Return the indices (1-indexed) of two numbers, [index1, index2], such that they add up to a given target number target and index1 < index2. Note that index1 and index2 cannot be equal, therefore you may not use the same element twice.

There will always be exactly one valid solution.

Your solution must use O(1) additional space.

Example 1:

Input: numbers = [1,2,3,4], target = 3

Output: [1,2]
Explanation:
The sum of 1 and 2 is 3. Since we are assuming a 1-indexed array, index1 = 1, index2 = 2. We return [1, 2].

Constraints:

2 <= numbers.length <= 1000
-1000 <= numbers[i] <= 1000
-1000 <= target <= 1000
     */
   //date:07/28/2025
    //time: O(n), space:O(1)
    public int[] twoSum(int[] numbers, int target) {
        int lo=0, hi = numbers.length - 1;
        int[]res = new int[2];
        while(lo < hi) {
            int ssum = numbers[lo] + numbers[hi];
            if (ssum == target) {
                res[0] = lo + 1;
                res[1] = hi + 1;
                return res;
            } else if (ssum < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return res;
    }
    public static void main(String[] args) {
    }
}
