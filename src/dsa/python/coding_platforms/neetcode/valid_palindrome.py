class Solution:
    def isPalindrome(self, s: str) -> bool:        
        s1 = s.strip().lower()
        lo, hi =0, len(s1) - 1

        while lo < hi:
            while lo < hi and not s1[lo].isalnum():
                lo+=1
            while hi > lo and not s1[hi].isalnum():
                hi-=1
            if s1[lo] != s1[hi]:
                return False
            lo+=1
            hi-=1
        return True