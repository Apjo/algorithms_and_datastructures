package problemsolving.leetcode.dailychallenge;
import java.util.*;
public class Solution_2024_07_07 {
    //time: O(N*K), space: O(N)
    public int findTheWinner(int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        int i=1;
        while(i <= n) {
            q.offer(i);
            i++;
        }

        while(q.size() != 1) {
            int j=0;
            while(j < k - 1) {
                //int temp = q.peek();
                q.offer(q.poll());
                //q.poll();
                j++;
            }
            q.poll();
        }
        return q.peek();
    }
}
