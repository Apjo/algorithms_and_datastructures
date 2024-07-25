package dsa.problems.subsetsum;

/*
 * Given an array = [1,1,2,3] return the count of no.of subsets whose difference in their sum is equal to the given target difference S(say 1)
 * All numbers will be positive
 * category: 0/1knapsack
 */

public class CountSubsetSumWithDifference {
    public static int solve(int []A, int D) {
        int sum = 0;
        for (int i : A) {
            sum+=i;
        }
        /*
        TRICK:
            we want to know if sum(subset1) - sum(subset2) == D -- 1
            We know sum(Array) = S
            i.e., sum(subset1) + sum(subset2) = S -- 2
            Adding equations 1 and 2 we get
            2 sum(subset1) = D + S
            i.e. sum(subset1) = D + S / 2
            So, we need to find count of all such subsets whose count == D + S / 2, which breaks down the problem merely to
            countSubsetSum
         */
        int finalCount = (D + sum) / 2;
        return CountSubsetSum.countSubsetSum(A, finalCount);
    }
    public static void main(String[] args) {
        int[]A = new int[]{1, 1, 2, 3};
        System.out.println(solve(A, 1)); //4
    }
}
