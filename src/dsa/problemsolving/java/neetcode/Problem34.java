package dsa.problemsolving.java.neetcode;

public class Problem34 {
    /*
    You are given an array of non-negative integers height which represent an elevation map.
    Each value height[i] represents the height of a bar, which has a width of 1.
Return the maximum area of water that can be trapped between the bars.
Possible follow up:
Suppose a '0' in the input means that there is a leak at that position and the water can leak out.
After the adjustment, that is, after the water levels have stabilized due to leaking, what is the answer?
How do we change our approach/what would be out ideal answer for this scenario?
Possible approach: https://leetcode.com/problems/trapping-rain-water/description/comments/2255402/?parent=1566651

     */
    //date:08/01/2025
    //refer for explanation: https://www.hellointerview.com/learn/code/two-pointers/trapping-rain-water
    //time: O(n), space:O(1)
    public int trap(int[] height) {
        int ans=0, leftMax=height[0], rightMax=height[height.length - 1],le=0,ri=height.length - 1;
        while(le < ri) {
            if (leftMax < rightMax) {
                le++;
                if (height[le] > leftMax) {
                    leftMax = height[le];
                } else {
                    ans+=leftMax - height[le];
                }
            } else {
                ri--;
                if(height[ri] > rightMax) {
                    rightMax = height[ri];
                } else {
                    ans+=rightMax - height[ri];
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
    }
}
