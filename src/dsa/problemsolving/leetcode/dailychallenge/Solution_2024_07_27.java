package dsa.problemsolving.leetcode.dailychallenge;

/*
A city is represented as a bi-directional connected graph with n vertices where each vertex is labeled from 1 to n (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself. The time taken to traverse any edge is time minutes.

Each vertex has a traffic signal which changes its color from green to red and vice versa every change minutes. All signals change at the same time. You can enter a vertex at any time, but can leave a vertex only when the signal is green. You cannot wait at a vertex if the signal is green.

The second minimum value is defined as the smallest value strictly larger than the minimum value.

For example the second minimum value of [2, 3, 4] is 3, and the second minimum value of [2, 2, 4] is 4.
Given n, edges, time, and change, return the second minimum time it will take to go from vertex 1 to vertex n.

Notes:

You can go through any vertex any number of times, including 1 and n.
You can assume that when the journey starts, all signals have just turned green.
 */

import java.util.*;

public class Solution_2024_07_27 {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> g = new HashMap();
        for(int[] e : edges) {
            int u  = e[0], v = e[1];
            g.computeIfAbsent(u, x -> new ArrayList()).add(v);
            g.computeIfAbsent(v, x -> new ArrayList()).add(u);
        }
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        q.offer(new int[]{1, 0});
        int[] uniqueVisit = new int[n+1]; // uniqueVisit limit to 2 <==> relax twice at most
        int[] dis = new int[n+1];
        Arrays.fill(dis, -1);
        while(!q.isEmpty()) {
            int size = q.size();
            int[] cur = q.poll();
            int node = cur[0], t = cur[1];  // arriving time
            if(dis[node] == t || uniqueVisit[node] >= 2) continue; // skip if it's same value or has been relaxed twice already
            uniqueVisit[node]++;
            dis[node] = t;
            if(node == n && uniqueVisit[node] == 2) return dis[node];
            // generate leaving time (waiting the green light)
            if((t / change) % 2 != 0) t = (t/change + 1) * change;
            for(int nei : g.getOrDefault(node, new ArrayList<>())) {
                q.offer(new int[]{nei, t + time});
            }
        }
        return -1;
    }

}
