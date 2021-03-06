package algorithms.dynamicprogramming.problems;

/**
 * LC# 72
 * F(m, n) = min cost of aligning sequences X1,X2,..Xm with Y1,Y2,...Yn
 * We support 3 operations match.mismatch, deletion and insertion
 * f(m,n) = could be f(m-1,n-1)(match or mismatch) or f(m-1,n)(deletion) and f(m, n-1) (insertion) in general
 * f(i, j) => min. cost of editing/aligning X[1..i] and Y[1..j], so Min {
 *          => f(i-1,j-1) => aligning first i-1 characters with the j-1 characters +
 *                          cost of aligning Xi with Yj(1 if mismatch, 0 if Xi=Yj)
 *          => f(i,j-1) => insertion case as the last pair, we would have Xm as is, and the second string shorter +
 *                          cost of (_, Yj) will be 1
 *         => f(i-1,j) => deletion case as the last pair, we would have Xi aligned with nothing +
                            cost of (Xi, _ ) will be 1
 *  }
 *  i varies from 0 to m, and j from 0 to n. No.of unique subproblems => (m+1)*(n+1)
 *  Time: O(m*n), space=O(m*n)
 */
public class EditDistance {
    public int solve(String s1, String s2) {
        int M = s1.length();
        int N = s2.length();
        int[][]dp = new int[M+1][N+1];
        for (int row = 1; row < M; row++) {
            dp[row][0] = row; //for col 0, cost at i -> rowId itself
        }
        for (int col = 1; col < N; col++) {
            dp[0][col] = col; //for row 0, cost at i -> colid itself
        }
        for (int row = 1; row < M; row++) {
            for (int col = 1; col < N; col++) {

                dp[row][col] = Math.min(
                        dp[row-1][col-1]  + ((s1.charAt(row-1) == s2.charAt(col-1)) ? 0 : 1),
                        Math.min(
                                dp[row][col-1] + 1,
                                dp[row-1][col] + 1));
            }
        }
        return dp[M][N];
    }
}
