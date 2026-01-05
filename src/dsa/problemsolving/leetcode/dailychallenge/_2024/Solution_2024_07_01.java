package dsa.problemsolving.leetcode.dailychallenge._2024;
import java.util.*;

public class Solution_2024_07_01 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> m = new HashMap<>();
        for(int n : nums1) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for(int n : nums2) {
            if (m.containsKey(n)) {
                if (m.get(n) > 0) {
                    m.put(n, m.get(n) - 1);
                    res.add(n);
                }
            } else {
                continue;
            }
        }
        int[] op = new int[res.size()];
        int ctr=0;
        for(Integer x : res) {
            op[ctr++]=x;
        }
        return op;
    }
}
