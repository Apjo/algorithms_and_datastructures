package problemsolving.leetcode.dailychallenge;

/*
You are given a positive integer k. You are also given:

a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
The two arrays contain integers from 1 to k.

You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once. The remaining cells should have the value 0.

The matrix should also satisfy the following conditions:

The number abovei should appear in a row that is strictly above the row at which the number belowi appears for all i from 0 to n - 1.
The number lefti should appear in a column that is strictly left of the column at which the number righti appears for all i from 0 to m - 1.
Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.

 */


import java.util.*;

public class Solution_2024_07_20 {
    private static List<Integer> topo(int[][] G, int k) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            adjList.add(new ArrayList<>());
        }

        // Convert edge list to adjacency list
        for (int[] edge : G) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
        }

        Stack<Integer> st = new Stack<>();
        Set<Integer> v = new HashSet<>();

        for(int i=1; i <= k; i++) {
            if(!v.contains(i)) {
                if (!dfs(st, adjList, i, v, new HashSet<>())) {
                    //cycle detected, return
                    return new ArrayList<>();
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        while(!st.isEmpty()) {
            res.add(st.pop());
        }
        return res;
    }
    private static boolean dfs(Stack<Integer> st, List<List<Integer>> G, int from, Set<Integer> v, Set<Integer> visiting) {
        if (visiting.contains(from)) {
            return false; // Cycle detected
        }

        if (v.contains(from)) {
            return true; // Already visited node
        }

        visiting.add(from);
        for (int neighbor : G.get(from)) {
            if (!dfs(st, G, neighbor, v, visiting)) {
                return false; // Cycle detected in DFS
            }
        }
        visiting.remove(from);
        v.add(from);
        st.push(from);

        return true;
    }
    private static void printL(List<Integer> l) {
        for(Integer i : l) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {

        List<Integer> f1 = topo(rowConditions, k);
        printL(f1);
        List<Integer> f2 = topo(colConditions, k);
        printL(f2);

        if(f1.size() != k || f2.size() != k) {
            return new int[0][0];
        }

        return build(k, f1, f2);
        //return new int[][]{{-1,-1}};
    }
    private static int[][] build(int k, List<Integer> l1, List<Integer> l2) {
        int[][]op = new int[k][k];
        for(int i=1; i <=k; i++) {
            op[l1.indexOf(i)][l2.indexOf(i)] = i;
        }
        return op;
    }
}
