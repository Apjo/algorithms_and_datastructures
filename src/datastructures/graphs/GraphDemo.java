package datastructures.graphs;
import java.util.*;

public class GraphDemo {
    /**
     * Graph representations:

     1. Adjacency Matrix:
     Pros:
     - simplest to represent
     - efficient to represent a dense graph
     - querying for an edge wt takes O(1)
     Cons:
     - Requires O(V^2) space
     - Iterating over all edges takes O(V^2) time

     2. Adjacency List: A map of nodes to a list of edges
     Pros:
     - space efficient for sparse graphs
     - iterating over all edges is efficient
     Cons:
     - less space efficient for denser graphs
     - edge weight lookup is O(E)
     - slightly more complex to represent as compared to Adj. matrix
     3. Edge List: An ordered list of edges. Think of a triplet (u, v, w) means cost of going from
     node u to node v is w
     Pros:
     - space efficient for sparse graphs
     - iterating over all edges is efficient
     - simple structure
     Cons:
     - less space efficient for denser graphs
     - edge weight lookup is O(E)
     **/
    static class Edge {
        int from, to, weight;
        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.weight = w;
        }
    }

    int vertices;
    boolean isDirected;
    private Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
    private int[][]adjMat;

    public GraphDemo(int totalVertices, boolean isDirected) {
        this.isDirected = isDirected;
        this.vertices = totalVertices;
        this.adjMat = new int[totalVertices][totalVertices];
        for (int i = 0 ; i < totalVertices; i++) {
            this.adjacencyList.put(i, new LinkedList<>());
            this.adjMat[i][i] = 0;
        }

    }
    public List<List<Integer>> getEdges () {
        List<List<Integer>> ll = new ArrayList<>();
        for (int i = 0; i < this.getVertices(); i++) {
            List<Integer> edges = this.getAdjacencyList().get(i);
            ll.add(edges);
        }
        return ll;
    }

    public int getVertices() {
        return vertices;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public Map<Integer, List<Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    public int[][] getAdjMat() {
        return adjMat;
    }

    public void addEdge(int source, int destination) {
        //add node v to u's adjacency list
        adjacencyList.get(source).add(destination);
        adjMat[source][destination] = 1;
        //if graph is undirected graph, add u to v's adjacency list
        if (!isDirected) {
            adjacencyList.get(destination).add(source);
            adjMat[destination][source] = 1;
        }
    }

    int[] findConnectedComponents(int V) {
        int[] components = new int[V];
        boolean[] visited = new boolean[V];
        int cnt = 0;
        for (int i = 0; i < V; i++) {
            cnt++;
            dfsCompo(i, visited, cnt, components);
        }
        return components;
    }
    private void dfsCompo(int src, boolean[] visited, int cnt, int[] compos) {
        visited[src] = true;
        compos[src] = cnt;
        for(int neighbor: this.adjacencyList.get(src)) {
            if (!visited[neighbor]) {
                dfsCompo(neighbor, visited, cnt, compos);
            }
        }
    }

    public void bfs(int source) {
        boolean[] visited = new boolean[this.adjacencyList.size()];
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        visited[0] = true;
        System.out.println("Visited " + source);
        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.println("Visiting " + v);
            for (int neighbor : this.adjacencyList.get(v)) {
                if (!visited[neighbor]) {
                    System.out.println("Visited neighbor " + neighbor);
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }

    //O(V+E)
    void dfsIter(int source) {
        Stack<Integer> s = new Stack<>();
        boolean[] visited = new boolean[this.adjacencyList.size()];
        s.add(source);
        visited[source] = true;
        while (!s.isEmpty()) {
            int v = s.pop();
            for (int neighbor : this.adjacencyList.get(v)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    s.add(neighbor);
                }
            }
        }
    }

    // O(V+E)
    void dfs(int V) {
        boolean[] visited = new boolean[V];
        for(int source = 0 ; source < V; source ++) {
            if (!visited[source]) {
                System.out.print("Started DFS on = " + source);
                dfsRecur(source, visited);
            }
        }
    }

    void dfsRecur(int source, boolean[] visited) {
        visited[source] = true;
        System.out.print("Visited = " + source);
        for (int neighbor : this.adjacencyList.get(source)) {
            if (!visited[neighbor]) {
                System.out.print("Visiting = " + neighbor);
                dfsRecur(neighbor, visited);
            }
        }
    }

    public void printGraph() {
        System.out.println("Here is our graph using Adjacency List representation");
        int src = 0;
        while (src < adjacencyList.size()) {
            System.out.print("Vertex " + src + " is connected to ");
            for (int v : adjacencyList.get(src)) {
                System.out.print(v + " ");
            }
            System.out.println();
            src++;
        }
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

    }
}
