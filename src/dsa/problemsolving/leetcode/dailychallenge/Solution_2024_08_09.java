package dsa.problemsolving.leetcode.dailychallenge;

/*
An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These characters divide the square into contiguous regions.

Given the grid grid represented as a string array, return the number of regions.

Note that backslash characters are escaped, so a '\' is represented as '\\'.

 */


import java.util.*;

public class Solution_2024_08_09 {
    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        int actualSize = N*3;
        boolean[][]G = new boolean[actualSize][actualSize];
        //fill G
        for(int i=0; i < actualSize; i++) {
            Arrays.fill(G[i], true);
        }
        for(int i=0; i < N; i++) {
            for(int j=0; j < N; j++) {
                if(grid[i].charAt(j) == '/') {
                    G[3*i+2][3*j] = G[3*i+1][3*j+1] = G[3*i][3*j+2] = false;
                }
                if(grid[i].charAt(j) == '\\') {
                    G[3*i][3*j] = G[3*i+1][3*j+1] = G[3*i+2][3*j+2] = false;
                }
            }
        }
        //dfs
        int ans=0;
        for(int i=0; i < actualSize; i++) {
            for(int j=0; j < actualSize; j++) {
                if(G[i][j]) {
                    ans++;
                    dfs(G, i, j);
                }
            }
        }
        return ans;
    }
    private static void dfs(boolean[][]G, int r, int c) {
        if(r >=0 && r < G.length && c >= 0 && c < G[0].length && G[r][c]) {
            G[r][c]=false;
            dfs(G, r+1, c);
            dfs(G, r-1, c);
            dfs(G, r, c+1);
            dfs(G, r, c-1);
        }
    }
}
