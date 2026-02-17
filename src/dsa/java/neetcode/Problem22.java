package dsa.java.neetcode;

public class Problem22 {
    /*
You are given an array of characters which represents a string s. Write a function which reverses a string.

You must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Input: s = ["n","e","e","t"]

Output: ["t","e","e","n"]
Example 2:

Input: s = ["r","a","c","e","c","a","r"]

Output: ["r","a","c","e","c","a","r"]
 */
    //date:07/23/2025
    public static void reverseString(char[] s) {
        int le=0, ri = s.length - 1;
        while(le < ri) {
            char tt = s[le];
            s[le] = s[ri];
            s[ri] = tt;
            le++;
            ri--;
        }
    }
    public static void main(String[] args) {
        char[]cc = {'a','b','c','d'};
        reverseString(cc);
    }
}
