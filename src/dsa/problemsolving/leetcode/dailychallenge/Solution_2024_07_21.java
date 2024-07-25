package dsa.problemsolving.leetcode.dailychallenge;

/*
You are given an array of strings names, and an array heights that consists of distinct positive integers. Both arrays are of length n.

For each index i, names[i] and heights[i] denote the name and height of the ith person.

Return names sorted in descending order by the people's heights.

Example 1:

Input: names = ["Mary","John","Emma"], heights = [180,165,170]
Output: ["Mary","Emma","John"]
Explanation: Mary is the tallest, followed by Emma and John.
Example 2:

Input: names = ["Alice","Bob","Bob"], heights = [155,185,150]
Output: ["Bob","Alice","Bob"]
Explanation: The first Bob is the tallest, followed by Alice and the second Bob.

 */
import java.util.*;

public class Solution_2024_07_21 {
    class Record implements Comparable<Record> {
        String n;
        int h;
        public Record(String a, int n) {
            this.n = a;
            this.h = n;
        }
        @Override
        public int compareTo(Record e) {
            return Integer.compare(e.h, this.h);
        }

    }
    public String[] sortPeople(String[] names, int[] heights) {
        List<Record> r = new ArrayList<>();
        for(int i=0; i < heights.length; i++) {
            r.add(new Record(names[i], heights[i]));
        }
        Collections.sort(r);
        String[]op = new String[names.length];
        int ctr=0;
        for(Record d : r) {
            op[ctr++] = d.n;
        }
        return op;
    }
}
