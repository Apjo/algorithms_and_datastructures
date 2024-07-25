package dsa.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
//This implementation of Prims uses a PriorityQueue, and solves the problem in O(ELogE) time
public class PrimsMST {
    private static class Edge implements Comparable<Edge> {
        int from, to, cost;
        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private List<List<Edge>> graph;
    private boolean[] visited;
    private PriorityQueue<Edge> pq;
    private int vertices;
    private Edge[] edgesInMst;
    private boolean foundMst;
    private long mstCost;
    private boolean alreadySolved;

    public Edge[] getEdgesInMst() {
        solve();
        return foundMst ? edgesInMst : null;
    }

    public Long getMstCost() {
        solve();
        return foundMst ? mstCost : null;
    }

    public PrimsMST(List<List<Edge>> g) {
        this.vertices = g.size();
        this.graph = g;
    }

    public static List<List<Edge>> createEmptyGraph(int V) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i ++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    public static void addUndirectedEdge(List<List<Edge>> g, int source, int destination, int wt) {
        g.get(source).add(new Edge(source, destination, wt));
        g.get(destination).add(new Edge(destination, source, wt));
    }

    static void printGraph(List<List<Edge>> am) {
        for (int i = 0; i < am.size(); i++) {
            System.out.println("\nVertex " + i + " has edges & their weights such as ");
            for (int j = 0; j < am.get(i).size(); j++) {
                int from = am.get(i).get(j).from;
                int to = am.get(i).get(j).to;
                int cost = am.get(i).get(j).cost;
                System.out.print(" " + from  + " -> " + to + " : " + cost);
            }
            System.out.println();
        }
    }

    private void addEdgesOfVertexToPQ(int vertexNum) {
        visited[vertexNum] = true;
        //add all the (not visited) vertices connected to vertex vertexNum in the PQ
        List<Edge> edges = graph.get(vertexNum);
        for (Edge e: edges) {
            if(!visited[e.to]) {
                pq.offer(e);
            }
        }
    }

     void solve() {
        if (alreadySolved) { return;}
        alreadySolved = true;
        visited = new boolean[vertices];
        pq = new PriorityQueue<>();

        int numEdgesInMst = 0;

        int totalEdgesInGraph = vertices - 1;
        //basically our MST tree, containing <= V - 1 edges
        edgesInMst = new Edge[totalEdgesInGraph];

        addEdgesOfVertexToPQ(0);

        while (!pq.isEmpty() && numEdgesInMst != totalEdgesInGraph) {
            Edge current = pq.poll();
            if (visited[current.to]) { continue;}
            int edgeCost = current.cost;
            int toVertex = current.to;
            //since vertex is not visited, save edge in MST
            edgesInMst[numEdgesInMst++] = current;
            mstCost +=edgeCost;
            addEdgesOfVertexToPQ(toVertex);
        }
        foundMst = (numEdgesInMst == totalEdgesInGraph);
    }

    public static void main(String[] args) {
        // Create the graph
        int V = 7;
        List<List<Edge>> g = createEmptyGraph(V);

        // Add edges
        addUndirectedEdge(g, 0, 1, 9);
        addUndirectedEdge(g, 0, 2, 0);
        addUndirectedEdge(g, 0, 3,5);
        addUndirectedEdge(g, 0, 5,7);
        addUndirectedEdge(g, 1, 3,-2);
        addUndirectedEdge(g, 1, 4, 3);
        addUndirectedEdge(g, 1, 6, 4);
        addUndirectedEdge(g, 2, 5, 6);
        addUndirectedEdge(g, 3, 5, 2);
        addUndirectedEdge(g, 3, 6, 3);
        addUndirectedEdge(g, 4, 6, 6);
        addUndirectedEdge(g, 5, 6, 1);

        printGraph(g);

        PrimsMST primsAlgo = new PrimsMST(g);

        primsAlgo.solve();

        Long cost = primsAlgo.getMstCost();

        if (cost == null) {
            System.out.println("MST does not exists");
        } else {
            System.out.println("MST cost: " + cost);
            for (Edge e : primsAlgo.getEdgesInMst()) {
                System.out.println(String.format("from: %d, to: %d, cost: %d", e.from, e.to, e.cost));
            }
        }

    }
}
