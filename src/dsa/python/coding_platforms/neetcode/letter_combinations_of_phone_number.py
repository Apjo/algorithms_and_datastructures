"""
Filename: letter_combinations_of_phone_number.py
Date: 2026-07-12
link: https://neetcode.io/problems/combinations-of-a-phone-number/question
"""

from typing import List

class Solution:
    #time:O(4^N), space:output list O(n*4^n)
    def letterCombinations(self, digits: str) -> List[str]:
        """
        Each digit maps to a set of characters (like on a phone keypad).
        The task is to choose one character per digit, in order, and generate all possible combinations
        At index i, pick one character from the mapping of digits[i]
         - Move to the next digit
         - When the length of the built string equals the number of digits, we have formed one valid combination
        """
        def solve(idx, buff, res):
            if idx == len(digits):
                res.append(buff)
                return
            curr_digit = int(digits[idx])
            for curr_char in d_to_l[curr_digit]:
                buff += curr_char
                solve(idx + 1, buff, res)
                buff = buff[:-1]

        if not digits:
            return []
        d_to_l = {
            0: (),
            1: (),
            2: ("a", "b", "c"),
            3: ("d", "e", "f"),
            4: ("g", "h", "i"),
            5: ("j", "k", "l"),
            6: ("m", "n", "o"),
            7: ("p", "q", "r", "s"),
            8: ("t", "u", "v"),
            9: ("w", "x", "y", "z"),
        }

        idx, res, buff = 0, [], ""
        solve(idx, buff, res)
        # print(res)
        
        return res



if __name__ == '__main__':
    Solution().letterCombinations("34")