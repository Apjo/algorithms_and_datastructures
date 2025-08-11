package dsa.problemsolving.neetcode;

import java.util.Arrays;

public class Problem33 {
    /*
    You are given an integer array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit.
    Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
    Return the minimum number of boats to carry every given person.
    Input: people = [5,1,4,2], limit = 6
    [1,2,4,5]

Output: 2
Explanation:
First boat [5,1].
Second boat [4,2].

Example 2:

Input: people = [1,3,2,3,2], limit = 3

Output: 4
Explanation:
First boat [3].
Second boat [3].
Third boat [1,2].
Fourth boat [2].

Constraints:

1 <= people.length <= 50,000
1 <= people[i] <= limit <= 30,000
     */
    //date:08/01/2025
    //time: O(nlogn), space: O(1)
    public int numRescueBoats(int[] people, int limit) {
        //why sort?
        Arrays.sort(people);
        int lo=0, hi = people.length - 1, ctr=0;
        while(lo <= hi) {
            int ss = people[lo] + people[hi];
            if (ss <= limit) {
                ctr++;
                lo++;
                hi--;
            } else {
                ctr++;
                hi--;
            }
        }
        return ctr;
    }
    public static void main(String[] args) {
    }
}
