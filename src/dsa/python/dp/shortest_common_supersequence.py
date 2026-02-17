# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/7/26
Notes:
- no need to add characters those that are already part of the LCS
- you need to add the remaining characters, so the question is where do you add them?
- we also could start with the longer of the 2 strings, and find the max number of characters already common with the shorter
string? its the LCS!so characters that are part of the lcs from the longer string, those characters are already a part of the shorter string.
so no need to include them to form the supersequence.
- we focus on characters that aren't a part of the LCS
- we can get the len of the superseq as L1 + L2 - LCS
- once the grid for the lcs is built, we need to use it to get our answer:
    - whenever we make a diagonal move we are going to append one copy of the lcs char
    - and whenever we make a horizontal or vertical move we take the char we are inserting or deleting in any order
    - that would help in collecting the characters in the order in which they would appear in the alignment
    - so we start from the bottom right corner, and we need to reach the top left corner by following the chain of values back
    - so wherever we are located we check to see where we derived the value(ans[i][j]) from was it from the neighbor directly above us, or the neighbor to our left, or
    from our left diagonal neighbor?


"""


def shortestCommonSupersequence(str1: str, str2: str) -> str:
    def get_lcs_grid(s1, s2):
        m,n = len(s1), len(s2)
        ans=[[0]*(n + 1) for _ in range(m + 1)]
        for i in range(1, m+1):
            ans[i][0]=0
        for i in range(1, n+1):
            ans[0][i]=0
        for i in range(1, m+1):
            for j in range(1, n + 1):
                if s1[i-1] == s2[j-1]:
                    s=1
                else:
                    s=0
                ans[i][j] = max(ans[i-1][j], ans[i][j-1], ans[i-1][j-1] + s)
        return ans
    def build_seq_str(s1, s2):
        rows, cols = len(s1), len(s2)
        grid = get_lcs_grid(s1, s2)
        res = []
        while rows > 0 and cols > 0:
            if grid[rows][cols] == grid[rows - 1][cols]:
                res.append(s1[rows - 1])
                rows-=1
            elif grid[rows][cols] == grid[rows][cols - 1]:
                res.append(s2[cols - 1])
                cols-=1
            else:
                res.append(s1[rows - 1])
                rows-=1
                cols-=1
        while rows > 0:
            res.append(s1[rows - 1])
            rows-=1
        while cols > 0:
            res.append(s2[cols - 1])
            cols-=1
        res= reversed(res)
        return "".join(res)
    return build_seq_str(str1, str2)


def main():
    assert shortestCommonSupersequence(str1 = "abac", str2="cab") == "cabac"
    assert shortestCommonSupersequence(str1 = "aaaaaaaa", str2 = "aaaaaaaa") == "aaaaaaaa"


if __name__ == '__main__':
    main()
