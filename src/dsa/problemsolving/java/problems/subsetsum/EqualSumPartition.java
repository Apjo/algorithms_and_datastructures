package dsa.problemsolving.java.problems.subsetsum;

/**
 * Given an array = [1,5,11,5] determine if the input arr. can be split exactly in half such that
 * sum of elements in both the halves are equal.
 * All numbers will be positive
 * category: 0/1knapsack
 */
public class EqualSumPartition {
    public static boolean solve(int[]A) {
        int N = A.length;
        if (N == 0) { return false; }
        int s = 0;
        for (int j : A) {
            s += j;
        }
        if(s%2 != 0) { return false; }
        return SubsetSum.hasSubsetSum(A, s/2);
    }
}
