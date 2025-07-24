package dsa.datastructures.graphs.problems;
import java.util.*;

public class CountBasins {static int[] parent;
    private static void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if(parentX != parentY) {
            parent[parentX] = parentY;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static List<Integer> find_basins(List<List<Integer>> matrix) {
        int M = matrix.size();
        int N = matrix.get(0).size();
        parent = new int[M*N];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int coordNum = (i * N) + j;
                int sinkNum = solve(i,j,matrix,coordNum, M, N);
                union(coordNum, sinkNum);
            }
        }
        int []count = new int[M*N];
        for (int i = 0; i < M*N; i++) {
            count[find(i)]++;
        }

        for(int i = 0; i< M*N; i++) {
            if(count[i] !=0) {
                res.add(count[i]);
            }
        }
        Collections.sort(res);
        return res;
    }

    private static boolean isValid(int x, int y, int R, int C) {
        return (x >=0 && x < R && y >=0 && y < C);
    }

    private static int solve(int r, int c, List<List<Integer>> mat, int coord, int M, int N) {
        int[] xDir = new int[]{-1, 1, 0, 0};
        int[] yDir = new int[]{0, 0, -1, 1};
        int minV = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        int sink = coord;
        int currElem = mat.get(r).get(c);

        for (int i = 0; i < 4; i++) {
            int newX = r + xDir[i];
            int newY = c + yDir[i];
            if (isValid(newX, newY, mat.size(), mat.get(0).size())) {
                int neigh = mat.get(newX).get(newY);
                if (neigh < minV) {
                    minV = neigh;
                    minX = newX;
                    minY = newY;
                }
            }
        }
        if (minV < currElem) {
            sink = minX*N + minY;
        }
        return sink;
    }
}
