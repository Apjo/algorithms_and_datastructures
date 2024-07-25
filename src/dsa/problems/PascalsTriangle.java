package dsa.problems;
//LC# 118

/**
 * Properties of pascal's triangle
 * C(n,k) = C(n-1, k) + C(n-1, k-1)
 * Symmetry: C(n, k) = C(n, n-k)
 * Row sum= 2^n
 * Closed form : C(n, k) = n!/k!*(n-k)!
 * Typical triangular array problem, wherein we have variable no. of columns, since no.of cols in each row, deeends on what
 * rowid you are in
 */
public class PascalsTriangle {
    public int[] solve(int N) {
        int[][]res = new int[N][N];

        //create a 1d array to hold an entry at the Nth level, where no.of columns for row i = i + 1
        for (int row = 0; row < N; row++) {
            res[row] = new int[row + 1];
        }
        res[0][0] = 1;
        //we set all entries whose nC0 = 1 and nCn = 1. The left and right "walls"
        //Instead of this, since we know we are initializing the walls with a 1, we could just eliminate this for loop
        // and initialize the entire table with a 1
        for (int row = 1; row < res.length - 1; row++) {
            res[row][0] = 1; //left most entry
            res[row][res.length - 1] = 1; //right most entry
        }
        for (int row = 2; row < res.length - 1; row++) {
            //for a given row, the final col. index = len(res[row] - 1), hence, the penultimate col. index = len - 2
            for(int entry = 1; entry < res.length - 2; entry++) {
                res[row][entry] = res[row - 1][entry] + res[row - 1][entry-1];
            }
        }
        return res[N - 1];
    }
}
