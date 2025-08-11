package dsa.problemsolving.neetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
/*
Given an integer array nums and an integer k, return the k most frequent elements within the array.

The test cases are generated such that the answer is always unique.

You may return the output in any order.

Example 1:

Input: nums = [1,2,2,3,3,3], k = 2

Output: [2,3]
Example 2:

Input: nums = [7,7], k = 1

Output: [7]
 */
//date:07/23/2025
public class Problem13 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for(int ii: nums) {
            m.put(ii, m.getOrDefault(ii, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> minH = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.getValue(), b.getValue()));
        for(Map.Entry<Integer, Integer> e: m.entrySet()) {
            minH.offer(e);
            if(minH.size() > k) {
                minH.poll();
            }
        }
        int[]op = new int[k];
        int c = 0;
        while(!minH.isEmpty()) {
            op[c++] = minH.poll().getKey();
        }
        return op;
    }
    public static void main(String[] args) {
    }
}
