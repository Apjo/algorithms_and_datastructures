package datastructures.graphs.problems;
import java.util.*;
//LC equivalent: #864
//TC: O(rows * columns * 2^keys) where keys=10 because there can be up to ten types of keys, ‘a’..’j’.
public class ShortestPathsWithKeysAndDoors {
    private static class Point {
        int x, y;
        int key;
        Point parent;
        public Point(int x, int y, int key, Point parent) {
            this.x = x;
            this.y = y;
            this.key = key;
            this.parent = parent;
        }
        Point getParent() {return this.parent;}
        int getX() {return this.x;}
        int getY() {return this.y;}
        int getKey() {return this.key;}

        void setKey(int k) {this.key = k;}

        private String marshal() {
            return this.getX() + "-" + this.getY() + "-" + this.getKey();
        }
    }
    static int VALID_MOVES = 4;
    static int[] addRows = {1,-1,0,0};
    static int[] addCols = {0,0,1,-1};
    public static int[][] find_shortest_path(String[] grid) {
        int R = grid.length;
        int C = grid[0].length();
        char[][] mat = prepMat(grid, R, C);
        System.out.print("2d Grid");
        //unique way to mark visited
        Set<String> visited = new HashSet<>();
        List<Point> paths = new ArrayList<>();
        System.out.println(Arrays.deepToString(mat).replace("], ", "]\n"));

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (mat[i][j] == '@') {
                    System.out.println("Starting BFS at row= " + i + " col= " + j);
                    bfs(mat, new Point(i, j, 0, null), visited, paths);
                }
            }
        }
        if (paths.size() == 0) {
            System.out.println("Got empty paths.");
            return new int[0][0];
        }
        Collections.reverse(paths);
        System.out.println("path size= " + paths.size());
        int[][]op = new int[paths.size()][];
        int idx = 0;
        for(Point p:paths) {
            int[]o = new int[]{p.getX(), p.getY()};
            op[idx]= o;
            idx++;
        }
        return op;
    }

    private static char[][] prepMat(String[] g, int R, int C) {
        char[][]mat = new char[R][C];
        for (int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                mat[i][j] = g[i].charAt(j);
            }
        }
        return mat;
    }

    private static void bfs(char[][] g, Point st, Set<String> visited, List<Point> paths) {
        System.out.println("Inside BFS...");
        Queue<Point> q = new LinkedList<>();
        q.add(st);
        visited.add(st.getX() + "-" + st.getY() + "-" + st.getKey());
        while(!q.isEmpty()) {
            Point curr = q.poll();
            int currX = curr.getX();
            int currY = curr.getY();

            int currKey = curr.getKey();
            char currChar = g[currX][currY];
            System.out.println("Currently at= " + currChar);
            //reached end
            if(currChar == '+') {
                System.out.println("REACHED GOAL");
                while(curr != null) {

                    paths.add(curr);
                    curr = curr.getParent();
                }
                break;
            }
            //List<Point> neighbors = getNeighbors(g.length, g[0].length, curr.getX(), curr.getY(), curr);
            //System.out.println("Total neighbors= " + neighbors.size());
            for(int m = 0; m < VALID_MOVES; m++) {
                int neighborX = currX + addRows[m];
                int neighborY = currY + addCols[m];
                if (!(neighborX >=0 && neighborY >= 0 && neighborX < g.length && neighborY < g[0].length)) {
                    continue;
                }
                char neighbor = g[neighborX][neighborY];
                System.out.println("Current neighbor= " + neighbor);
                if (neighbor == '#') {
                    continue;
                }

                if (neighbor >= 'A' && neighbor <= 'J') {
                    System.out.println("At a DOOR");
                    if(isDoorLocked(currKey, neighbor)) {
                        continue;
                    }
                }
                System.out.println("NEXT POINT");
                Point nextPoint = new Point(neighborX, neighborY, currKey, curr);
                if (neighbor >= 'a' && neighbor <= 'j') {
                    System.out.println("At a KEY");
                    nextPoint.setKey(useKey(nextPoint.getKey(), neighbor));
                }
                String keyed = nextPoint.marshal();
                if(!visited.contains(keyed)) {
                    visited.add(keyed);
                    q.add(nextPoint);
                }
            }
        }
    }//end bfs
    private static boolean isDoorLocked(int k, char c) {
        return (k & (1 << c - 'A')) == 0;
    }

    private static int useKey(int k, char c) {
        k |= (1 << c - 'a');
        return k;
    }

    public static void main(String[] args) {
        String[] g = new String[]{"...B",".b#.","@#+."};
        int[][] op = find_shortest_path(g);
        System.out.println(Arrays.deepToString(op).replace("], ", "]\n"));
    }
}






