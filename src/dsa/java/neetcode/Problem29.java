package dsa.java.neetcode;

import java.util.*;

public class Problem29 {
    /*
    Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] where nums[i] + nums[j] + nums[k] == 0, and the indices i, j and k are all distinct.

The output should not contain any duplicate triplets. You may return the output and the triplets in any order.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]

Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].

Example 2:

Input: nums = [0,1,1]

Output: []
Explanation: The only possible triplet does not sum up to 0.

Example 3:

Input: nums = [0,0,0]

Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
     */
    //date:07/30/2025
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        //Set<Integer> j = new HashSet<>();
        for(int i=0; i < nums.length; i++) {
            int le = i + 1, ri = nums.length - 1;
            while(le < ri) {
                int ss = nums[i] + nums[le] + nums[ri];
                if (ss == 0) {
                    res.add(Arrays.asList(nums[i], nums[le], nums[ri]));
                    le+=1;ri-=1;
                } else if(ss < 0) {
                    le+=1;
                } else {
                    ri-=1;
                }
            }
            //List<Integer> temp = new ArrayList<>(j);
            //res.add(temp);
        }
        return new ArrayList<>(res);
    }
    public List<List<Integer>> threeSumNoExtraSpace(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int L = nums.length;
        for(int i=0; i < L; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int le = i + 1, ri = L - 1;
            while(le < ri) {
                int ss = nums[i] + nums[le] + nums[ri];
                if (ss == 0) {
                    res.add(Arrays.asList(nums[i], nums[le], nums[ri]));
                    le+=1;ri-=1;
                } else if(ss < 0) {
                    le+=1;
                } else {
                    ri-=1;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
    }
}
