package dsa.java.datastructures.graphs;

import java.util.List;
import java.util.PriorityQueue;
//naive implementation using adjacency matrix and priority queue. Time: O(V^2)
public class PrimsMSTAdjMat {

    private static class Edge implements Comparable<Edge> {
        int from, to, weight;
        public Edge(int f, int t, int w) {
            this.from = f;
            this.to = t;
            this.weight = w;
        }
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
    private void addEdgesOfVertexToPQ(int vertexNum, boolean[]visited, Integer[][]graph, PriorityQueue<Edge> pq) {
        visited[vertexNum] = true;
        //add all the (not visited) vertices connected to vertex vertexNum in the PQ
        for (int v = 0; v < graph.length; v++) {
            if(!visited[v] && graph[vertexNum][v] != null) {
                pq.offer(new Edge(vertexNum, v, graph[vertexNum][v]));
            }
        }
    }

    //approach 1: Using a Priority Queue
    public Long solve(Integer[][]graph, int V) {
        long mstCost = 0;
        boolean[]visited = new boolean[V];
        visited[0] = true;
        int edgeCount = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        //add all the edges connected to vertex 0, if the weight between the edges is > 0
        for (int v = 1; v < V; v++) {
            if (graph[0][v] != null) {
                pq.add(new Edge(0, v, graph[0][v]));
            }
        }
        while (edgeCount < V - 1 && !pq.isEmpty()) {
            Edge curr = pq.poll();
            int toVertex = curr.to;
            int edgeCost = curr.weight;
            if (!visited[toVertex]) {
                visited[toVertex] = true;
            }
            edgeCount++;
            mstCost+=edgeCost;
            //adjust distances of all the vertices from chosen vertex those are not in the mst
            addEdgesOfVertexToPQ(toVertex, visited, graph, pq);
        }
        //total number of edges in the MST atleast have to be == total no. of vertices - 1
        if (edgeCount != V - 1) { return null; }
        return mstCost;
    }

    //Approach2: without using any min priority queue, a solution of O(V^2)
    public static Long solveNoPQ(Integer[][] g) {
        int V = g.length;
        int INF = 9999999;
        boolean[] visited = new boolean[V];
        int edgesInMst = 0;
        visited[0] = true;
        long sum = 0;
        while (edgesInMst < V - 1) {
            int min = INF;
            int fromVertex = 0;
            int toVertex = 0;
            for (int row = 0; row < V; row++) {
                if (visited[row]) {
                    for (int col = 0; col < V; col++) {
                        if(!visited[col] && g[row][col] != null) {
                            if (min > g[row][col]) {
                                min = g[row][col];
                                fromVertex = row;
                                toVertex = col;
                            }
                        }
                    }
                }
            }
            visited[toVertex] = true;
            sum+=g[fromVertex][toVertex];
            edgesInMst++;
        }
        return sum;
    }

    public static void main(String[] args) {
        // Create the graph
        int V = 7;
        Integer[][] g = new Integer[V][V];
        // Add edges
        g[0][1] = g[1][0] = 9;
        g[0][2] = g[2][0] = 0;
        g[0][3] = g[3][0] = 5;
        g[0][5] = g[5][0] = 7;
        g[1][3] = g[3][1] = -2;
        g[1][4] = g[4][1] = 3;
        g[1][6] = g[6][1] = 4;
        g[2][5] = g[5][2] = 6;
        g[3][5] = g[5][3] = 2;
        g[3][6] = g[6][3] = 3;
        g[4][6] = g[6][4] = 6;
        g[5][6] = g[6][5] = 1;

        PrimsMSTAdjMat primsAlgo = new PrimsMSTAdjMat();

        Long cost = primsAlgo.solve(g, V);
        Long cost2 = PrimsMSTAdjMat.solveNoPQ(g);

        if (cost == null) {
            System.out.println("MST does not exists");
        } else {
            System.out.println("MST cost: " + cost);
        }
        if (cost2 == null) {
            System.out.println("MST does not exists");
        } else {
            System.out.println("MST cost with no PQ: " + cost2);
        }
    }
}
