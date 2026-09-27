#link: https://neetcode.io/problems/longest-substring-without-duplicates/question

class Solution:
    #time: O(n) this efficient as each char in the string gets added or removed once
    def lengthOfLongestSubstring(self, s: str) -> int:
        freq={}
        N,left,ans=len(s),0,0

        for i in range(N):
            #expand to right while keeping track of frequency of characters
            if s[i] in freq:
                freq[s[i]]+=1
            else:
                freq[s[i]]=1
            #we found a duplicate char, shrink the window from left until the duplicate is removed
            while left < i and freq[s[i]] > 1:
                freq[s[left]]-=1
                left+=1
            #window represents a valid substring, we track its max size
            ans=max(ans, i-left + 1)
        return ans