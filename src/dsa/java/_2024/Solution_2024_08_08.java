package dsa.problemsolving.leetcode.dailychallenge._2024;

/*
A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given a row x col grid of integers, how many 3 x 3 contiguous magic square subgrids are there?

Note: while a magic square can only contain numbers from 1 to 9, grid may contain numbers up to 15.

 */

import java.util.*;

public class Solution_2024_08_08 {

    private static boolean isMagic(int[][] G) {
        int[]rowSums = new int[3];
        int[]colSums = new int[3];
        int diag1=0, diag2=0;
        Set<Integer> visited = new HashSet<>();
        for(int i=0; i < G.length; i++) {
            for(int j=0; j < G[0].length; j++) {
                if (G[i][j] < 1 || G[i][j] > 9 || visited.contains(G[i][j])) {
                    return false;
                }
                visited.add(G[i][j]);
                rowSums[i] += G[i][j];
                colSums[j] += G[i][j];
            }
            diag1 += G[i][i];
            diag2 += G[i][2 - i];
        }

        for (int i = 0; i < 3; i++) {
            if (rowSums[i] != colSums[i]) {
                return false;
            }
            if (rowSums[i] != diag1 || rowSums[i] != diag2) {
                return false;
            }
            if (colSums[i] != diag1 || colSums[i] != diag2) {
                return false;
            }
        }
        return true;
    }
    private static int[][] get33(int[][] matrix, int startRow, int startCol) {
        int[][] submatrix = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((startRow + i) >= matrix.length || (startCol + j) >= matrix[0].length) {
                    continue;
                }
                submatrix[i][j] = matrix[startRow + i][startCol + j];
            }
        }

        return submatrix;
    }
    public int numMagicSquaresInside(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        if (R*C < 9) {
            return 0;
        }
        int ans=0;
        for(int i=0; i < grid.length; i++) {
            for(int j=0; j < grid[0].length; j++) {
                int[][]m = get33(grid, i, j);
                if (isMagic(m)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
