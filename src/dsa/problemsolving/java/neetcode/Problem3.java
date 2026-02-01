package dsa.problemsolving.java.neetcode;

/*
Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.

An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

Example 1:

Input: s = "racecar", t = "carrace"

Output: true
Example 2:

Input: s = "jar", t = "jam"

Output: false
 */
//date:07/23/2025
public class Problem3  {
    public boolean isAnagram(String s, String t) {
        if (t.length() != s.length()) { return false; }
        if (t.equals(s)) { return true; }
        int[]freq = new int[26];
        for(int i=0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = c - 'a';
            freq[d]++;
        }
        for(int i=0; i < t.length(); i++) {
            char c = t.charAt(i);
            int d = c - 'a';
            freq[d]--;
        }
        for(int i : freq) {
            if(i != 0) {
                return false;
            }
        }
        return true;
    }
}
