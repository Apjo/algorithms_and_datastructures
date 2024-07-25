package dsa.graphs;
//Finds shortest path from all pairs of nodes in the graph. Runs in O(V^3)
public class FloydWarshallAllPairsShortestPath {
    public void solve(int[][]input) {
        int R = input.length;
        int[][] dist = new int[R][R];
        int[][]path = new int[R][R];
        //init setup
        //initially the distance matrix will be equivalent to the input matrix
        for (int i = 0; i < R; i ++) {
            for (int j = 0; j < R; j ++) {
                dist[i][j] = input[i][j];
                if (i == j) {
                    path[i][j] = 0;
                } else if (input[i][j] != Integer.MAX_VALUE)
                    path[i][j] = j;
            }
        }
        //algo
        for (int k = 0; k < R; k ++) {
            for (int i = 0; i < R; i ++) {
                for (int j = 0; j < R; j ++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
        //identify edges contributing to negative cycles
        for (int k = 0; k < R; k ++) {
            for (int i = 0; i < R; i ++) {
                for (int j = 0; j < R; j ++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = Integer.MAX_VALUE;
                        path[i][j] = -1;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {

    }
}
