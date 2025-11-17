package prep.meta;

public class DiagTraverse1 {
    //using the trick of determining on which diagonal you are on. if sum of indices is EVEN=> move top right, else move bottom left
    public int[] findDiagonalOrder(int[][] mat) {
        int M = mat.length, N = mat[0].length;
        int total = M*N, row=0, col=0;
        int[]ans = new int[total];
        for(int i =0; i < total; i++) {
            ans[i] = mat[row][col];
            if ((row + col) % 2 == 0) {
                //if at last col, goto next row
                if(col == N - 1) {
                    row++;
                }
                //if at 0th row, goto next col
                else if(row == 0) {
                    col++;
                } else {
                    //go top right i.e. row -- , col++
                    row++;
                    col--;
                }
            } else {
                //if at last row, goto next col
                if (row == M - 1) {
                    col++;
                } else if(col == 0) {
                    //if 0th col, goto next row
                    row++;
                } else {
                    //else go bottom left i.e. row++, col--
                    row++;col--;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
    }
}
