package algorithms.dynamicprogramming.problems;
//LC#1137
//f(0) = 0, f(1) = 1, f(2)= 1, f(n+3) = f(n) + f(n+1) + f(n+2)
public class TribonacciSeries {
    public static int tribocacci(int N) {
        if (N <=1) {return N;}
        int[]memo = new int[4];
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 1;
        memo[3] = 2;
        for(int i = 4; i <= N; i++) {
            memo[i%4] = memo[(i - 3)%4] + memo[(i - 2)%4] + memo[(i - 1)%4];
        }
        return memo[N%4];
    }
    public static void main(String[] args) {
        System.out.println("Nth tribonacci no.= " + tribocacci(10));
    }
}
