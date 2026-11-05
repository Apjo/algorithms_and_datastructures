"""
Filename: gas_station.py
Date: 2026-10-19
"""


class Solution:
    def canCompleteCircuit(self, gas: list[int], cost: list[int]) -> int:
        # if the total gas we have is less than the total cost, we cannot make the round trip at all
        if sum(gas) < sum(cost):
            return -1
        # If the total gas is sufficient, then at least one valid starting station exists. Under this problem's guarantee, there will be at most one valid answer.
        # While iterating from left to right keep track of gas consumed or gas balance, if at anytime the balance drops below 0 we reset it to next index since we know that any stations from i to before i won't be able to make the round trip, so we reset the tank, and start with next station
        N, gas_balance, ans = len(gas), 0, 0

        for i in range(N):
            gas_balance += gas[i] - cost[i]
            if gas_balance < 0:
                ans = i + 1
                gas_balance = 0

        return ans


if __name__ == "__main__":
    Solution().solve()
