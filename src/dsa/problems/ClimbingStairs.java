package dsa.problems;
//LC #70
//similar style will be used to tile an n-board with squares, dominoes, and triominoes
// where recurrence function will be f(n) = f(n-1) + f(n-2) + f(n-3)

public class ClimbingStairs {
    public static int climbStairs(int N) {
        int []m = new int[N+1];
        m[0] = 1;
        m[1] = 1;
        for(int i = 2; i <= N; i++) {
            m[i] = m[i - 2] + m[i - 1];
        }
        return m[N];
    }
    public static void main(String[] args) {
        System.out.println("Unique ways to climb N=3 stairs= " + climbStairs(3)); //3
        System.out.println("Unique ways to climb N=2 stairs= " + climbStairs(2)); //2
        System.out.println("Unique ways to climb N=1 stairs= " + climbStairs(1)); //1
    }
}