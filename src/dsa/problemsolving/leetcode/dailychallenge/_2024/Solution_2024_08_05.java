package dsa.problemsolving.leetcode.dailychallenge._2024;

/*
You are given a string word containing lowercase English letters.

Telephone keypads have keys mapped with distinct collections of lowercase English letters, which can be used to form words by pushing them. For example, the key 2 is mapped with ["a","b","c"], we need to push the key one time to type "a", two times to type "b", and three times to type "c" .

It is allowed to remap the keys numbered 2 to 9 to distinct collections of letters. The keys can be remapped to any amount of letters, but each letter must be mapped to exactly one key. You need to find the minimum number of times the keys will be pushed to type the string word.

Return the minimum number of pushes needed to type word after remapping the keys.

 */

import java.util.*;

public class Solution_2024_08_05 {
    public int minimumPushes(String word) {
        Map<Integer, Integer> m = new HashMap<>();
        for(char cc : word.toCharArray()) {
            int i = cc - 'a';
            m.put(i, m.getOrDefault(i, 0 ) + 1);
        }
        List<Integer> ff = new ArrayList<>(m.values());
        Collections.sort(ff, (a, b) -> Integer.compare(b, a));
        int ans=0;
        for(int i=0; i < ff.size(); i++) {
            // if(ff.get(i) == 0) {
            //     break;
            // }
            ans += (i / 8 + 1) * ff.get(i);
        }
        return ans;
    }

}
