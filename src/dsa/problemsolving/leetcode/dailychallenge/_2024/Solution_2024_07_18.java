package dsa.problemsolving.leetcode.dailychallenge._2024;

import java.util.ArrayList;
import java.util.List;

/*
Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.

A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.

Example 1:

Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
Output: [15]
Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.

 */

import java.io.*;
import java.util.*;

public class Solution_2024_07_18 {
    public List<Integer> luckyNumbers (int[][] matrix) {
        Set<Integer> res = new HashSet<>();
        Set<Integer> res2 = new HashSet<>();
        for(int[] rr : matrix) {
            int mi = Arrays.stream(rr).min().getAsInt();
            res.add(mi);
        }
        System.out.println(res);
        for(int c =0; c < matrix[0].length; c++) {
            int ma = Integer.MIN_VALUE;
            for(int r=0; r < matrix.length; r++) {
                ma = Math.max(ma, matrix[r][c]);
                // res2.add(ma);
            }
            res2.add(ma);
        }
        System.out.println(res2);
        res2.retainAll(res);
        return new ArrayList<>(res2);
    }
}
