package dsa.java.datastructures.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//finds minimum spanning tree using Kruskal's algo using Disjoint set. O(ElogE)
public class KruskalMST {
    private static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.weight = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public Long solve(List<Edge> graph, int N) {
        if (graph == null) { return null;}
        long sum = 0L;
        //sort edges by weight in ascending order
        Collections.sort(graph);
        //create a disjoint set for all vertices
        DisjointSet ds = new DisjointSet(N);
        //for all edges, starting with the smallest weight edge, if both vertices are in the same connected component, skip
        //else, union the vertices, and add the cost to the sum. Once the size of component == N, we found the MST
        for (Edge e: graph) {
            if (ds.areConnected(e.from, e.to)) {
                continue;
            }
            ds.weightedUnion(e.from, e.to);
            sum+=e.weight;
            if (ds.size(0) == N) { break; }
        }
        if(ds.size(0) != N) { return null; }
        return sum;
    }
    public static void main(String[] args) {
        KruskalMST kruskal = new KruskalMST();
        int numNodes = 10;
        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 5));
        edges.add(new Edge(1, 2, 4));
        edges.add(new Edge(2, 9, 2));
        edges.add(new Edge(0, 4, 1));
        edges.add(new Edge(0, 3, 4));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 7, 4));
        edges.add(new Edge(2, 8, 1));
        edges.add(new Edge(9, 8, 0));
        edges.add(new Edge(4, 5, 1));
        edges.add(new Edge(5, 6, 7));
        edges.add(new Edge(6, 8, 4));
        edges.add(new Edge(4, 3, 2));
        edges.add(new Edge(5, 3, 5));
        edges.add(new Edge(3, 6, 11));
        edges.add(new Edge(6, 7, 1));
        edges.add(new Edge(3, 7, 2));
        edges.add(new Edge(7, 8, 6));

        Long cost = kruskal.solve(edges, numNodes);
        if (cost == null) {
            System.out.println("No kruskal mst");
        } else {
            System.out.println("Kruskal mst cost: " + cost);
        }
    }
}
