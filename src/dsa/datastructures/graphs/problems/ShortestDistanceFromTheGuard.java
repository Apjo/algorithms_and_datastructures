package dsa.datastructures.graphs.problems;
import java.util.*;
//problem uses multisource BFS to bring time complexity to O(M+N), else BFS can be used on all vertices to arrive at the answer
public class ShortestDistanceFromTheGuard {
    static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    private static boolean isInRange(int x, int y, int R, int C) {
        return (x >= 0 && x < R && y >= 0 && y < C);
    }
    //can only move iff next position is an 'O' or whose distance is yet to be calculated i.e. -1
    private static boolean canMove(int x, int y, List<List<Character>> g, List<List<Integer>> distances) {
        return (g.get(x).get(y) == 'O' && distances.get(x).get(y) == -1);
    }

    public static List<List<Integer>> find_shortest_distance_from_a_guard(List<List<Character>> grid) {
        List<List<Integer>> distances = new ArrayList<>();

        int M = grid.size();
        int N = grid.get(0).size();
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Queue<Point> q = new LinkedList<>();

        //add all Guards to the queue, with initial distance to be 0, else initial distance to be -1 for all vertices
        for (int i = 0; i < M; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (grid.get(i).get(j) == 'G') {
                    Point p = new Point(i, j, 0);
                    q.add(p);
                    row.add(0);
                } else {
                    row.add(-1);
                }
            }
            distances.add(row);
        }

        while (!q.isEmpty()) {
            Point curr = q.poll();
            int currX = curr.x;
            int currY = curr.y;
            int currDist = curr.dist;

            for (int[] dir : directions) {
                int newX = currX + dir[0];
                int newY = currY + dir[1];
                int newDist = currDist + 1;
                if (isInRange(newX, newY, M, N) && canMove(newX, newY, grid, distances)) {
                    distances.get(newX).set(newY, newDist);
                    q.add(new Point(newX, newY, newDist));
                }
            }
        }
        return distances;
    }
}
