package dsa.datastructures.graphs;

import java.util.*;
//FLI0604141901989

//Finds shortest path from all pairs of nodes in the graph. Runs in O(V^3)
public class FloydWarshallAllPairsShortestPath {
    final static int MAX_DIST = Integer.MAX_VALUE;
    public static void solve(int[][]input) {
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
                } else if (input[i][j] != MAX_DIST)
                    path[i][j] = j;
            }
        }
        System.out.println("Finished setting up dist, path matrices");
        System.out.println("dist array=" + java.util.Arrays.deepToString(dist));
        System.out.println("path array=" + java.util.Arrays.deepToString(path));
        //algo
        // Add all vertices one by one to the set of intermediate vertices.
        for (int k = 0; k < R; k ++) {
            // Pick all vertices as source one by one
            for (int i = 0; i < R; i ++) {
                // Pick all vertices as destination for the above picked source
                for (int j = 0; j < R; j ++) {
                    System.out.println("At i="+i+" j="+j+" k="+k);
                    if (dist[i][k] != MAX_DIST && dist[k][j] != MAX_DIST) {
                        System.out.println("At dist i-k="+dist[i][k]+" dist k-j="+dist[k][j]+" dist i-j="+dist[i][j]);
                        //shortest path from i to j
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            System.out.println("Updated dist from i="+i+" to j=" +j + " via k="+k+" to be=" + dist[i][j]);
                            path[i][j] = path[k][j];
                            System.out.println("Updated path from i="+i+" to j=" +j + " via k="+k+" to be=" + path[i][j]);
                        }
                    }
                }
            }
        }
        System.out.println("Identifying negative cycles");
        //identify edges contributing to negative cycles
        for (int k = 0; k < R; k ++) {
            for (int i = 0; i < R; i ++) {
                for (int j = 0; j < R; j ++) {
                    //if (dist[i][k] > 0 && dist[k][j] > 0 && dist[i][j] > 0) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = MAX_DIST;
                            path[i][j] = -1;
                        }
                    //}
                }
            }
        }
        System.out.println("dist array=" + java.util.Arrays.deepToString(dist));
        System.out.println("path array=" + java.util.Arrays.deepToString(path));
    }
    private static int[][] prepInput(int[] e) {
        int V = e.length;
        int[][]input = new int[V][V];
        for(int i=0; i < V; i++) {
            for(int j=0; j < V; j++) {
                if (i == j) {
                    input[i][j] = 0;
                } else {
                    input[i][j] = j == e[i] ? 1 : MAX_DIST;
                }
            }
        }
        //System.out.println("Created input=" + java.util.Arrays.deepToString(input));
        return input;
    }
    public static int solveForGivenNodes(int[][]input, int node1, int node2) {
        int R = input.length;
        int[][] dist = new int[R][R];
        //init setup
        //initially the distance matrix will be equivalent to the input matrix
        for (int i = 0; i < R; i ++) {
            for (int j = 0; j < R; j ++) {
                dist[i][j] = input[i][j];
            }
        }
        //System.out.println("Finished setting up dist matrix");
        //System.out.println("dist=" + java.util.Arrays.deepToString(dist));
        //algo
        // Add all vertices one by one to the set of intermediate vertices.
        for (int k = 0; k < R; k ++) {
            // Pick node1 vertex as source
            for (int i = 0; i < R; i ++) {
                // Pick all vertices as destination for the above picked source
                for (int j = 0; j < R; j++) {
                    //System.out.println("Starting from node=" + i + " to node=" + j + " via node=" + k);
                    if (dist[i][k] != MAX_DIST && dist[k][j] != MAX_DIST) {
                        //shortest path from node1 to j
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            //System.out.println("Updated dist from node=" + i + " to node=" + j + " via node=" + k + " to be=" + dist[i][j]);
                        }
                    }
                }
            }
        }
        System.out.println("Generated dist matrix=" + java.util.Arrays.deepToString(dist));
        int[]rowForNode1 = dist[node1];
        System.out.println("node1 array=" + java.util.Arrays.toString(rowForNode1));
        int[]rowForNode2 = dist[node2];
        System.out.println("node2 array=" + java.util.Arrays.toString(rowForNode2));

        int currMi = MAX_DIST, track=-1;
        for(int i=0; i < rowForNode1.length; i++){
            if (rowForNode1[i] != MAX_DIST && rowForNode2[i] != MAX_DIST) {
                if (currMi > Math.max(rowForNode1[i], rowForNode2[i])) {
                currMi = Math.max(rowForNode1[i], rowForNode2[i]);
                track = i;
                }
            }
        }

        return track;
    }
    public static void main(String[] args) {
        int[][]input1 = {
                {0, MAX_DIST, 1, MAX_DIST},
                {MAX_DIST, 0, 1, MAX_DIST},
                {MAX_DIST, MAX_DIST, 0, 1},
                {MAX_DIST, MAX_DIST, MAX_DIST, 0}
        };
        //FloydWarshallAllPairsShortestPath.solve(input1);

        int[][]input2 = {
                {0,        1,        MAX_DIST},
                {MAX_DIST, 0,        1},
                {MAX_DIST, MAX_DIST, 0}
        };
        //FloydWarshallAllPairsShortestPath.solveForGivenNodes(input1, 0, 1);
        int[]ip1 = {4,4,8,-1,9,8,4,4,1,1};
        int node11=5, node21=6;
        int[][]data1 = prepInput(ip1);
        System.out.println("nodes " +node11 + ", and node "+ node21 +" will meet at index=" + FloydWarshallAllPairsShortestPath.solveForGivenNodes(data1, node11, node21));
        int[]ip2 = {1,2,0,4,5,3};//op:0
        int node12=0, node22=1;
        int[][]data2 = prepInput(ip2);
        System.out.println("nodes " +node12 + ", and node "+ node22 +" will meet at index=" + FloydWarshallAllPairsShortestPath.solveForGivenNodes(data2, node12, node22));
        int[]ip3 = {2,2,3,-1};//op:2
        int node13=0, node23=1;
        int[][]data3 = prepInput(ip3);
        System.out.println("nodes " +node13 + ", and node "+ node23 +" will meet at index=" + FloydWarshallAllPairsShortestPath.solveForGivenNodes(data3, node13, node23));
        int[]ip4={5,3,1,0,2,4,5};
        int node14=0, node24=2;
        int[][]data4 = prepInput(ip4);
        System.out.println("nodes " +node14 + ", and node " +node24 +" will meet at index=" + FloydWarshallAllPairsShortestPath.solveForGivenNodes(data4, node14, node24));





    }
}
