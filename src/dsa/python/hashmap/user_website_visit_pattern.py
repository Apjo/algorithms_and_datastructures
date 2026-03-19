#link: https://leetcode.com/problems/analyze-user-website-visit-pattern/?envType=company&envId=spotify&favoriteSlug=spotify-all

import collections
from typing import List

class Solution:
    def mostVisitedPattern(self, username: List[str], timestamp: List[int], website: List[str]) -> List[str]:
        user_website_vist = collections.defaultdict(list)
        user_website_visit_count = collections.Counter()
        for user, timestmp, webs in zip(username, timestamp, website):
            user_website_vist[user].append((timestmp, webs))
            user_website_visit_count[user]+=1
        three_seq_cnt = collections.defaultdict(int)
        for user, records in user_website_vist.items():
            records.sort() #sort on timestmp in ASC order
            visited = set() #if some 3-sequence appears multiple times for one person, it only counts once
            #generate all 3 sequences
            for i in range(user_website_visit_count[user] + 1):
                for j in range(i+1, user_website_visit_count[user]):
                    for k in range(j + 1, user_website_visit_count[user]):
                        three_seq = (records[i][1], records[j][1], records[k][1])
                        if three_seq in visited:
                            continue
                        three_seq_cnt[three_seq]+=1
                        visited.add(three_seq)
        # ans = sorted(three_seq_cnt.items(), reverse=True, key=lambda x:(-x[1], x[0]))
        ans = sorted(three_seq_cnt.items(), key=lambda x:(-x[1], x[0]))
        return ans[0][0]