# link: https://leetcode.com/problems/total-waviness-of-numbers-in-range-i/

class Solution:
    def totalWaviness(self, num1: int, num2: int) -> int:
        ans = 0
        for num in range(num1, num2+1):
            numstr = str(num)
            for i in range(1, len(numstr) - 1):
                if numstr[i] > numstr[i - 1] and numstr[i] > numstr[i + 1] or numstr[i] < numstr[i - 1] and numstr[i] < numstr[i + 1]:
                    ans+=1
        
        return ans
