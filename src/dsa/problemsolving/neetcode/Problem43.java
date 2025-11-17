package dsa.problemsolving.neetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class Problem43 {
    /*
    You are given an array of integers nums and an integer k.
    There is a sliding window of size k that starts at the left edge of the array.
    The window slides one position to the right until it reaches the right edge of the array.
Return a list that contains the maximum element in the window at each step.

Example 1:

Input: nums = [1,2,1,0,4,2,6], k = 3
Output: [2,2,4,4,6]

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
     */
    //date: 08/10/2025
    //time: O(n), space:O(n)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int L = nums.length;
        int[]res = new int[L - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        //to set the current max for first window
        //for i in 0 to k
            //keep on adding to the TAIL end of the queue as long as nums[i] is greater than the last element added
            //else remove from the TAIL end of the queue if nums[i] >= queue's last element
            //set the 0th element of the res to be the queue's first element from the head
        for(int i=0; i < k; i++) {
            while(!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        int idx=0;
        //what if dq is empty here?
        res[idx] = nums[dq.peekFirst()];
        idx++;
        /*similarly, now from i in k to nums.len:
         1. remove the index of the largest element in the queue from the HEAD of the queue if its location is outside the current sliding window i - k
         2. keep on removing all indexes from the back of the q where the element at those indexes are < nums[i]
         3. add the ith or the "right" element to the back of the queue
         4. set the element in res at location i-k+1 to be the largest element at front of the q
        */
        for(int i=k; i < L; i++) {
            if (!dq.isEmpty() && i-k == dq.peekFirst()) {
                dq.pollFirst();
            }
            while(!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            res[idx++] = nums[dq.peekFirst()];
        }
        return res;
    }
    //using a maxheap, time: O(n*logn)
    public int[] maxSlidingWindowUsingHeap(int[] nums, int k) {
        int L = nums.length, ri=0;
        int[]res = new int[L - k + 1];
        //maxh is used to store indices
        PriorityQueue<Integer> maxH = new PriorityQueue<>((a,  b) -> Integer.compare(nums[b], nums[a]));
        while(ri < L) {
            int currWindowLen = ri - k + 1;
            while(!maxH.isEmpty() && currWindowLen > maxH.peek()) {
                maxH.poll();
            }
            maxH.offer(ri);
            if (currWindowLen >=0) {
                res[ri - k + 1] = nums[maxH.peek()];
            }
            ri++;
        }
        return res;
    }
    public static void main(String[] args) {
    }
}
