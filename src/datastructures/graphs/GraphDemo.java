package datastructures.graphs;
import java.util.*;

public class GraphDemo {
    public Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

    public void addEdge(int source, int destination, boolean bidirectional) {

        if (!adjacencyList.containsKey(source))
            addVertex(source);

        if (!adjacencyList.containsKey(destination))
            addVertex(destination);

        adjacencyList.get(source).add(destination);
        if (bidirectional) {
            adjacencyList.get(destination).add(source);
        }
    }

    public void addVertex(int s) {
        adjacencyList.put(s, new LinkedList<Integer>());
    }

    public void bfsRecur(int source) {

    }

    public void bfsIter(int source) {
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
        visited[0] = true;
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
    void dfs(int source) {
        boolean[] visited = new boolean[this.adjacencyList.size()];
        if (!visited[source]) {
            dfsRecur(source, visited);
        }
    }

    void dfsRecur(int source, boolean[] visited) {
        visited[source] = true;
        for (int neighbor : this.adjacencyList.get(source)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                dfsRecur(neighbor, visited);
            }
        }
    }

    public void printGraph() {
        System.out.println("Here is our graph!");
        int src = 0;
        while (src < adjacencyList.size()) {
            for (int v : adjacencyList.get(src)) {
                System.out.println("Edge from= " + src + " to= " + v);
            }
            System.out.println();
            src++;
        }
    }
}
