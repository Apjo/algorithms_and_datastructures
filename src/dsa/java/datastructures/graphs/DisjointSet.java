package dsa.java.datastructures.graphs;
//Disjoint set(union-find) datastructure, Wiki: https://en.wikipedia.org/wiki/Disjoint-set_data_structure
//Disjoint-set data structures support three operations:
// 1. Making a new set containing a new element,
// 2. finding the representative of the set containing a given element,
// 3. and merging two sets.
//This data structure is used in Kruskal's algo to find the minimum spanning tree
public class DisjointSet {
    int[] parents; //parent of node x
    int[] sizes; // to keep a track of no.of subtrees for a given node x
    public DisjointSet(int N) {
        this.parents = new int[N];
        this.sizes = new int[N];
        for(int i = 0; i < N; i++) {
            parents[i] = i;
            sizes[i] = 1;//each node is in itself a subtree
        }
    }

    public boolean areConnected(int node1, int node2) {
        return findByPathCompression(node1) == findByPathCompression(node2);
    }

    public int size(int node) {
        return sizes[findByPathCompression(node)];
    }

    public void weightedUnion(int node1, int node2) {
        int root1 = findByPathCompression(node1);
        int root2 = findByPathCompression(node2);
        if (root1 == root2) { return; }
        if (sizes[root1] < sizes[root2]) {
            parents[root1] = root2;
            sizes[root2] += sizes[root1];
        } else {
            parents[root2] = root1;
            sizes[root1] += sizes[root2];
        }
    }

    public int findByPathCompression(int node) {
        int curr = node;
        while (curr != parents[curr]) {
            curr = parents[curr];
        }
        while (curr != node) {
            int next = parents[node];
            parents[node] = curr;
            node = next;
        }
        return curr;
    }
}

