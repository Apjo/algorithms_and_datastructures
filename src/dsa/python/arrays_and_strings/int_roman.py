"""
Filename: roman_to_int.py
Date: 2026-09-26
"""


class Solution:
    def intToRoman(self, num: int) -> str:
        res = []
        symbols = {1: "I", 4: "IV", 5:"V", "IX": 9, "X":10, "XL": 40, "L":50, "XC":90, "C":100, "CD": 400, "D":500, "CM":900, "M":1000}
        #start from reverse i.e. with the max value in symbols which is M
        for value, roman in reversed(symbols):
            if num == 0:
                break
            repeat_to_append = num // value
            if repeat_to_append > 0:
                res.append(roman * repeat_to_append)
            num -= value * repeat_to_append

        return "".join(res)


        


if __name__ == '__main__':
    Solution().solve()