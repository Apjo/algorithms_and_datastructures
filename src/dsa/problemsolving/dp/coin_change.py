#link: https://leetcode.com/problems/coin-change/description/
import math
from typing import List


def solve(coins: List[int], amount: int) -> int:
    ans=[float('inf')]*(amount + 1)
    #print(len(ans))
    ans[0]=0 #min num of coins to make up amount 0 is 0
    #say we have coins with denominations c1,c2,c...ck, so f(n)=is the min. no of coins required to make amount n
    #so we rely on coins c1,c2,..,ck to make the amount n, and we need the minimum amongst them
    # so f(n)=1 + min{f(n-c1), f(n - c2), ..., f(n - ck)}, we add the 1 to denote that we have picked one coin of a particular denomination
    #iterate over all the coins
    for i in range(1, amount + 1):
        for c in coins:
            if i - c >= 0 and ans[i - c] != float('inf'):
                ans[i] = min(ans[i], ans[i - c] + 1)
    return -1 if ans[amount] == float('inf') else ans[amount]


def main():
    assert solve([1,2, 5], 11) == 3
    assert solve([1,5,7], 10) == 2


if __name__ == '__main__':
    main()
