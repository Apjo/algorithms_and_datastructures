package problemsolving.leetcode.dailychallenge;
import java.io.*;
import java.util.*;

public class Solution_2024_06_28 {
        public List<List<Integer>> getAncestors(int n, int[][] edges) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer>[] adj = new List[n];
            //prep graph
            for(int i=0; i < n; i++) {
                adj[i] = new ArrayList<>();
                ans.add(new ArrayList<>());
            }
            for(int i=0; i < edges.length; i++) {
                adj[edges[i][0]].add(edges[i][1]);
            }
            //do dfs
            Set<Integer> v = new HashSet<>();

            for(int i=0; i < n; i++) {
                solve(i, i, adj, ans, v);
            }
            return ans;
        }
        private static void solve(int from, int curr, List<Integer>[]G, List<List<Integer>>l, Set<Integer> v) {
            v.add(curr);
            for(int ne : G[from]) {
                if(l.get(ne).size() == 0 ||
                l.get(ne).get(l.get(ne).size() - 1) != from) {
                    l.get(ne).add(from);
                    solve(from, ne, G, l, v);
                }
            }
        }
}
