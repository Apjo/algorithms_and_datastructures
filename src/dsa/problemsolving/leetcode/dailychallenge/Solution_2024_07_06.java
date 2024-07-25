package dsa.problemsolving.leetcode.dailychallenge;

public class Solution_2024_07_06 {
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;

        while(numBottles >= numExchange) {
            int rm = numBottles % numExchange;
            numBottles /= numExchange;
            ans += numBottles;
            numBottles += rm;
        }
        return ans;
        //return numBottles + (numBottles - 1 / numExchange - 1)
    }
}
