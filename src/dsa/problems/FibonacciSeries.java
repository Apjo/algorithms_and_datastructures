package dsa.problems;

public class FibonacciSeries {
    //time: O(N), space:O(N), implicit call stack space: O(N),
    // this is recursion without repetition aka memoization
    public static int fib(int N) {
        if(N <= 1) {return N;}
        int[]memo = new int[N + 1];
        memo[0] = 0;
        memo[1] = 1;

        memo[N] = fib(N - 2) + fib(N - 1);

        return memo[N];
    }

    public static int fibWithDp(int N) {
        if (N <=1) {return N;}
        int[]memo = new int[N + 1];
        memo[0] = 0;
        memo[1] = 1;
        if(memo[N] != 0) {
            return memo[N];
        }
        for(int i = 2; i <= N; i++) {
            memo[i] = memo[i - 2] + memo[i - 1];
        }
        return memo[N];
    }

    public static int fibWithDpOptimizedSpace(int N) {
        if (N <=1) {return N;}
        int[]memo = new int[3];
        memo[0] = 0;
        memo[1] = 1;

        for(int i = 2; i <= N; i++) {
            //at any given time we only need 3 spaces, The 0th space will be filled with a multiple of 3
            //the 1st loc. by multiple of 3 + 1, and 2nd loc. by multiple of 3 + 2
            memo[i%3] = memo[(i - 2)%3] + memo[(i - 1)%3];
        }
        return memo[N%3];
    }


    public static void main(String[] args) {
        System.out.println("Nth fib with Recursion with memo= " + fib(10));
        System.out.println("Nth Fib with bottom-up DP= " + fibWithDp(10));
        System.out.println("Nth Fib with bottom-up DP space optimized= " + fibWithDpOptimizedSpace(10));
    }
}
