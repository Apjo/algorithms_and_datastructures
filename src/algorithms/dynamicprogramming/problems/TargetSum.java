package algorithms.dynamicprogramming.problems;
//LC#494
public class TargetSum {
    //Using approach mentioned by Aditya Verma
    /*
    This approach basically talks about finding number of subsets having the difference in the sum of subsets
    to be equal to the target. You basically, split the positives and negatives, find the subset sum of each, then find
    difference.
    You end up using the CountSubsetSumWithDifference technique.
    category: 0/1knapsack
     */
    public static int solve(int[]A, int S) {
        return CountSubsetSumWithDifference.solve(A, S);
    }
    //using approach in LC, starting with recursion
    public static int solveRec(int []A, int S) {
        return solveIt(A, S, 0, 0);

    }
    private static int solveIt(int[]A, int S, int idx, int currSum) {
        if (idx < 0 && currSum == S) {
            return 1;
        }
        if (idx < 0) {
            return 0;
        }

       int pos =  solveIt(A, S, idx + 1, currSum + A[idx]);
       int neg = solveIt(A, S, idx + 1, currSum + -A[idx]);
       return pos + neg;
    }
    //using approach in LC, now using memoization
    public static int solveMemo(int []A, int S) {
        int [][]memo = new int[A.length + 1][S + 1];
        return solveItMemo(A, S, memo, 0, 0);
    }
    private static int solveItMemo(int[]A, int S, int[][]memo, int idx, int currSum) {
        if (memo[idx][currSum] == S) {
            return memo[idx][currSum];
        }
        if (idx < 0 && currSum == S) {
            return 1;
        }
        if (idx < 0) {
            return 0;
        }

        int pos =  solveItMemo(A, S, memo, idx + 1, currSum + A[idx]);
        int neg = solveItMemo(A, S, memo, idx + 1, currSum + -A[idx]);

        memo[idx][currSum] = pos + neg;

        return memo[idx][currSum];
    }

    /*
        The DP way could? follow the same approach we used in CountSubsetSumWithDifference
        Meaning, we know sum(subset1) + sum(subset2) = sum_array -- 1
        we also know sum(positive_subset) - sum(negative_subset) = target --2
        Adding 1 and 2
        2*sum(subset1) = sum_array + target
        sum(subset1) = sum_array + target / 2
        So, we only need to find the count of subsets having their target sum = sum_array + target / 2 (say count).
        Note: this count will be even.
     */
    int solveDp(int[]A, int S) {
        int sum = 0;
        for(int i: A) {
            sum+=A[i];
        }
        if (S < sum || (S + sum) % 2 > 0) { return 0; }
        int target = (S + sum) / 2;
        return countSubsetSumOptimal(A, target);
    }

    static int countSubsetSumOptimal(int[]A, int S) {
        int[]dp = new int[S + 1];
        dp[0] = 1;
        for (int k : A) {
            for (int j = S; j >= k; j--) {
                dp[j] += dp[j - k];
            }
        }
        return dp[S];
    }

}
