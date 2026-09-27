#link:https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all
from collections import deque

class Solution:
    def findLexSmallestString(self, s: str, a: int, b: int) -> str:
        #enumerate all possibilites for the input string after applying each operation on it, 
        #we discard numbers that are already seen, and keep on collecting into a result
        #finally pick the min
        q = deque([s])
        visited=set([s])
        #we start with input string to be the current lexicographically smallest string
        min_ans = s

        def add_digit_to_odd_indices(curr_str: str) -> str:
            curr = list(curr_str)
            for i in range(len(curr)):
                curr_ch = curr[i]
                if i % 2 == 1:
                    new_digit = (int(curr_ch) + a) % 10
                    curr[i]=str(new_digit)
                else:
                    curr[i]=curr_ch
            return "".join(curr)
        while q:
            curr = q.pop()

            min_ans = min(min_ans, curr)
            
            rotate_num_str = curr[-b:] + curr[:-b]

            if rotate_num_str not in visited:
                q.append(rotate_num_str)
                visited.add(rotate_num_str)
            
            add_num_str = add_digit_to_odd_indices(curr)

            if add_num_str not in visited:
                q.append(add_num_str)
                visited.add(add_num_str)

        return min_ans
