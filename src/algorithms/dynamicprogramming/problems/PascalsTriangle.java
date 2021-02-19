package algorithms.dynamicprogramming.problems;
//LC# 118

/**
 * Properties of pascal's triangle
 * C(n,k) = C(n-1, k) + C(n-1, k-1)
 * Symmetry: C(n, k) = C(n, n-k)
 * Row sum= 2^n
 * Closed form : C(n, k) = n!/k!*(n-k)!
 */
public class PascalsTriangle {
    public int[] solve(int N) {
        int[][]res = new int[N][N];
        //create a 1d array to hold an entry at the Nth level
        for (int row = 0; row < N; row++) {
            res[row] = new int[row + 1];
        }
        //we set all entries whose nC0 = 1 and nCn = 1
        for (int row = 0; row < N; row++) {
            res[row][0] = 1;
            res[row][row] = 1;
        }
        for (int row = 2; row < N; row++) {
            for(int entry = 1; entry < row - 1; entry++) {
                res[row][entry] = res[row - 1][entry] + res[row - 1][entry-1];
            }
        }
        return res[N - 1];
    }
}
