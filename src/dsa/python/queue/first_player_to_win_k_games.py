#link https://leetcode.com/problems/find-the-first-player-to-win-k-games-in-a-row/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all

from collections import deque
from typing import List

class Solution:
    def findWinningPlayer(self, skills: List[int], k: int) -> int:
        #if k >= n then only the player with maximum value for skills wins
        if skills and k >= len(skills):
            max_skill = max(skills)
            for i in range(len(skills)):
                if max_skill == skills[i]:
                    return i
        
        #else we simulate and find out!
        winners={}
        index_queue = deque()
        for index, element in enumerate(skills):
            index_queue.append(index)

        while index_queue:
            skill1 = index_queue.popleft()
            skill2 = index_queue.popleft()
            if skills[skill1] > skills[skill2]:
                #add index1 to front of queue
                index_queue.appendleft(skill1)
                #add index2 to end of queue
                index_queue.append(skill2)
                #mark winners
                winners[skill1] = winners.get(skill1, 0) + 1
                if k == winners[skill1]:
                    return skill1
            else:
                #add index2 to front of queue
                index_queue.appendleft(skill2)
                #add index1 to end of queue
                index_queue.append(skill1)
                #mark winners
                winners[skill2] = winners.get(skill2, 0) + 1
                if k == winners[skill2]:
                    return skill2
