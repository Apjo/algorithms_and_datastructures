#link:
from typing import List
class Solution:
    #time: O((m+n)) space: O(M+N)
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        
        def solve_med(merged: List[int]) -> float:
            N = len(merged)
            lo,hi = 0, N - 1
            mid = (lo + hi) // 2
            if N % 2 == 0:
                next_elem = mid + 1
                add_res = float(merged[mid] + merged[next_elem]) 
                return (add_res / 2)
            else:
                return float(merged[mid])
        
        def solve(M, N):
            p1, p2 = 0,0
            C=[]
            while p1 < M and p2 < N:
                if nums1[p1] <= nums2[p2]:
                    C.append(nums1[p1])
                    p1+=1
                else:
                    C.append(nums2[p2])
                    p2+=1
            while p1 < M:
                C.append(nums1[p1])
                p1+=1
            while p2 < N:
                C.append(nums2[p2])
                p2+=1
            return solve_med(C)
        
        if not nums1:
            return solve_med(nums2)
        if not nums2:
            return solve_med(nums1)
        
        return solve(len(nums1), len(nums2))
    #time: O(M+N), space: O(1)
    def findMedianSortedArrays_no_merge(self, nums1: List[int], nums2: List[int]) -> float:
        '''
        We can simulate the merge process using two pointers as in the one we have above but this time we only iterate from 0 to middle of the combined array.
        Because the median depends only on the middle elements, we do not need to process the entire merged array.
        We simply track the last one or two values seen while merging, and once we reach the halfway point, we can compute the median.
        '''
        median1, median2 = 0,0
        M,N = len(nums1), len(nums2)
        mid_loc = (M + N) // 2 + 1
        p1,p2=0,0
        for i in range(mid_loc):
            median2 = median1
            if p1 < M and p2 < N:
                if nums1[p1] <= nums2[p2]:
                    median1 = nums1[p1]
                    p1+=1
                else:
                    median1 = nums2[p2]
                    p2+=1
            elif p1 < M:
                median1 = nums1[p1]
                p1+=1
            else:
                median1 = nums2[p2]
                p2+=1
        if (M+N) % 2 == 1:
            return float(median1)
        else:
            return (median1 + median2) / 2.0
