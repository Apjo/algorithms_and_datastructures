package prep.amz.oa;
/*
Amazon is developing an efficient string matching library. Develop a prototype service that matches a simple pattern with a text. There are two arrays of strings, text, and pat, each of size n. Each string in pat is a regex expression that contains exactly one wildcard character (*).

A wildcard character (*) matches any sequence of zero or more lowercase English letters. A regex matches some string if it is possible to replace the wildcard character with some sequence of characters such that the regex expression becomes equal to the string. No other character can be changed. For example, regex "abc*bcd" matches "abcbcd", "abcefgbcd" and "abccbcd" whereas it does not match the strings "abcbd", "abzbcd", "abcd".

For every i from 1 to n, your task is to find out whether pat[i] matches text[i]. Return the answer as an array of strings of size n where the ith string is "YES" if pat[i] matches text[i], and "NO" otherwise.

Note: The implementation shall not use any in build regex libraries.

Function Description

Complete the function matchStrings in the editor below.

matchStrings has the following parameters:

String text[n]: the strings to test
String pat[n]: the patterns to match
Returns

String[n]: "YES" or "NO" answers to the queries

𓍢ִ🌷͙֒Credit to MasterKhan ദ്ദി(˵ •̀ ᴗ - ˵ ) ✧

Example 1:

Input: text = ["code", "coder"], pat = ["co*d", "co*er"]
Output: ["NO", "YES"]
Explanation:

Given n = 2, text = ["code", "coder"], pat = ["co*d", "co*er"],

text[0] = "code", pat[0] = "co*d", "NO", the suffixes do not match


text[1] = "coder", pat[1] = "co*er", "YES", the prefixes and suffixes match


Here prefix of a string is defined as any substring that starts at the beginning of the string and suffix of a string is defined as any substring that ends at the end of the string.

Return ["NO", "YES"].
 */

import java.util.ArrayList;
import java.util.List;

public class StringMatching {
    public static List<String> solve(String[] text, String[] pat) {
        List<String> res = new ArrayList<>();

        for(int i=0; i < text.length; i++) {
            String t = text[i];
            String p = pat[i];
            int starIndex = p.indexOf("*");
            String pref = p.substring(0, starIndex);
            String suff = p.substring(starIndex+1);

            if (t.startsWith(pref) && t.endsWith(suff)) {
                // Check if the combined length of prefix and suffix is less than or equal to text length
                if(pref.length() + suff.length() <= t.length()) {
                    res.add("YES");
                } else {
                    res.add("NO");
                }
            } else {
                res.add("NO");
            }
        }
        return res;

    }
    public static void main(String[] args) {
    }
}
