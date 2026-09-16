"""
Filename: smaller_prefixes.py
Date: 2026-09-16
"""


class Solution:
    
    def smaller_prefixes_slow_fast_pointer(self, arr):
        if not arr:
            return True
        sp, fp, slow_sum, fast_sum,N = 0,0,0,0,len(arr)

        while fp < N:
            slow_sum+=arr[sp]
            fast_sum+=arr[fp] + arr[fp + 1]
            if slow_sum >= fast_sum:
                return False
            sp+=1
            fp+=2

        return True

    #using prefix sum, time: O(n), space:O(n)
    def smaller_prefixes(self, arr):
        if not arr:
            return True
        N = len(arr)
        ps = []
        ps.append(arr[0])

        k = N // 2
        for i in range(1, N):
            ps.append(ps[i - 1] + arr[i])

        for kd in range(1, k + 1):
            first_k = ps[kd - 1]
            twice = kd * 2
            first_2k = ps[twice - 1]
            if first_k > first_2k:
                return False

        return True

        


if __name__ == '__main__':
    Solution().solve()