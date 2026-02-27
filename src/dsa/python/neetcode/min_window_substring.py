#link https://neetcode.io/problems/minimum-window-with-characters/question
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        #if both are equal, return either
        if t == "":
            return ""
        #prepare a frequency map for t, and since we need a substring in s of T, it should have the same length as that of T, call it required, and init it to len(frequency map of T) i.e. how many distinct characters of T are in S
        freq_t = {}
        for c in t:
            freq_t[c] = 1+freq_t.get(c, 0)
        have, need = 0, len(freq_t)
        freq_s = {}
        left, ans=0,float("infinity")
        res_len=[-1, -1]
        #iterate over s from LEFT TO RIGHT:
        for i in s:
            curr_char = s[i]
            freq_s[curr_char] = 1 + freq_s.get(curr_char, 0)
            if curr_char in t and freq_s[curr_char] == freq_t[curr_char]:
                have+=1
            while have == need:
                if i - left + 1 < ans:
                    res_len = [left, i]
                    ans = i - left + 1
                freq_s[s[left]]-=1
                if s[left] in freq_t and freq_s[s[left]] < freq_t[s[left]]:
                    have-=1
                left+=1
        left, i = res_len
        return s[left : i + 1] if ans != float("infinity") else ""