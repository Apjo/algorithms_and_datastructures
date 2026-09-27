#link: https://neetcode.io/problems/largest-rectangle-in-histogram/question
from typing import List

class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        stk=[]
        ans=0
        #refer to https://leetcode.com/problems/largest-rectangle-in-histogram/editorial/comments/604574/?parent=175925 for more details
        #iterate over the heights array, if you see the height on the top of the stk >= heights[i]
        for i in range(len(heights)):
            start_index = i 
            while stk and stk[-1][1] >= heights[i]:
                ht, idx=stk.pop()
                w = i - idx
                a = ht * w
                ans = max(ans, a)
                start_index=idx
            stk.append((heights[i], start_index))
        while stk:
            ht, idx=stk.pop()
            w = i - idx
            a = ht * w
            ans = max(ans, a)
        return ans

        #if the stk is not empty, continue doing the calculation of area
        #return the area