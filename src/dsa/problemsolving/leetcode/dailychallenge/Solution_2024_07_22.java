package dsa.problemsolving.leetcode.dailychallenge;

/*
Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

Example 1:

Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
Example 2:

Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
Example 3:

Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]

Constraints:

1 <= nums.length <= 100
-100 <= nums[i] <= 100
 */

import java.util.*;

public class Solution_2024_07_22 {
    public int[] frequencySort(int[] nums) {
        List<Map.Entry<Integer, Integer>> res = new ArrayList<>();
        Map<Integer, Integer> m = new HashMap<>();
        for(int i : nums) {
            m.put(i, m.getOrDefault(i, 0) + 1);
        }
        //sort based on frequency values in ASC order
        PriorityQueue<Map.Entry<Integer, Integer>> p1 = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
        for(Map.Entry<Integer, Integer> e : m.entrySet()) {
            p1.offer(e);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> p2 = new PriorityQueue<>((e1, e2) -> Integer.compare(e2.getKey(), e1.getKey()));

        while(!p1.isEmpty()) {
            res.add(p1.poll());
        }
        Collections.sort(res, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                return (e1.getValue() == e2.getValue() ? Integer.compare(e2.getKey(), e1.getKey()) : Integer.compare(e1.getValue(), e2.getValue()));
            }
        });
        int[] op = new int[nums.length];
        int c=0;
        for(Map.Entry<Integer,Integer> ii : res) {
            for(int occur = 0; occur < ii.getValue(); occur++) {
                op[c++] = ii.getKey();
            }

        }
        return op;
    }
}
