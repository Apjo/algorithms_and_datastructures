"""
Filename: june_30.py
Date: 2026-07-01
link: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/?envType=daily-question&envId=2026-06-30
"""


class Solution:
    """
    hint used:
    - Use two pointers for the sliding window.
    - Think when can the sliding window size be of maximum length ( hint : a , b , c ).
    - If I got the max window size then do i need to check on the right for current window? Can we not just count substrings simply by ............ ( hint: arithmetic )
    - If I have got the max window size, then do i need to check for the left windows ?
    - When and why should we increment the left pointer, does it have to do something with window size for (hint: a,b,c)
    """
    def numberOfSubstrings(self, s: str) -> int:
        # keep on adding the chars to a freq map, until each one of their frequencies respectively becomes >=1
        freq_map = {}
        N = len(s)
        count, left = 0, 0
        for i in range(N):
            if s[i] in freq_map:
                freq_map[s[i]] += 1
            else:
                freq_map[s[i]] = 1
            # while all of the a/b/c have their frequenceis > 0
            while (
                freq_map.get("a", 0) > 0
                and freq_map.get("b", 0) > 0
                and freq_map.get("c", 0) > 0
            ):
                # calculate the number of substrings containing only a/b/c have been formed????
                # how? s.len - i
                count += N - i
                # decrement frequency of left most char by 1
                freq_map[s[left]] -= 1
                # move the left pointer
                left += 1
        return count
        


if __name__ == '__main__':
    Solution().numberOfSubstrings("abcabc")