package prep.amz.oa;

import java.util.Arrays;
/*
Amazon has millions of products sold on its e-commerce website, and each product is identified by its product code.

Given an array of n productCodes and order, a string that represents the precedence of characters, sort the productCodes in lexicographically increasing order per the precedence.

Note: Lexicographical order is defined in the following way. When we compare strings s and t, first we find the leftmost position with differing characters: sl and tl. If there is no such position (i.e. s is a prefix of t or vice versa) the shortest string is less. Otherwise, we compare characters sl and t[i] according to their order in the given precedence order.

Function Description

Complete the function sortProductCodes in the editor below. sortProductCodes has the following parameter(s):


string order: the new precedence order string
productCodes[n]: the array to sort
Returns

string[n]: the productCodes array in sorted order

Example 1:

Input: order = "abcdefghijklmnopqrstuvwxyz", productCodes = ["adc", "abc"]
Output: ["abc", "adc"]
Explanation:

 Consider the strings "adc" and "abc", the first point of difference is at position 1 (assuming start index is 0), and 'd'>'b' according to the given precedence order.
 */
public class SortProductCodes {
    public static String[] sortProductCodes(String order, String[] productCodes) {
        int[]map = new int[26];
        for(int i=0; i < order.length(); i++) {
            char c = order.charAt(i);
            map[c - 'a'] = i;
        }
        Arrays.sort(productCodes, (a,  b) -> {
            int len = Math.min(a.length(), b.length());
            for(int i=0; i <len; i++) {
                char A = a.charAt(i);
                char B = b.charAt(i);
                if (A != B) {
                    return map[A - 'a'] - map[B - 'b'];
                }
            }
            return a.length() - b.length();
        });
        return productCodes;
    }
    public static void main(String[] args) {
        assert sortProductCodes("abcdefghijklmnopqrstuvwxyz", new String[]{"a", "xyz"})
                .equals(new String[]{"a", "xyz"});
    }
}
