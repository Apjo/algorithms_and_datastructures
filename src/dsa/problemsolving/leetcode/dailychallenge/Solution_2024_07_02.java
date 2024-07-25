package dsa.problemsolving.leetcode.dailychallenge;

import java.io.*;
import java.util.*;

public class Solution_2024_07_02 {
    public int minDifference(int[] nums) {
        if(nums.length < 5) {
            return 0;
        }
        Arrays.sort(nums);
        int ctr=0;
        int ans=0;
        while(ctr < 3) {
            //case1: delete 3 largest element, and 0 from low
            int d1 = nums[nums.length - 4] - nums[0];
            //case2: delete 3 from lo, and 0 from end
            int d2 = nums[nums.length - 1] - nums[3];
            //case3: delete 1 from lo, 2 from hi
            int d3 = nums[nums.length - 3] - nums[1];
            //case4: delete 2 from lo, 1 from hi
            int d4 = nums[nums.length - 2] - nums[2];
            ans = Math.min(d1, Math.min(d2, Math.min(d3, d4)));

            ctr++;
        }
        return ans;
    }
}
