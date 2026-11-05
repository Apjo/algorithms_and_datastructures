"""
Filename: hand_of_straight.py
Date: 2026-10-21
"""


class Solution:
    def isNStraightHand(self, hand: list[int], groupSize: int) -> bool:
        N = len(hand)
        if N % groupSize != 0:
            return False
        freq = {}
        hand.sort()
        for i in range(N):
            freq[hand[i]] = freq.get(hand[i], 0) + 1

        for i in range(N):
            # If the current card is already used up (its count is 0), skip it
            # Otherwise, try to form a group starting from this card
            if freq[hand[i]] == 0:
                continue
            """
            To form a group starting at hand[i]:
            For every value from hand[i] to hand[i] + groupSize - 1:
                If that value does not exist in the count map, return false
                Otherwise, decrement its count by 1
            """
            for j in range(groupSize):
                curr = hand[i] + j
                if curr in freq:
                    if freq[curr] == 0:
                        return False
                    freq[curr] -= 1
                else:
                    return False
        # all cards are forming a group without fails
        return True


if __name__ == "__main__":
    Solution().solve()
