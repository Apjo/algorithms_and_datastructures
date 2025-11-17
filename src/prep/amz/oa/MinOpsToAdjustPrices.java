package prep.amz.oa;

import java.util.Arrays;

/*
The manager of the Amazon warehouse has decided to make changes to the inventory by changing the prices of the products. Currently, the inventory has n products, where the price of the i-th product is represented by the array element prices[i].

The manager is given two integers:

k - which is the maximum amount by which a product's price can be adjusted (increased or decreased) in a single operation, and
d - which represents the target price difference.
The goal is to ensure that the difference between the highest and lowest prices in the inventory is strictly less than d.

In order to make changes to the inventory, the manager can do the following operation any number of times:

The manager selects two indices x, y (1 ≤ x, y ≤ n), and an integer p (1 ≤ p ≤ k).
The manager increases the price of the product x by p.
The manager decreases the price of the product y by p.
Given n products, an array prices, and the two integers k and d, find the minimum number of operations that the manager has to perform such that the maximum difference between the prices of any two products from the array of products is strictly less than d.

Function Description

Complete the function minOperations in the editor.

minOperations has the following parameters:

int prices[n]: an array of integers representing the prices of n products
int k: the maximum amount that a price can be increased or decreased by in one operation
int d: the target difference
Returns

int: the minimum number of operations required to ensure the condition is satisfied, i.e., the difference between the maximum and minimum prices of the array of products is strictly less than d.

Example 1:

Input: prices = [1, 5, 9, 11], k = 4, d = 2
Output: 3
 */
public class MinOpsToAdjustPrices {
    public static int minOperations(int[] prices, int k, int d) {
        /*
        Notes:
        You have an array of prices.You want: max - min < d (strictly less than d). In each operation we increase some min by p (≤ k), decrease some max by p (≤ k).
        1. Each operation can shrink the gap by up to 2 * k
                Why?
                - min increases by p → reduces gap by p
                - max decreases by p → reduces gap by another p
                - Total reduction per operation ≤ 2 * k
         2. How much total reduction is needed?
                 Initial gap: gap = max - min
                 Target: gap < d → we must reduce gap by at least:gap−(d−1) this becomes our "required reduction"
                 Why d-1? Because if gap is < d, it satisfies the strict inequality.
         3. Each operation reduces gap by at most 2*k,
            So the minimum number of operations = ceil(required reduction / 2 *k)
                                                = ceil(gap - (d - 1) / 2*k)
         */
        Arrays.sort(prices);
        int currMi = prices[0], currMa = prices[prices.length - 1];
        int currGap = currMa - currMi;
        int reductionNeeded = currGap - (d - 1);
        int maxReductionsPerOp = 2*k;
        int opsNeeded = (int) Math.ceil(reductionNeeded / maxReductionsPerOp);
        return opsNeeded;
    }
    public static void main(String[] args) {
        assert minOperations(new int[]{1,5,9,11}, 2, 2) == 3;
        assert minOperations(new int[]{4, 1, 6}, 1, 2) == 2;
    }
}
