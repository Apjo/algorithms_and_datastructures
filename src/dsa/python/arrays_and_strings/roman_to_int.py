"""
Filename: roman_to_int.py
Date: 2026-09-26

For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.
"""


class Solution:
    def romanToInt(self, s: str) -> int:
        ans = 0

        sym_to_val = {
            "I": 1,
            "IV": 4,
            "V": 5,
            "IX": 9,
            "X": 10,
            "XL": 40,
            "L": 50,
            "XC": 90,
            "C": 100,
            "CD": 400,
            "D": 500,
            "CM": 900,
            "M": 1000,
        }

        N = len(s)
        i = N - 1

        while i >= 0:
            curr = s[i]
            # if curr==V, or X prev has to be I
            # elif curr==L or C, prev has to be X
            # elif curr == D or M, prev has to be C
            prev = ""
            if i - 1 >= 0:
                prev = s[i - 1]
            print(f"curr={curr}, prev={prev}")
            if curr == "V" and prev == "I":
                ans += sym_to_val["IV"]
                print(f"curr ans={ans}")
                i -= 2
            elif curr == "X" and prev == "I":
                ans += sym_to_val["IX"]
                print(f"curr ans={ans}")
                i -= 2
            elif curr == "C" and prev == "X":
                ans += sym_to_val["XC"]
                print(f"curr ans={ans}")
                i -= 2
            elif curr == "M" and prev == "C":
                ans += sym_to_val["CM"]
                print(f"curr ans={ans}")
                i -= 2
            elif curr == "D" and prev == "C":
                ans += sym_to_val["CD"]
                print(f"curr ans={ans}")
                i -= 2
            elif curr == "L" and prev == "X":
                ans += sym_to_val["XL"]
                print(f"curr ans={ans}")
                i -= 2
            else:
                print(f"got current={curr}")
                ans += sym_to_val[curr]
                i -= 1

        return ans


if __name__ == "__main__":
    Solution().solve()
