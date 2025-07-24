package dsa.datastructures.graphs.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KnightsTourOnChess {
    private static class Point {
        int x,y,wt;
        public Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.wt = w;
        }
        public int getX() { return this.x;}
        public int getY() { return this.y;}
        public int getWt() { return this.wt;}
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if  (obj.getClass() != this.getClass()) {
                return false;
            }
            Point pos = (Point) obj;
            return pos.x == this.x && pos.y == this.y;
        }
    }

    private static List<Point> neighbors(int x, int y, int c, int R, int C) {
        List<Point> ll = new ArrayList<>();
        if (x + 2 < R && y + 1 < C) {
            ll.add(new Point(x + 2, y + 1, c + 1));
        }
        if (x + 2 < R && y - 1 >=0) {
            ll.add(new Point(x + 2, y - 1, c + 1));
        }
        if (x - 2 >=0 && y + 1 < C) {
            ll.add(new Point(x - 2, y + 1, c + 1));
        }
        if (x - 2 >=0 && y - 1 >= 0) {
            ll.add(new Point(x - 2, y - 1, c + 1));
        }
        if (x + 1 < R && y + 2 < C) {
            ll.add(new Point(x + 1, y + 2, c + 1));
        }
        if (x + 1 < R && y - 2 >=0) {
            ll.add(new Point(x + 1, y - 2, c + 1));
        }
        if (x - 1 >=0 && y + 2 < C) {
            ll.add(new Point(x - 1, y + 2, c + 1));
        }
        if (x - 1 >=0 && y - 2 >= 0) {
            ll.add(new Point(x - 1, y - 2, c + 1));
        }

        return ll;
    }

    static void bfs(Point a, int endR, int endC, int R, int C, List<Integer> wts) {
        Queue<Point> q = new LinkedList<>();
        q.add(a);
        boolean[][]visited = new boolean[R][C];
        visited[a.getX()][a.getY()] = true;
        int mi = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Point current = q.poll();
            if(current.getX() == endR && current.getY() == endC) {
                if (current.getWt() < mi) {
                    mi = current.getWt();
                    wts.add(mi);
                }
                break;
            }
            List<Point> neighs = neighbors(current.getX(), current.getY(), current.getWt(), R, C);
            for(Point neighbor: neighs) {
                if (!visited[neighbor.getX()][neighbor.getY()]) {
                    visited[neighbor.getX()][neighbor.getY()] = true;
                    q.add(neighbor);
                }
            }
        }
    }

    static int find_minimum_number_of_moves(int rows, int cols, int start_row, int start_col, int end_row, int end_col) {

        List<Integer> cost = new ArrayList<>();
        Point knightStart = new Point(start_row, start_col, 0);
        if (knightStart.getX() == end_row && knightStart.getY() == end_col) {
            return 0;
        }
        bfs(knightStart, end_row, end_col, rows, cols, cost);
        if (cost.size() > 0) {
            if (cost.get(0) != Integer.MAX_VALUE) {
                return cost.get(0);
            } else {return -1;}
        } else {return -1;}
    }
    public static void main(String[] args) {
        //should produce -1 as output
        System.out.println(find_minimum_number_of_moves(2,7,0,5,1,1));
    }
}
