"""
Filename: partition_equal_subset_sum.py
Date: 2026-08-30
"""

from typing import List


class Solution:
    def canPartition_dp(self, nums: List[int]) -> bool:
        N = len(nums)
        all_sum = sum(nums)
        if all_sum % 2 != 0:
            return False
        all_half = all_sum // 2

        """
        I started by initializing 
        dp=[[False for _ in range(all_half + 1)]] * (N + 1)
        
        This is WRONG since this creates multiple references to the same list!

        [[False for _ in range(all_half + 1)]] creates ONE list
        * (N + 1) repeats that reference N+1 times
        All rows point to the same list object.
        
        So, if you modify dp[0][5], you also change dp[1][5], dp[2][5], etc.

        CORRECT APPROACH:
        dp = [[False] * (all_half + 1) for _ in range(N + 1)]
        Creates N+1 separate lists
        Each row is independent
        Modifications don't leak across rows
        """
        # dp[i][j]=using first i numbers, can we form the sum j?
        dp = [[False] * (all_half + 1) for _ in range(N + 1)]
        # REMEMBER: dp[i-1][j] OR dp[i-1][j-nums[i-1]]
        for i in range(N + 1):
            # for all number i having a sum=0 is possible by not taking any number i
            dp[i][0] = True
        for i in range(1, N + 1):
            for j in range(1, all_half + 1):
                # can we afford to pick this number?
                if nums[i - 1] <= j:
                    # When we pick this number the result depends on => can we make sum j without using nums[i-1] SKIP path OR can we make sum j by using nums[i-1] i.e. PICK path?
                    dp[i][j] = dp[i - 1][j] or dp[i - 1][j - nums[i - 1]]
                else:
                    # skip this number as its too big
                    dp[i][j] = dp[i - 1][j]

        return dp[N][all_half]

    def canPartition_memo(self, nums: List[int]) -> bool:
        N = len(nums)
        all_sum = sum(nums)
        if all_sum % 2 != 0:
            return False
        all_half = int(all_sum / 2)

        def solve(idx, prev_sum) -> bool:
            # Check if we've already computed the answer for this exact state (idx, prev_sum)
            # If yes, return the memoized answer instead of recomputing
            if (idx, prev_sum) in memo:
                return memo[(idx, prev_sum)]
            # we found the 1/2(total sum)!
            if prev_sum == all_half:
                return True
            # out of bounds, or exceeds target!
            if idx >= N or prev_sum > all_half:
                return False

            # PICK BRANCH: Include nums[idx] in our subset
            # Calculate new sum by adding current number
            new_sum = prev_sum + nums[idx]
            # Recurse to next index with this new sum. See if this choice leads to a solution.
            ans = solve(idx + 1, new_sum)
            # If this branch found a valid subset, store the result and return immediately.
            # No need to try SKIP—we already found the answer.
            if ans:
                memo[(idx, prev_sum)] = ans
                return True

            # SKIP BRANCH: Don't include nums[idx] in our subset
            # Recurse to next index, keeping the same sum (we skipped this number)
            ans = solve(idx + 1, prev_sum)
            # If this branch found a valid subset, store the result and return immediately.
            if ans:
                memo[(idx, prev_sum)] = ans
                return True
            # TL; DR:We got till here, we tried PICK it failed, we tried SKIP it too failed, so ultimately we return a False
            # Both branches failed. We tried adding nums[idx], it didn't work.
            # We tried skipping nums[idx], it didn't work either.
            # So from this state (idx, prev_sum), there's no way to reach the target sum.
            # Store False in memo so we don't waste time recomputing this state again.
            memo[(idx, prev_sum)] = False
            return False

        # We always store at (idx, prev_sum) the state we entered the function with—because that's what future calls will look up
        memo = {}
        return solve(0, 0)

    # using pure recurion but making the recursion return a value
    # time:O(2^N) space:O(N) as N recursive calls
    def canPartition_rec_2(self, nums: List[int]) -> bool:
        N = len(nums)
        all_sum = sum(nums)
        if all_sum % 2 != 0:
            return False
        # found_half_sum = False
        all_half = int(all_sum / 2)

        def solve(idx, prev) -> bool:
            print(f"Entering at index={idx}, prev sum={prev}")
            # nonlocal ans
            if idx >= N:
                print("OUTOF BOUNDS! returning")
                if prev == all_half:
                    return True
                return False

            if prev > all_half:
                return False

            if prev == all_half:
                return True

            prev += nums[idx]
            print(f"PICK num={nums[idx]} at idx={idx}, new sum={prev}")
            ans = solve(idx + 1, prev)
            if ans:
                return True
            prev -= nums[idx]
            print(f"SKIP to next index={idx + 1}, curr sum={prev}")
            ans = solve(idx + 1, prev)
            if ans:
                return ans

            # we got till here, we tried PICK it failed, we tried SKIP it too failed, so ultimately we return a False
            return False

        return solve(0, 0)

    # using pure recursion, and global variables
    # time: O(2^N)
    def canPartition(self, nums: List[int]) -> bool:
        N = len(nums)
        all_sum = sum(nums)
        if all_sum % 2 != 0:
            return False
        found_half_sum = False
        all_half = int(all_sum / 2)

        def solve(idx, prev):
            nonlocal found_half_sum
            print(f"Entering at index={idx}, prev sum={prev}")
            if idx >= N:
                print("OUTOF BOUNDS! returning")
                if prev == all_half:
                    found_half_sum = True
                else:
                    found_half_sum = False
                return
            if found_half_sum:
                return
            if prev > all_half:
                print(f"curr sum={prev} > 1/2 sum={all_half}")
                found_half_sum = False
                return
            if prev == all_half:
                print(f"MATCH curr sum={prev} == 1/2 sum={all_half}")
                found_half_sum = True
                return
            prev += nums[idx]
            print(f"PICK num={nums[idx]} at idx={idx}, new sum={prev}")
            solve(idx + 1, prev)
            prev -= nums[idx]
            print(f"SKIP to next index={idx + 1}, curr sum={prev}")
            solve(idx + 1, prev)

        solve(0, 0)
        return found_half_sum


if __name__ == "__main__":
    Solution().solve()
