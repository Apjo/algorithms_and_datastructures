package dsa.java.datastructures.graphs;

import java.util.*;

public class TopologicalSortKahns {
    //O(V+E)
    public static int[] kahns(GraphDemo g) {
        int V = g.getVertices();
        int[]inDegrees = new int[V];
        int[] ordering = new int[V];
        int idx = 0;
        for (List<Integer> edges : g.getEdges()) {
            for (int to: edges) {
                inDegrees[to]++;
            }
        }
        //BFS approach is used
        Queue<Integer> q= new LinkedList<>();
        for (int v = 0; v < V; v++) {
            if (inDegrees[v] == 0) {
                q.add(v);
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            ordering[idx++] = u;
            for(int v : g.getAdjacencyList().get(u)) {
               inDegrees[v]--;
               if (inDegrees[v] == 0) {
                   q.add(v);
               }
            }
        }
        return ordering;
    }

    public static void main(String[] args) {
        int vertices = 14;
        GraphDemo g = new GraphDemo(vertices, true);
        g.addEdge( 0, 2);
        g.addEdge( 0, 3);
        g.addEdge( 0, 6);
        g.addEdge( 1, 4);
        g.addEdge( 2, 6);
        g.addEdge( 3, 1);
        g.addEdge( 3, 4);
        g.addEdge( 4, 5);
        g.addEdge( 4, 8);
        g.addEdge( 6, 7);
        g.addEdge( 6, 11);
        g.addEdge( 7, 4);
        g.addEdge( 7, 12);
        g.addEdge( 9, 2);
        g.addEdge( 9, 10);
        g.addEdge( 10, 6);
        g.addEdge( 11, 12);
        g.addEdge( 12, 8);

        g.printGraph();
        //should print [0, 9, 13, 3, 2, 10, 1, 6, 7, 11, 4, 12, 5, 8]
        System.out.println("TOPOLOGICAL SORT USING KAHNS Algo: " + Arrays.toString(kahns(g)));
    }
}
