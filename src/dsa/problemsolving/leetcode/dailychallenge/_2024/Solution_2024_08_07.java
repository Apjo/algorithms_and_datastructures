package dsa.problemsolving.leetcode.dailychallenge._2024;

/*
You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.

You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually, we reach all rows * cols spaces of the grid.

Return an array of coordinates representing the positions of the grid in the order you visited them.

 */

import java.util.*;

public class Solution_2024_08_07 {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int R = rows;
        int C = cols;
        int[][]op = new int[R*C][2];
        List<int[]> res = new ArrayList<>();
        res.add(new int[]{rStart, cStart});
        int distance=1;

        while(res.size() < R * C) {
            //go right
            for(int i=1; i <= distance; i++) {
                cStart++;
                addToList(res, R, C, rStart, cStart);
            }
            //go down
            for(int i=1; i <= distance; i++) {
                rStart++;
                addToList(res,R, C, rStart, cStart);
            }
            //len of spiral increases after 2 operations
            distance++;
            //go left
            for(int i=1; i <= distance; i++) {
                cStart--;
                addToList(res,R, C, rStart, cStart);
            }
            //go up
            for(int i=1; i <= distance; i++) {
                rStart--;
                addToList(res,R, C, rStart, cStart);
            }
            distance++;
        }
        return res.toArray(op);
    }
    private static void addToList(List<int[]> res, int R, int C, int r, int c) {
        if(r < 0 || r >= R || c < 0 || c >= C) {
            return;
        }
        res.add(new int[]{r, c});
    }


}
