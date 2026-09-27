#link:https://leetcode.com/problems/ransom-note/?envType=company&envId=spotify&favoriteSlug=spotify-all

class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        freq_1 = {}
        for c in ransomNote:
            if c in freq_1:
                freq_1[c]+=1
            else:
                freq_1[c] = 1
        for c in magazine:
            if c in freq_1:
                if freq_1[c] > 0:
                    freq_1[c]-=1
            
        return all(v == 0 for v in freq_1.values())
    
    #or using Counter
    def canConstruct(self, s1:str, s2:str) -> bool:
        from collections import Counter

        return Counter(s1) - Counter(s2) == {}