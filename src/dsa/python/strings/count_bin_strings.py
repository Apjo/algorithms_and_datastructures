#link:
class Solution:
    def countBinarySubstrings(self, s: str) -> int:
        groups=[1] #first element of s belong to its own group
        for i in range(1, len(s)):
            #if the character matches we just include it in same group
            if s[i] == s[i-1]:
                groups[-1]+=1
            else:
                #else form a new group
                groups.append(1)
        ans=0
        for i in range(1, len(groups)):
            ans+=min(groups[i - 1], groups[i])
        return ans
    def countBinarySubstrings_const_space(self, s: str) -> int:
        ans,prev, curr=0,0,1
        for i in range(1, len(s)):
            if s[i] == s[i - 1]:
                curr+=1
            else:
                prev=curr
                curr=1
            if prev >= curr:
                ans+=1
        return ans