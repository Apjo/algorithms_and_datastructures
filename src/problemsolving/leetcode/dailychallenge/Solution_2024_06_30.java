package problemsolving.leetcode.dailychallenge;

public class Solution_2024_06_30 {
    public boolean threeConsecutiveOdds(int[] arr) {
        if(arr.length < 3) {
            return false;
        }
        for(int i=0; i < arr.length; i++) {
            if(i + 2 < arr.length) {
                int f = arr[i];
                int s = arr[i+1];
                int l = arr[i+2];
                if ((f & 1)==1 && (s & 1)==1 && (l&1)==1) {
                    return true;
                }
            }
        }
        return false;
    }
    }
