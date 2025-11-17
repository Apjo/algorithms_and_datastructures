package prep.amz.oa;

import java.util.Arrays;
import java.util.Comparator;

/*
You are on a flight and want watch two movies during this flight.
You are given List<Integer> movieDurations which includes all the movie durations.
You are also given the duration of the flight which is d in minutes.
Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min).

Find the pair of movies with the longest total duration and return they indexes. If multiple found, return the pair with the longest movie.

Example 1:

Input: movieDurations = [90, 85, 75, 60, 120, 150, 125], d = 250
Output: [0, 6]
Explanation: movieDurations[0] + movieDurations[6] = 90 + 125 = 215 is the maximum number within 220 (250min - 30min)
There could be duplicate times
We must find a pair whose duration that equals to EXACTLY d - 30
There could be more than 1 pair. If there is more than one pair, you choose the pair that's going to have a larger number in it. (eg. [30, 30] vs. [10, 50], we would choose [10, 50].
Return a pair of indices.

 */
public class MoviesOnFlight {
    public static int[] solve(int[]durations, int d) {
        d = d - 30;
        int N = durations.length;
        int[][]B = new int[N][2];
        for(int i=0; i < N; i++) {
            B[i][0] = durations[i];
            B[i][1] = i;
        }
        Arrays.sort(B, Comparator.comparingInt(a -> a[0]));
        int le=0, ri = N - 1;
        int globalSum = 0;
        int[]ans = new int[2];
        while(le < ri) {
            int localSum = B[le][0] + B[ri][0];
            if (localSum <= d) {
                if (localSum > globalSum) {
                    globalSum = localSum;
                    ans[0] = B[le][1];
                    ans[1] = B[ri][1];
                }
                le++;
            } else {
                ri--;
            }
        }
        Arrays.sort(ans);
        return ans;
    }
    public static void main(String[] args) {
        assert solve(new int[]{90, 85, 75, 60, 120, 150, 125}, 225).equals(new int[]{0, 6});
    }
}
