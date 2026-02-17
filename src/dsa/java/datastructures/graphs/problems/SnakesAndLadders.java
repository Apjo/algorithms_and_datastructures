package dsa.java.datastructures.graphs.problems;
import java.util.*;
public class SnakesAndLadders {
    static int minNumberOfRolls(int n, List<Integer> moves) {
        boolean[] visited = new boolean[n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int currLoc = curr[0];
            if(visited[currLoc]) { continue; }
            int currWt = curr[1];

            if(currLoc == n - 1) { return currWt; }
            visited[currLoc] = true;

            for(int i = 1; i <= 6; i++) {
                int nextLoc = currLoc + i;
                if(nextLoc < n) {
                    int val = moves.get(nextLoc);
                    if(val != -1) {
                        nextLoc = val;
                    }
                    q.add(new int[]{nextLoc, currWt + 1});
                }
            }
        }
        return -1;
    }
}
