#link: https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all
class Solution:
    def minimumSwap(self, s1: str, s2: str) -> int:
        '''
        iterate over both the input strs:
            -if str1[i]!=str2[i]:
                -if str1[i]==x:
                    increment xy count by 1
                else:
                    increment yx count by 1
        check to see if the addition of xy count and yx count is even, since we need a pair, if it is not return -1
        1. if we have 2 xy count, we only need 1 swap, similarly if we have 2 yx count we only need 1 swap i.e. example strings being(xx,yy) or (yy, xx) respectively so add 1/2 of it to final result
        2. If we still have 1 count of xy, and 1 count of yx then they need 2 swaps so add 2 to result
        return result


        '''
        xy,yx=0,0
        res=0
        for i in range(len(s1)):
            if s1[i]=='x' and s2[i]=='y':
                xy+=1
            elif s1[i]=='y' and s2[i]=='x':
                yx+=1
        if xy%2 != yx % 2:
            return -1
        res=xy//2 
        res+=yx//2
        #NOTE: we could rewrite below as res+=(xy%2)+(yx%2) also
        if xy % 2 == 1:
            #if count is 1 we still have xy, or yx situation so we need 2 more swaps
            res+=2
        return res