#link:
from typing import List

class Solution:
    #time: O(N),space:O(1)
    def trap(self, height: List[int]) -> int:
        if not height:
            return 0
        N = len(height)
        ans=0
        le,ri,lemax,rimax=0,N-1,height[0], height[N-1]
        while le < ri:
            if lemax <= rimax:
                le+=1
                if lemax < height[le]:
                    lemax=height[le]
                else:
                    ans+=lemax - height[le]
            else:
                ri-=1
                if rimax < height[ri]:
                    rimax=height[ri]
                else:
                    ans+=rimax - height[ri]
        return ans