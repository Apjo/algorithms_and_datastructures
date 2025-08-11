package dsa.problemsolving.neetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
//date:07/23/2025
public class Problem18 {
    //sort the array, tc: O(nlogn)
    public int longestConsecutive(int[] nums) {
        int ans=0;
        Arrays.sort(nums);
        int curr=nums[0], i=0, len=0;
        while(i < nums.length) {
            if (curr == nums[i]) {
                while(i < nums.length && curr==nums[i]) {
                    i++;
                }
                len++;
                curr++;
                ans=Math.max(ans, len);
            }
            if (curr != nums[i]) {
                len=0;
                curr=nums[i];
            }
        }
        return ans;
    }
    //no sorting, tc: O(n)
    /*
    First turn the input into a set of numbers. That takes O(n) and then we can ask in O(1) whether we have a certain number.
Then go through the numbers.
If the number x is the start of a streak (i.e., x-1 is not in the set), then test y = x+1, x+2, x+3, ... and stop at the first number y not in the set.
The length of the streak is then simply y-x and we update our ans with that.
Since we check each streak only once, this is overall O(n).
notes:
When checking for x in the number, it must be ensured that x is the first number in a contiguous array.
If x - 1 is in the set, let's say, it's at the 2nd place of the array, you cannot count from the 2nd number to count the whole length.
     */
    public int longestConsecutiveLinear(int[] nums) {
        Set<Integer> hs = new HashSet<>();
        int ans=0;
        for(int ii:nums) {hs.add(ii);}
        for(int ii:hs) {
            if (!hs.contains(ii - 1)) {
                int m = ii + 1;
                while(hs.contains(m)) {
                    m++;
                }
                ans = Math.max(ans, m - ii);
            }
        }
        return ans;
    }
        public static void main(String[] args) {
    }
}
