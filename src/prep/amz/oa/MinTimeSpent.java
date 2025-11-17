package prep.amz.oa;
/*
Amazon Prime Video has movies in category 'comedy' or 'drama'. Determine the earliest time you can finish at least one movie from each category. The release schedule and duration of the movies are provided.


You can start watching a movie at the time it is release or later.
If you begin a movie at time t, it ends at t + duration.
If a movie ends at time t + duration , the second movie can start at that time, t+ duration, or later.
The movies can be watched in any order.
Complete the function minimumTimeSpent which has the following parameters:


int comedyReleaseTime[n]: release times
int comedyDuration[n]: durations
int dramaReleaseTime[m]: release times
int dramaDuration[m]: durations
Example 1:

Input: comedyReleaseTime = [1, 4], comedyDuration = [3, 2], dramaReleaseTime = [5, 2], dramaDuration = [2, 2]
Output: 6
Explanation:

Duration and release time are aligned by index.

Two of the best ways to finish watching one movie from each category at the earliest time are as follows:

- Start watching comedy movie1 at time t = 1 and until t = 1 + 3 = 4. Then, watch the drama movie1 from time t = 4 to t = 4 + 2 = 6.

- Start watching a comedy movie2 at time t = 2 and until t = 2 + 2 = 4. Then, watch the drama movie1 from time t = 4 to t = 4 + 2 = 6.

The earliest finish time and also answer is 6.

Examples that are sub-optimal include:

- Start watching a comedy movie2 at time t = 4 and until t = 4 + 2 = 6. Then, watch the drama movie1 from time t = 6 to t = 6 + 2 = 8.

- Start watching a comedy movie1 at time t = 1 and until t = 1 + 3 = 4. Then, watch the drama movie1 from time t = 5 to t = 5 + 2 = 7.
 */
public class MinTimeSpent {

    public static int minimumTimeSpent(int[] comedyReleaseTime, int[] comedyDuration,
                                int[] dramaReleaseTime, int[] dramaDuration) {
        int earliestTime = Integer.MAX_VALUE;
        for(int i=0; i < comedyReleaseTime.length; i++) {
            for(int j=0; j < dramaReleaseTime.length; j++) {
                int comedyStartTime = comedyReleaseTime[i];
                int comedyEndTime = comedyStartTime + comedyDuration[i];
                int dramaStartTime = Math.max(dramaReleaseTime[j], comedyEndTime);
                int totalTimeSpent = dramaStartTime + dramaDuration[j];
                earliestTime = Math.min(earliestTime, totalTimeSpent);
            }
        }
        return earliestTime;
    }
    public static void main(String[] args) {
        assert minimumTimeSpent(new int[]{1,4}, new int[]{3,2}, new int[]{5,2}, new int[]{2,2}) == 6;
    }
}
