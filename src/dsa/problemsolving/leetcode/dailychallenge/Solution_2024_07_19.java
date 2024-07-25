package dsa.problemsolving.leetcode.dailychallenge;

import java.util.*;
/*
You are given two arrays rowSum and colSum of non-negative integers where rowSum[i] is the sum of the elements in the ith row and colSum[j] is the sum of the elements of the jth column of a 2D matrix. In other words, you do not know the elements of the matrix, but you do know the sums of each row and column.

Find any matrix of non-negative integers of size rowSum.length x colSum.length that satisfies the rowSum and colSum requirements.

Return a 2D array representing any matrix that fulfills the requirements. It's guaranteed that at least one matrix that fulfills the requirements exists.

Example 1:

Input: rowSum = [3,8], colSum = [4,7]
Output: [[3,0],
         [1,7]]
Explanation:
0th row: 3 + 0 = 3 == rowSum[0]
1st row: 1 + 7 = 8 == rowSum[1]
0th column: 3 + 1 = 4 == colSum[0]
1st column: 0 + 7 = 7 == colSum[1]
The row and column sums match, and all matrix elements are non-negative.
Another possible matrix is: [[1,2],
                             [3,5]]
 */
public class Solution_2024_07_19 {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m=rowSum.length, n = colSum.length;
        int[][]arr = new int[m][n];
        for(int i=0; i < m; i++) {
            for(int j=0; j < n; j++) {
                int x = Math.min(rowSum[i], colSum[j]);
                arr[i][j] = x;
                rowSum[i] -= x;
                colSum[j] -= x;
            }
        }
        return arr;
    }
}
