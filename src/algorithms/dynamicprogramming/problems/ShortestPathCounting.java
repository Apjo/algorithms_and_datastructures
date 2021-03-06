package algorithms.dynamicprogramming.problems;
import java.util.*;
/**
 * LC# 62(similar)
 * A chess rook can move horizontally or vertically to any square in the same row or same column of the chessboard.
 * Find the no.of shortest paths by which a rook can move from one corner to diagonally opposite corner
 * Recurrence relation
 * F(rows, cols) = #no.of ways to reach cell (R,C) from (say) top-left corner
 *  Using decrease and conquer assume we have answers till (R-1,C) and (R, C-1), to reach the (R,C) loc. we have
 *  f(R, C) = f(R-1, C) + f(R, C-1)
 *  This is a 2D counting problem
 *  Extension: What is the grid is an irregular grid in the shape of an graph?
 */
public class ShortestPathCounting {
    public static int countPath(int M, int N) {
        int[][]dp = new int[M][N];
        //all the values will be 1 for first row(row=0), since there is only 1 path that goes from top-left corner to them
        for (int col = 0; col < N; col++) {
            dp[0][col] = 1;
        }
        //all the values will be 1 for first column(column=0), since there is only 1 path that goes from top-left corner to them
        for (int row = 0; row < M; row++) {
            dp[row][0] = 1;
        }
        //every path from top-left to bottom-right has to make N-1 horizontal and M-1 vertical moves
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[M - 1][N - 1];
    }
    //If the cell contains a 1 its an obstacle, else consider it as 0
    //LC#63
    //time: O(M*N), space=O(M*N)
    public static int countPathWithObstacles(int[][]g) {
        int M = g.length;
        int N = g[0].length;
        int[][]dp = new int[M][N];
        //values will be 1 for first row(row=0)
        for (int col = 1; col < N - 1; col++) {
            if(g[0][col] == 1) {
                dp[0][col] = 1;
            } else {
                //if no obstacle, inherit the previous value
                dp[0][col] = dp[0][col - 1];
            }
        }
        //values for first column(column=0)
        for (int row = 1; row < M - 1; row++) {
            if(g[row][0] == 1) {
                dp[row][0] = 1;
            } else {
                //if no obstacle, inherit the previous value
                dp[row][0] = dp[row - 1][0];
            }
        }
        //every path from top-left to bottom-right has to make N-1 horizontal and M-1 vertical moves
        for (int row = 1; row < M - 1; row++) {
            for (int col = 1; col < N - 1; col++) {
                if (g[row][col] == 1) {
                    dp[row][col] = 1;
                } else {
                    dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                }
            }
        }
        return dp[M - 1][N - 1];
    }
    public static void main(String[] args) {
        int[][]g = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        System.out.println("Count paths with size=2 " + countPath(2,2)); //2
        System.out.println("Count paths with obstacles= " + countPathWithObstacles(g)); //2
    }
}
