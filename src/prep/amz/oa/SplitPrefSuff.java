package prep.amz.oa;
/*
Amazon Prime Day is a day where many items are put on sale for Amazon Prime members. A list of sale items is assembled where each item is assigned a category denoted by a lowercase English letter.

Since the sale is to be held on two different days, the company has decided to split the list of items into two contiguous non-empty sub-lists - a prefix and a suffix. To ensure that both days share a sufficient number of similar items, they also need to split it in a way such that the number of distinct categories shared by both sub-lists is greater than k.

Formally, given a string, categories, find the number of ways to split the string into exactly two contiguous non-empty substrings such that the number of distinct characters occurring in both the substrings is greater than a given integer k.

Function Description

Complete the function splitPrefixSuffix in the editor.

splitPrefixSuffix has the following parameters:

string categories: the categories
int k: shared distinct categories must be greater than this value
Returns

int: the number of ways to split the given string

Example 1:

Input: categories = "abbcac", k = 1
Output: 2
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SplitPrefSuff {
    public int splitPrefixSuffix(String categories, int k) {
        Map<Character, Integer> freq = new HashMap<>();
        Set<Character> seen = new HashSet<>();
        for(char cc : categories.toCharArray()) {
            freq.put(cc, freq.getOrDefault(cc, 0) + 1);
            seen.add(cc);
        }
        int ans = 0, N = categories.length();
        for(int i=0; i < N - 1; i++) {
            char curr = categories.charAt(i);
            freq.put(curr, freq.get(curr) - 1);
            if(freq.get(curr) == 0) {
                freq.remove(curr);
                seen.remove(curr);
            }

        }
    }
    public static void main(String[] args) {
    }
}
