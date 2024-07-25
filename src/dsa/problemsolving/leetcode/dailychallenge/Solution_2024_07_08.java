package dsa.problemsolving.leetcode.dailychallenge;

import java.util.*;
/*
There is a restaurant with a single chef. You are given an array customers, where customers[i] = [arrivali, timei]:

arrivali is the arrival time of the ith customer. The arrival times are sorted in non-decreasing order.
timei is the time needed to prepare the order of the ith customer.
When a customer arrives, he gives the chef his order, and the chef starts preparing it once he is idle. The customer waits till the chef finishes preparing his order. The chef does not prepare food for more than one customer at a time. The chef prepares food for customers in the order they were given in the input.

Return the average waiting time of all customers. Solutions within 10-5 from the actual answer are considered accepted.



Example 1:

Input: customers = [[1,2],[2,5],[4,3]]
Output: 5.00000
 */
public class Solution_2024_07_08 {
    public double averageWaitingTime(int[][] customers) {
        List<Integer> numbers = new ArrayList<>();
        int chefStTime = customers[0][0];
        int chefFinishTime = chefStTime + customers[0][1];
        numbers.add(chefFinishTime - chefStTime);

        for(int i=1; i < customers.length; i++) {
            int arrival = customers[i][0];
            int time = customers[i][1];
            if (chefFinishTime < arrival) {
                chefStTime = arrival;
            } else {
                chefStTime = chefFinishTime;
            }
            chefFinishTime = chefStTime + time;
            numbers.add(chefFinishTime - arrival);
        }
        OptionalDouble averageOpt =  numbers.stream()
                .mapToInt(Integer::intValue)
                .average();

        return averageOpt.orElseThrow(() -> new IllegalArgumentException("List must not be empty"));
    }
}
