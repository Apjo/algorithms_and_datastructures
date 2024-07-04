package problemsolving.leetcode.dailychallenge;
import java.util.*;
public class Solution_2024_06_29 {
        static class MyUnionFind {
            int[]parent;
            int numComponents;
            int[]size;
            public MyUnionFind(int n) {
                this.parent = new int[n];
                this.size = new int[n];
                for(int i=0; i < n; i++) {
                    parent[i]=i;
                    size[i]=1;
                }
                numComponents=n;
            }
            public boolean union(int a, int b) {
                int u = find(a);
                int v = find(b);
                if (u != v) {
                    if(size[u] > size[v]) {
                        parent[v] = u;
                        size[u]+=size[v];
                    } else {
                        parent[u] = v;
                        size[v]+=size[u];
                    }
                    numComponents--;
                    return true;
                } else {
                    return false;
                }
            }
            public int find(int u) {
                if(u != parent[u]) {
                    parent[u] = find(parent[u]);
                }
                return parent[u];
            }
            public boolean connected(int u, int v) {
                return find(u) == find(v);
            }
        }
        public int maxNumEdgesToRemove(int n, int[][] edges) {
            Arrays.sort(edges, (a, b) -> Integer.compare(b[0], a[0])); //type3 comes first
            MyUnionFind a = new MyUnionFind(n+1);
            MyUnionFind b = new MyUnionFind(n+1);
            int ans=0, allEdgesCount=0;
            for(int[] edge : edges) {
                switch(edge[0]) {
                    case 3:
                        boolean a1 = a.union(edge[1], edge[2]);
                        boolean b1 = b.union(edge[1], edge[2]);

                        if ( a1) allEdgesCount++;
                        if (b1) allEdgesCount++;

                        if(!a1 || !b1) ans++;
                        break;
                    case 2:
                        boolean b2 = b.union(edge[1], edge[2]);
                        if(b2) {
                            allEdgesCount++;
                        } else {
                            ans++;
                        }
                        break;
                    case 1:
                        boolean a2 = a.union(edge[1], edge[2]);
                        if(a2) {
                            allEdgesCount++;
                        } else {
                            ans++;
                        }
                        break;
                }
            }
            if(allEdgesCount != 2 * n - 2) return -1;
            return ans;
        }
}
