package datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFordAlgorithm {
    private static class Edge {
        int from, to, cost;
        public Edge(int from, int to, int wt) {
            this.from = from;
            this.to = to;
            this.cost = wt;
        }
    }

    public static void addEdge(List<List<Edge>> graph, int from, int to, int cost) {
        graph.get(from).add(new Edge(from, to, cost));
    }

    public static List<List<Edge>> createEmptyGraph(int V) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i ++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    public static int[] solve(int V, List<List<Edge>> graph, int start) {
        int[]distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        for (int v = 0; v < V - 1; v++) {
            for (List<Edge> edges: graph) {
                for (Edge edge: edges) {
                    int fromVertex = edge.from;
                    int toVertex = edge.to;
                    int cost = edge.cost;
                    int newDistance = fromVertex + cost;
                    if (distance[toVertex] > newDistance) {
                        distance[toVertex] = newDistance;
                    }
                }
            }
        }
        //run algo 2nd time to detect which nodes are part of negative cycle
        for (int v = 0; v < V - 1; v++) {
            for (List<Edge> edges: graph) {
                for (Edge edge: edges) {
                    int fromVertex = edge.from;
                    int toVertex = edge.to;
                    int cost = edge.cost;
                    int newDistance = fromVertex + cost;
                    if (distance[toVertex] > newDistance) {
                        distance[toVertex] = Integer.MIN_VALUE;
                    }
                }
            }
        }
        return distance;
    }
    public static void main(String[] args) {
        int V = 9, start = 0;

        List<List<Edge>> graph = createEmptyGraph(V);
        addEdge(graph, 0, 1, 1);
        addEdge(graph, 1, 2, 1);
        addEdge(graph, 2, 4, 1);
        addEdge(graph, 4, 3, -3);
        addEdge(graph, 3, 2, 1);
        addEdge(graph, 1, 5, 4);
        addEdge(graph, 1, 6, 4);
        addEdge(graph, 5, 6, 5);
        addEdge(graph, 6, 7, 4);
        addEdge(graph, 5, 7, 3);

        int []d = solve(V, graph, start);

        for (int i = 0; i < V; i++)
            System.out.printf("The cost to get from node %d to %d is %d\n", start, i, d[i]);

    }
}
