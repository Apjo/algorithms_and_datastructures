#link:https://neetcode.io/problems/max-water-container/question

from typing import List
class Solution:
    #time:O(n), space:O(1)
    def maxArea(self, heights: List[int]) -> int:
        left, right, ans=0, len(heights) - 1, float("-inf")
        while left < right:
            width = right - left
            #ht is limited by which end is smaller, else the water will overflow
            height = min(heights[left], heights[right])
            area = width * height
            ans=max(ans, area)
            if heights[left] <= heights[right]:
                left+=1
            else:
                right-=1
        return ans