package dsa.java.datastructures.graphs;

import java.util.*;
//heavily adapted from https://github.com/williamfiset/Algorithms/blob/master/src/main/java/com/williamfiset/algorithms/graphtheory/DijkstrasShortestPathAdjacencyList.java

public class DijkstraAlgorithm {
    private static class Edge {
        int from, to, weight;
        public Edge(int from ,int to, int wt) {
            this.from = from;
            this.to = to;
            this.weight = wt;
        }
    }
    private static class Node {
        int vertex, cost;
        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
    //private static final double EPS = 1e-6;
    private Comparator<Node> comparator = new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            if (Math.abs(node1.cost - node2.cost) == 0) {
                return 0;
            }
            return (node1.cost - node2.cost) > 0 ? +1 : -1;
        }
    };

    public void addEdge(int from, int to, int cost) {
        graph.get(from).add(new Edge(from, to, cost));
    }

    public static List<List<Edge>> createEmptyGraph(int V) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i ++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    private int N;
    private int[] distance;
    private Integer[] parent;
    private List<List<Edge>> graph;

    public int solve(int source, int end) {
        //init a visited arr
        boolean[]visited = new boolean[N];
        //init a distance arr
        distance = new int[N];
        //set distance of all vertices to be POSITIVE INFINITY
        Arrays.fill(distance, Integer.MAX_VALUE);
        //set distance of source vertex to be 0
        distance[source] = 0;
        //set parent of source vertex to be -1/null?
        parent[source] = -1;
        //Init a min priority Q with our defined comparator
        PriorityQueue<Node> pq = new PriorityQueue<>(2 * N, comparator);
        //add source vertex to the PQ
        pq.add(new Node(source, 0));
        //mark source vertex as visited
        visited[source] = true;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            int vertex = current.vertex;
            int cost = current.cost;
            visited[vertex] = true;
            //if we have already seen this vertex with a min distance, continue by skipping this vertex
            if(distance[vertex] < cost) { continue; }
            List<Edge> neighboringEdges = graph.get(vertex);
            for (int v = 0; v < neighboringEdges.size(); v++) {
                Edge neighboringEdge = neighboringEdges.get(v);

                int fromVertex = neighboringEdge.from;
                int toVertex = neighboringEdge.to;
                //cannot get a better distance by revisiting the same vertex
                if (visited[toVertex]) { continue;}
                int edgeCost = neighboringEdge.weight;
                int newDistance = distance[fromVertex] + edgeCost;
                //relax edge weights
                if (newDistance < distance[toVertex]) {
                    distance[toVertex] = newDistance;
                    parent[toVertex] = fromVertex;
                    pq.offer(new Node(toVertex, newDistance));
                }
            }
            if (vertex == end) {return distance[end]; }
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {

    }
}
