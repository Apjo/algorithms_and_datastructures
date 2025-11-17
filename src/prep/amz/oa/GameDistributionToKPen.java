package prep.amz.oa;

import java.util.Arrays;

/*
A logistics coordinator must distribute n computer games among k children using k identical pen drives (one per child).

Each game has a size gameSize[i].

Each child must receive at least 1 game and at most 2 games.

Every game must be assigned to some child (no leftovers).

A pen drive’s capacity must be large enough so the sum of sizes stored on that single drive does not exceed capacity.
Question: What is the minimum pen-drive capacity you must order so that you can give the games to the children under these rules?

Example (from the image):
n = 4, gameSize = [9, 2, 4, 6], k = 3.
 */
public class GameDistributionToKPen {
    public static int solve(int[]games, int k) {
        int N = games.length;
        if (k <= 0 || k > N) {
            return 0;
        }
        int lo = Arrays.stream(games).max().getAsInt();
        int hi = Arrays.stream(games).sum();
        while(lo < hi) {
            int mid = lo + ((hi - lo) / 2);
            if (canDistribute(games, k, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    private static boolean canDistribute(int[] arr, int k, int C) {
        int numPenDrives = 0;
        int le=0, ri = arr.length - 1;
        while(le <= ri) {
            if (arr[le] + arr[ri] <= C) {
                le++;
                ri--;
            } else {
                ri--;
            }
            if (numPenDrives > k) {
                return false;
            }
        }
        return numPenDrives <= k;
    }
    public static void main(String[] args) {
        assert solve(new int[]{2,4,6,9}, 3) == 9;
    }
}
