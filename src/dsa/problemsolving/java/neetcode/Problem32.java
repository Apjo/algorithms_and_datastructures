package dsa.problemsolving.java.neetcode;

public class Problem32 {
    /*
    You are given an integer array heights where heights[i] represents the height of the ith bar.
You may choose any two bars to form a container. Return the maximum amount of water a container can store.
Input: height = [1,7,2,5,4,7,3,6]

Output: 36
Example 2:

Input: height = [2,2,2]

Output: 4
Constraints:

2 <= height.length <= 1000
0 <= height[i] <= 1000
     */
    //date:08/01/2025
    public int maxArea(int[] heights) {
        int ans = Integer.MIN_VALUE;
        int lo=0, hi = heights.length - 1;

        while(lo < hi) {
            int breadth = hi - lo;
            int length = Math.min(heights[lo], heights[hi]);
            int area = breadth * length;
            ans=Math.max(ans, area);
            //always move the pointer pointing to the smaller of the 2 heights
            if (heights[lo] <= heights[hi]) {
                lo++;
            } else {
                hi--;
            }
        }

        return ans;
    }
    public static void main(String[] args) {
    }
}
