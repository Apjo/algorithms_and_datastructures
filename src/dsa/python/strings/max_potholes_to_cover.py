#link: https://leetcode.com/problems/maximum-number-of-potholes-that-can-be-fixed/description/?envType=company&envId=geico&favoriteSlug=geico-all
class Solution:
    def maxPotholes(self, road: str, budget: int) -> int:
        ans=0
        pot_hole_len_rev = sorted(map(len, road.split('.')), reverse=True)
        '''
        instead of above we can have the below code too to fetch all pothole len in reverse sorted order
        curr=0
        i=0
        consec=[]
        while(i<len(road)):
            if road[i]=='x':
                curr+=1
            else:
                if curr>0:
                    consec.append(curr)
                curr=0
            i+=1
        if curr>0:
            consec.append(curr)

        print(sorted(consec, reverse=True))
        '''
        for pot_hole_len in pot_hole_len_rev:
            curr_len = min(pot_hole_len + 1, budget)
            if curr_len <= 1:
                break
            budget -= curr_len
            ans+=(curr_len - 1)
        return ans