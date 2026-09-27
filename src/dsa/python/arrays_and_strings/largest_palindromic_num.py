#link: https://leetcode.com/problems/largest-palindromic-number/
from collections import Counter

class Solution:
    def largestPalindromic(self, num: str) -> str:
        #freq count of all chars
        freq_count = Counter(num)
        mid_elem = -1
        res=[]

        #iterate from 9 to 0, and for each digit check:
        for i in range(9, -1, -1):
            #if freq. count of this digit is odd we need the max value
            if freq_count[str(i)] % 2 == 1:
                mid_elem = max(mid_elem, i)
            #elif we are at 0, and nothing in temp substr, we break
            if i == 0 and len(res)==0:
                break
            #for those that have even occurrence, we break it into 2 parts i.e. 1/2 we append to the beginning of this substr
        #reverse the first 1/2 of the substr, this becomes the second 1/2 that gets added to the result substr
            even_count_half = freq_count[str(i)] // 2
            while even_count_half > 0:
                res.append(i)
                even_count_half-=1
        
        rev_list = res[::-1]
        res.extend(rev_list)
        
        
        #finally, if there is a middle element, add it to the middle
        if mid_elem != -1:
            res.insert(len(res) // 2, mid_elem)
        
        #return the result if not empty, else simply return 0
        if not res:
            return "0"
        
        return ''.join([str(elem) for elem in res])