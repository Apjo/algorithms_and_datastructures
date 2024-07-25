package dsa.problems;
//LC#790, MEDIUM
//time: O(n), space(3*n) = O(n)
public class DominoTrominoTiling {
    public int solve(int N) {
        if (N <=1) {return N;}
        int[]f = new int[N+1]; //table will be used to address normal conditions when we do not have an extra cell. i.e.
        //we have filled in all but n-1 cells, we have 4 options to fill in the last block. either by using 2 dominoes, or 2 triominoes
        //but, we enter a weird case with triominoes(or a rectangle of size 2 * N-1) since it leaves us with an extra cell either at the bottom (|_ ) or at the top(|-)
        int[]l = new int[N+1]; //will be used when we have an extra cell at bottom (|_)
        int[]u = new int[N+1]; //will be used when we have an extra cell at top (|-)
        f[1] = 1;//a single dominoe
        f[2] = 2;

        l[1] = 1;//a single trominoe (|_)
        l[2] = 2;

        u[1] = 1;//a single trominoe (|-)
        u[2] = 2;
        /**
         * recurrence relation we end up with are as follows:
         * f(n) = f(n-1)+f(n-2)+L(n-2)+U(n-2)
         * L(n) = f(n-1) + U(n-1), tile a 2*N rect, with an additional square at bottom
         * U(n) = f(n-1) + L(n-1), tile a 2*N rect, with an additional square at top
         */
        for(int i = 3; i <= N; i++) {
            f[i] = f[i - 2] + f[i - 1] + l[i-2] + u[i-2];
            l[i] = f[i-1] + u[i-1];
            u[i] = f[i-1] + l[i-1];
        }
        return f[N];
    }
}
