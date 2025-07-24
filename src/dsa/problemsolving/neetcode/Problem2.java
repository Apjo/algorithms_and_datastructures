package dsa.problemsolving.neetcode;

import java.io.*;
import java.util.*;
/*
Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.

Example 1:

Input: nums = [1, 2, 3, 3]

Output: true

Example 2:

Input: nums = [1, 2, 3, 4]

Output: false
 */
public class Problem2 {
    public boolean hasDuplicate(int[] nums) {
        int L = nums.length;
        Set<Integer> hs = new HashSet<>();
        for(int i:nums) {
            hs.add(i);
        }
        return hs.size() == L ? false:true;
    }
}
