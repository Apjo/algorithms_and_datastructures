package prep.amz.oa;

import java.util.*;

/*
Problem: Rate-Limiting Algorithm

Some developers at Amazon are building a prototype for a simple rate-limiting algorithm. There are n requests to be processed by the server, represented by a string requests, where the i-th character represents the region of the i-th client. Each request takes 1 unit of time to process.

There must be a minimum time gap of minGap units between any two requests from the same region.

The requests can be sent in any order, and there can be gaps in transmission for testing purposes.
Find the minimum amount of time required to process all the requests such that no request is denied.
Example 1:
Suppose n = 6, requests = "aaabbb", and minGap = 2:

requests: a a a b b b
           | |   | |
         ab _ ab _ ab (minimum time gap of 2 between same region requests)
The requests can be sent in the order ab_ab_ab where _ represents that no request was sent in that unit time. Here, the minimum time gap between two requests from the same region is minGap = 2.

The total time taken is 8 units.

Example 2:
Input:

12
abacadaeafag
2
Explanation:

n = 12
requests = "abacadaeafag"
minGap = 2
Sample Output:

16
Explanation:
One optimal strategy is: "ab_ad_afgae_ac_a"

Function Description:
Complete the function getMinTime in the editor below:

def getMinTime(n: int, requests: str, minGap: int) -> int:
Parameters:

n: the number of requests
requests: a string representing the region of each request
minGap: the minimum time gap required between requests from the same region
Returns:

The function should return an integer representing the minimum time required to process all requests without denial.
 */
public class RateLimiting {
    public static int solve(String requests, int minGap) {
        int time=0;
        char[]arr = requests.toCharArray();
        Map<Character, Integer> freq = new HashMap<>();
        for(char cc : arr) {
            freq.put(cc, freq.getOrDefault(cc, 0) + 1);
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,  b) -> Integer.compare(b, a));
        for(Map.Entry<Character, Integer> entry : freq.entrySet()) {
            maxHeap.offer(entry.getValue());
        }
        //this queue will hold those tasks/requests to be added back to the maxHeap for processing at time t
        //represented as [frequency_count, idleTime]
        List<int[]> queue = new LinkedList<>();
        while(!maxHeap.isEmpty() || !queue.isEmpty()) {
            //time increments as we start processing each request
            time++;
            if (!maxHeap.isEmpty()) {
                int count = maxHeap.poll();
                //decrement count, as we process one request at a time
                count--;
                //if count is > 0, then there are still these many requests to be handled
                if (count > 0) {
                    //to handle them at a time=time+minGap add them to a Queue
                    queue.add(new int[]{count, time + minGap});
                }
            }
            //if the queue isn't empty, and if the request's idle time has reached the current time
            if (!queue.isEmpty() && queue.get(0)[1] == time) {
                maxHeap.offer(queue.remove(0)[0]);
            }
        }
        return time;
    }
    public static void main(String[] args) {
        assert solve("abacadaeafag", 2) == 16;
    }
}
