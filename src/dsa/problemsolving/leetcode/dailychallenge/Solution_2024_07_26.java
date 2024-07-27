package dsa.problemsolving.leetcode.dailychallenge;

/*
You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English letters. You are also given two 0-indexed character arrays original and changed, and an integer array cost, where cost[i] represents the cost of changing the character original[i] to the character changed[i].

You start with the string source. In one operation, you can pick a character x from the string and change it to the character y at a cost of z if there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y.

Return the minimum cost to convert the string source to the string target using any number of operations. If it is impossible to convert source to target, return -1.

Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].

Example 1:

Input: source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
Output: 28
Explanation: To convert the string "abcd" to string "acbe":
- Change value at index 1 from 'b' to 'c' at a cost of 5.
- Change value at index 2 from 'c' to 'e' at a cost of 1.
- Change value at index 2 from 'e' to 'b' at a cost of 2.
- Change value at index 3 from 'd' to 'e' at a cost of 20.
The total cost incurred is 5 + 1 + 2 + 20 = 28.
It can be shown that this is the minimum possible cost.
Example 2:

Input: source = "aaaa", target = "bbbb", original = ["a","c"], changed = ["c","b"], cost = [1,2]
Output: 12
Explanation: To change the character 'a' to 'b' change the character 'a' to 'c' at a cost of 1, followed by changing the character 'c' to 'b' at a cost of 2, for a total cost of 1 + 2 = 3. To change all occurrences of 'a' to 'b', a total cost of 3 * 4 = 12 is incurred.
Example 3:

Input: source = "abcd", target = "abce", original = ["a"], changed = ["e"], cost = [10000]
Output: -1
Explanation: It is impossible to convert source to target because the value at index 3 cannot be changed from 'd' to 'e'.

 */

import java.util.*;

public class Solution_2024_07_26 {
    private static Map<String, Integer> memo = new HashMap<>();

    static class Edge {
        char st, end;
        int wt;
        public Edge(char s, char e, int w) {
            this.st = s;
            this.end=e;
            this.wt=w;
        }
        @Override
        public String toString() {
            return "(" + st + "->" + end + ", " + wt + ")";
        }
    }
    static class Graph {
        private Map<Character, List<Edge>> G;
        public Graph() {
            this.G = new HashMap<>();
        }
        public void addVertex(char c) {
            G.putIfAbsent(c, new ArrayList<>());
        }
        public List<Edge> getNeighbors(char c) {
            return G.get(c);
        }
        public void addEdge(char st, char e, int w) {
            G.get(st).add(new Edge(st, e, w));
        }
        public Map<Character, List<Edge>> getGraph() {
            return this.G;
        }
    }

    private static int getSSP(Graph g, char src, char dest) {
        String key = "" + src + "-" + dest;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        PriorityQueue<Edge> p = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.wt, e2.wt));
        Map<Character, Integer> dist = new HashMap<>();
        Set<Character> visited = new HashSet<>();
        for(Character s : g.getGraph().keySet()) {
            dist.put(s, Integer.MAX_VALUE);
        }
        dist.put(src, 0);
        //for(Edge e:g.getNeighbors(src)) {
        p.offer(new Edge(src, src, 0));
        //}
        while(!p.isEmpty()) {
            Edge currEdge = p.poll();
            char u = currEdge.end;
            int currDist = currEdge.wt;
            if (currDist == Integer.MAX_VALUE) {
                continue;
            }
            if (u == dest) {
                int v = dist.get(u) ;
                // System.out.println("FOUND! sssp between src="+src +" dest="+dest+" to be=" +v);
                memo.put(key, dist.get(u));
                return v;
            }
            if (visited.contains(u)) {
                continue;
            }

            visited.add(u);
            List<Edge> neighbors = g.getNeighbors(u);
            if (neighbors == null) {
                continue; // Skip if the current node has no neighbors
            }

            for(Edge neighbor : neighbors) {
                char neighborDest = neighbor.end;
                int newDist = currDist + neighbor.wt;
                // if(!visited.contains(neighborDest)) {
                if (newDist < dist.get(neighborDest)) {
                    dist.put(neighborDest, newDist);
                    p.offer(new Edge(u, neighborDest, newDist));
                }
                //}
            }

        }
        memo.put(key, -1);
        return -1;
    }
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        memo.clear();
        //build graph
        Graph g = new Graph();
        //add vertices
        for(int i=0; i < original.length; i++) {
            g.addVertex(original[i]);
            g.addVertex(changed[i]);
        }
        //add edges
        for(int i=0; i < original.length; i++) {
            char s = original[i];
            char e = changed[i];
            g.addEdge(s, e, cost[i]);
        }
        Map<Character, List<Edge>> m = g.getGraph();
        long ans = 0;

        for(int i=0; i < source.length(); i++) {
            char src = source.charAt(i);
            char dest = target.charAt(i);
            // System.out.println("Getting sssp between src="+src +" dest="+dest);
            if (src != dest) {
                int c = getSSP(g, source.charAt(i), target.charAt(i));
                if (c < 0) {
                    return -1;
                }
                ans += c;
                //  System.out.println("current ans=" + ans);
            }
        }
        //for each src <-> target combo perform dijsktra and get min distance
        //determine the answer, if the distance collection a non-integer value, that means we cannot find the min cost, return -1
        return ans;
    }

}
