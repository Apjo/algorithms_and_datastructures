package datastructures.graphs;

import java.util.Arrays;
import java.util.Stack;

public class TopologicalSortDfs {
    //time and space: O(N)
    public static int[] topologicalSort(GraphDemo g) {
        Stack<Integer> st = new Stack<>();
        int V = g.getVertices();
        boolean[] visited = new boolean[V];
        int []op = new int[V];
        for (int v = 0; v < g.getVertices(); v++) {
            if(!visited[v]) {
                dfs(v, visited, st, g);
            }
        }
        int idx = 0;
        while(!st.isEmpty()) {
            op[idx++] = st.pop();
        }
        return op;
    }
    private static void dfs(int source, boolean[] visited, Stack<Integer> st, GraphDemo g) {
        visited[source] = true;
        for (int v : g.getAdjacencyList().get(source)) {
            if (!visited[v]) {
                dfs(v, visited, st, g);
            }
        }
        st.push(source);
    }
    public static void main(String[] args) {
        int vertices = 5;
        GraphDemo g = new GraphDemo(vertices, true);
        g.addEdge(0,1);
        g.addEdge(0,4);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.printGraph();
        //prints 0,1,2,3,4
        System.out.println("TOPOLOGICAL SORT USING DFS: " + Arrays.toString(topologicalSort(g)));
    }
}
