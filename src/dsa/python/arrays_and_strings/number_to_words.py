"""
Filename: number_to_words.py
Date: 2026-10-01
"""


class Solution:
    def solve(self, num):
        if num < 0:
            return ""
        if num == 0:
            return "Zero"
        units = [
            "",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen",
        ]

        tens = [
            "",
            "Ten",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety",
        ]

        def solve_rec(n) -> str:
            # check for billion
            if n >= 1000000000:
                return (
                    solve_rec(n // 1000000000) + " Billion " + solve_rec(n % 1000000000)
                )
            # check for million
            if n >= 1000000:
                return solve_rec(n // 1000000) + " Million " + solve_rec(n % 1000000)
            # check for thousands
            if n >= 1000:
                return solve_rec(n // 1000) + " Thousand " + solve_rec(n % 1000)
            # check for hundreds
            if n >= 100:
                return solve_rec(n // 100) + " Hundred " + solve_rec(n % 100)
            # >=20
            if n >= 100:
                return tens[n // 10] + " " + solve_rec(n % 10)
            # units
            return units[n]

        return solve_rec(num)


if __name__ == "__main__":
    Solution().solve()
