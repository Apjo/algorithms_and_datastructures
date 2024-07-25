package dsa.graphs.problems;
import java.util.*;

public class LongestPathInWeightedDAG {
    private static class Edge {
        int  to;
        long wt;
        public Edge(int t, long w) {

            this.to = t;
            this.wt = w;
        }
    }
    private static  Map<Integer, List<Edge>> adjList;
    static boolean[]visited;

    private static void topoSort(int from, List<Integer> st) {
        visited[from] = true;
        for (Edge e: adjList.get(from)) {
            if(!visited[e.to]) {
                topoSort(e.to, st);
            }
        }
        st.add(from);
    }

    // private static void solve(List<Integer> o, long[]distance, int[]parent, int to_node) {

    // }

    static int[] find_longest_path(int dag_nodes, int[] dag_from, int[] dag_to, int[] dag_weight, int from_node, int to_node) {
        adjList = new HashMap<>();

        //buildGraph(dag_nodes, dag_from, dag_to, dag_weight);
        for(int i = 1; i <= dag_nodes; i++) {
            adjList.put(i, new ArrayList<Edge>());
        }
        for (int i = 0; i < dag_from.length; i++) {
            adjList.get(dag_from[i]).add(new Edge(dag_to[i], dag_weight[i]));
        }

        visited = new boolean[dag_nodes + 1];

        List<Integer> st = new ArrayList<Integer>();
        topoSort(from_node, st);
        if(st.size() == 0) { return new int[0]; }
        Collections.reverse(st);

        long[]distance = new long[dag_nodes + 1];
        Arrays.fill(distance, 0);
        distance[from_node] = 0;

        int[]parent = new int[dag_nodes + 1];
        Arrays.fill(parent, -1);
        parent[from_node] = -1;

        LinkedList<Integer> res = new LinkedList<>();
        for (Integer i:st) {
            if (i == to_node) {
                break;
            }
            List<Edge> es = adjList.get(i);
            for(Edge e:es) {
                int to = e.to;
                long wt = e.wt;
                long newDist = distance[i] + wt;
                if (distance[to] < newDist) {
                    distance[to] = newDist;
                    parent[to] = i;
                }
            }
        }
        int p = to_node;
        while(p != -1) {
            res.addFirst(p);
            p = parent[p];
        }

        int[]r = new int[res.size()];
        int idxx = 0;
        for(Integer a:res) {
            r[idxx++] = a;
        }
        return r;
    }

    public static void main(String[] args) {

    }
}
