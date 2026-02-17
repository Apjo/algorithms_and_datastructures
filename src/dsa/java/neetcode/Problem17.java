package dsa.java.neetcode;
/*
You are given a 9 x 9 Sudoku board. A Sudoku board is valid if the following rules are followed:

Each row must contain the digits 1-9 without duplicates.
Each column must contain the digits 1-9 without duplicates.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.
Return true if the Sudoku board is valid, otherwise return false

Note: A board does not need to be full or be solvable to be valid.
 */
//date:07/23/2025
public class Problem17 {
    public boolean isValidSudoku(char[][] board) {
        boolean[][]seenR = new boolean[9][9];
        boolean[][]seenC = new boolean[9][9];
        boolean[][]seenB = new boolean[9][9];
        for(int i=0; i < 9; i++) {
            for(int j=0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '1';
                if(seenR[i][num]) {
                    return false;
                }
                seenR[i][num]=true;
                if(seenC[j][num]) {
                    return false;
                }
                seenC[j][num] = true;
                int boxIndex = ((i / 3) * 3) + j / 3;
                if(seenB[boxIndex][num]) {
                    return false;
                }
                seenB[boxIndex][num]=true;
            }
        }
        return true;
    }
    public static void main(String[] args) {
    }
}
