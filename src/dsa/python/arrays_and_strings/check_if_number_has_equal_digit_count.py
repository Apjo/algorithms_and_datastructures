#link: https://leetcode.com/problems/check-if-number-has-equal-digit-count-and-digit-value/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all

class Solution:
    def digitCount(self, num: str) -> bool:
        ctr=[0]*10
        for cc in num:
            ctr[int(cc)]+=1
        for i in range(len(num)):
            freq = ctr[i] 
            digit = int(num[i])
            #if frequency of this index doesn't match the number at this index
            if freq != digit:
                return False
        return True

        