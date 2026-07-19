"""
Filename: smallestSubsequence.py
Date: 2026-07-19
"""

from collections import Counter


class Solution:
    # time: O(n)
    def smallestSubsequence(self, s: str) -> str:
        """
        -iterate over the input str
        - if the char at index i is seen, continue
        - else consider adding to the subseq
            -but, if the current char s[i] > stk[-1]
                -add this s[i] to stk
            -else continue popping all elements < s[i] from the stk
        - pop from the stk, and return the final res as str
        """

        if not s:
            return ""
        N = len(s)
        visited = [False] * 26
        freq = Counter(s)
        stk = []
        for i in range(N):
            freq[s[i]] -= 1

            idx = ord(s[i]) - ord("a")
            if visited[idx]:
                continue
            """
            case1:
            - stack is empty ? simply push to stk
            case 2:
            - curr ch is >= character at top of stack ? Keeping it keeps the lexicographical order unchanged, so we simply push it onto the stack.
            case 3:
            - curr ch is < character at top of stack ?
                Since we want build the smallest subsequence, we have to greedily ensure that the smallest character appear as early as possible. Thus, if the larger character (top element) will appear later in the string:
                    We can safely pop this without losing this character from the final subsequence.Then, continue comparing with the new top element.
            We must repeat this until either:
                the stack becomes empty (Case 1),
                the top element is lexicographically smaller, (Case 2)
                or the top element does not appear again later.
            Everytime we check a new character, we decrement its frequency.
            If the character still has freq>0, then there must be the same character that will appear later.
            If case 3 is valid, we have to set visited[this chars index]=False, and pop() it from the main stack.
            case 4:
            -The current character is already seen in the stack.
            Since every distinct character must appear exactly once we "ignore" this char.
            """
            while stk and s[stk[-1]] > s[i] and freq[s[stk[-1]]]:
                curr_idx = stk.pop()
                visited[ord(s[curr_idx]) - ord("a")] = False

            #     print(f"and making {curr_idx} unvisited")
            # print(f"After removing from stk={stk}, we add element at s[i]={s[i]}, at idx={idx}")
            stk.append(i)
            visited[idx] = True

        op = []
        while stk:
            op.append(s[stk.pop()])
        return "".join(reversed(op))


if __name__ == "__main__":
    Solution().smallestSubsequence("bcaacdcbc")
