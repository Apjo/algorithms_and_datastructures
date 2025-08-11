package dsa.problemsolving.neetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem35 {
    /*
    You are given an integer array nums and an integer k, return true
    if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k, otherwise return false.

Example 1:

Input: nums = [1,2,3,1], k = 3

Output: true
Example 2:

Input: nums = [2,1,2], k = 1

Output: false
Constraints:

1 <= nums.length <= 100,000
-1,000,000,000 <= nums[i] <= 1,000,000,000
0 <= k <= 100,000
     */
    //date:08/01/2025
    //using map
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int right = 0; right < nums.length; right++) {
            if (map.containsKey(nums[right]) && right - map.get(nums[right]) <= k) {
                return true;
            }
            map.put(nums[right], right);
        }
        return false;
    }
    //using hash set
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        int left=0;
        for(int right=0; right < nums.length; right++) {
            if(right - left > k) {
                //drop from left
                window.remove(nums[left]);
                left++;
            }
            if (window.contains(nums[right])) {
                return true;
            }
            window.add(nums[right]);
        }
        return false;
     }
        public static void main(String[] args) {
    }
}
