#link:https://neetcode.io/problems/longest-repeating-substring-with-replacement/question
class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        freq={}
        N = len(s)
        ans,max_repeating_char_count,left = 0,0,0
        for i in range(N):
            if s[i] in freq:
                freq[s[i]]+=1
            else:
                freq[s[i]]=1
            #max_repeating_char_count is the max value of 'the largest count of a character in all previous sub-array'. This maxCount can't help to judge whether a sub-array is valid, it can only help to judge when the length of a sub-array is bigger than the current maxLength, whether it is valid.
            max_repeating_char_count = max(max_repeating_char_count, freq[s[i]])
            
            #as soon as the number of characters to keep go beyond k, we start shrinking the window
            #why aren't we recalculating max_repeating_char_count again in the while loop?
            #Ans: while loop only runs 1 step every time, so we don't need to update maxCount.
            
            '''
            refer: https://leetcode.com/problems/longest-repeating-character-replacement/solutions/91271/java-12-lines-on-sliding-window-solution-2tgb/comments/137008/

            When num_chars_to_keep == 0, then then the window is filled with only one character
            When num_chars_to_keep > 0, then we have characters in the window that are NOT the character that occurs the most. 
            num_chars_to_keep is equal to exactly the # of characters that are NOT the character that occurs the most in that window. Example: For a window "xxxyz", num_chars_to_keep would equal 2. (max_repeating_char_count=3 and there are 2 characters here, "y" and "z" that are not "x" in the window.)
            
            We are allowed to have at most k replacements in the window, so whennum_chars_to_keep > k, then there are more characters in the window than we can replace, and we need to shrink the window.
            If we have window with "xxxy" and k = 1, that's fine because num_chars_to_keep - max_repeating_char_count = 1, which is not > k. maxLength gets updated to 4.

            But if we then find a "z" after, like "xxxyz", then we need to shrink the window because now num_chars_to_keep - max_repeating_char_count = 2, and 2 > 1. The window becomes "xxyz".
            
            Lastly,"max_repeating_char_count" may be invalid at some points, but this doesn't matter, because it was valid earlier in the string, and all that matters is finding the max window that occurred anywhere in the string. 
            
            Additionally, it will expand if and only if enough repeating characters appear in the window to make it expand. So whenever it expands, it's a valid expansion.
            More notes:
            - We only care about the character repeated most in our window (as any other character would require a larger k), we increment the left pointer, then increment the right pointer until k cannot support the most repeated character
            - We also dont need to re-find the most repeated character when we increment start, because if the new most repeated frequency is less than whatever we have seen before, surely our window will not be a better solution

            '''
            if i - left + 1 - max_repeating_char_count > k:
                freq[s[left]]-=1
                left+=1
            else:
                ans=max(ans, i - left + 1)
        return ans
