package dsa.problemsolving.neetcode;

import java.util.*;
/*
Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.

An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

Example 1:

Input: strs = ["act","pots","tops","cat","stop","hat"]

Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
Example 2:

Input: strs = ["x"]

Output: [["x"]]
Example 3:

Input: strs = [""]

Output: [[""]]
 */
//date:07/23/2025
public class Problem6 {
    private static String hashIt(String s) {
        int[]ctr = new int[26];
        for(char cc : s.toCharArray()) {
            ctr[cc - 'a']++;
        }
        String gg =  Arrays.toString(ctr);
        return gg;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> freq = new HashMap<>();
        for(String s : strs) {
            String hh = hashIt(s);
            if (!freq.containsKey(hh)) {
                freq.put(hh, new ArrayList<>());
            } else {
                freq.get(hh).add(s);
            }
        }
        return new ArrayList<>(freq.values());
    }
    public static void main(String[] args) {
    }
}
