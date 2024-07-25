package dsa.problems;

/**
 * A popular problem, the one given in the CLRS book. This is a same problem under the unbounded knapsack category
 */
public class RodCutting {
    public static int solve(int[]L, int[]P, int C) {
        if (L.length == 0 || P.length == 0) {return 0;}
        int N = L.length;
        /*
        sometimes the Length array might contains fixed sizes a rod can be cut into.
        Eg: L=[3,5,1, 2], in this case the no.of rows will not be equal to the no.of cols. while deciding the
        dimensions for the dp table. So, even if the length of the rod is say N = 8, the allowed cuts will have to be chosen
        from the given L array. They will also have the corresponding price attached to it in a Price array. Even then
        the problem lies under the Unbounded Knapsack category. In this case the dp table dimension will be
        dp[L_Array.length +1][N + 1]
         */
        int[][]dp = new int[N+1][C + 1];
        //initialize
        for (int row = 1; row < N; row++) {
            dp[row][0] = 0; //for col 0, costs at i -> 0
        }
        for (int col = 1; col < P.length; col++) {
            dp[0][col] = 0; //for row 0, cost at i -> 0
        }
        //general traversal
        for (int length = 1; length < N; length++) {
            for (int price = 1; price < P.length; price++) {
                if(L[length - 1] <= price) {
                    dp[length][price] = Math.max(
                            dp[length - 1][price],
                            dp[length][price - L[length - 1]] + P[length - 1]
                    );
                } else {
                    dp[length][price] = dp[length - 1][price];
                }
            }
        }
        return dp[N][P.length];
    }
}
