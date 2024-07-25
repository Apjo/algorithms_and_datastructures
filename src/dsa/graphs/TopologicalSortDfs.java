package dsa.graphs;

import java.util.*;

public class TopologicalSortDfs {
    //time and space: O(N)
    public static int[] topologicalSort(GraphDemo g) {
        Stack<Integer> st = new Stack<>();
        int V = g.getVertices();
        boolean[] visited = new boolean[V];
        int []op = new int[V];
        List<List<Integer>> ancestors = new ArrayList<>();
        Map<Integer, List<Integer>> parents = new HashMap<>();
        parents.put(0, new ArrayList<>());
        for (int v = 0; v < g.getVertices(); v++) {
            if(!visited[v]) {
                System.out.println("Visiting " + v);
                dfs(v, visited, st, g);
            }
        }
        printMap(parents);
        //printNestedList(ancestors);
        int idx = 0;
        while(!st.isEmpty()) {
            op[idx++] = st.pop();
        }
        return op;
    }
    public static void printNestedList(List<List<Integer>> nestedList) {
        for (List<Integer> innerList : nestedList) {
            for (Integer num : innerList) {
                System.out.print(num + " ");
            }
            System.out.println();  // Print a newline after each inner list
        }
    }
    public static void printMap(Map<Integer, List<Integer>> map) {
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Integer key = entry.getKey();
            List<Integer> value = entry.getValue();

            System.out.print("Key: " + key + " -> Values: ");
            for (Integer num : value) {
                System.out.print(num + " ");
            }
            System.out.println();  // Print a newline after each entry
        }
    }

    private static void dfs(int source, boolean[] visited, Stack<Integer> st, GraphDemo g) {
        visited[source] = true;
        System.out.println("Visited " + source);
        for (int v : g.getAdjacencyList().get(source)) {
            System.out.println("Neighbor of node " + source +" is " + v);
            if (!visited[v]) {
                System.out.println("Visiting node " + v);
                dfs(v, visited, st, g);
            }
        }
        st.push(source);
    }
    public static void main(String[] args) {
        int vertices = 8;
        GraphDemo g = new GraphDemo(vertices, true);
        g.addEdge(0,3);
        g.addEdge(0,4);
        g.addEdge(1,3);
        g.addEdge(2,4);
        g.addEdge(2,7);
        g.addEdge(3,5);
        g.addEdge(3,6);
        g.addEdge(3,7);
        g.addEdge(4,6);
        //g.printGraph();
        //prints 0,1,2,3,4
        System.out.println("TOPOLOGICAL SORT USING DFS: " + Arrays.toString(topologicalSort(g)));
    }
}
