class Solution:
    def combinationSum(self, nums: List[int], target: int) -> List[List[int]]:
        def solve(idx, target, res, buff):
            if target == 0:
                res.append(buff[:])
                return
            if target < 0 or idx >= len(nums):
                return
            # pick,
            buff.append(nums[idx])
            solve(idx, target - nums[idx], res, buff)
            buff.pop()
            solve(idx + 1, target, res, buff)

        idx, res, buff = 0, [], []
        solve(idx, target, res, buff)

        return res
