package dsa.problemsolving.recursion;

import java.util.*;

class NQueens {
    // uses backtracking naive approach where it tries to place queen on every
    // row/col. Giving time complexity of almost O(N^N)
    public static String[][] nq(int N) {
        List<String[]> buff = new ArrayList<String[]>();
        char[][] board = new char[N][N];
        for (char[] row : board) {
            Arrays.fill(row, '-');
        }
        helper(N, 0, board, buff);
        return buff.toArray(new String[0][0]);
    }

    static void printL(List<List<String>> foo) {
        for (List<String> l1 : foo) {
            for (String n : l1) {
                System.out.print(n);
            }
            System.out.println();
        }
    }

    private static void helper(int N, int curRow, char[][] board, List<String[]> soFar) {
        if (curRow == N) {
            String[] row = new String[board.length];
            int i = 0;
            for (char[] r : board) {
                row[i++] = String.valueOf(r);
            }
            soFar.add(row);
        } else {
            // for all the choices we have
            for (int col = 0; col < N; col++) {
                // try one choice
                if (isSafe(curRow, col, board)) {
                    // make this choice
                    board[curRow][col] = 'Q';
                    // solve from here
                    helper(N, curRow + 1, board, soFar);
                    // unmake the choice
                    board[curRow][col] = '-';
                }
            }
        }
    }

    static boolean isSafe(int row, int col, char[][] board) {
        int cr = row;
        int cc = col;

        // check for left-up diagonal
        while (cr >= 0 && cc >= 0) {
            if (board[cr--][cc--] == 'Q') {
                return false;
            }
        }
        cr = row;
        cc = col;
        // check for right-up
        while (cr >= 0 && cc < board.length) {
            if (board[cr--][cc++] == 'Q') {
                return false;
            }
        }
        // check for column
        cr = row;
        while (cr >= 0) {
            if (board[cr--][col] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 4;
        System.out.println(Arrays.deepToString(nq(N)));
    }
}