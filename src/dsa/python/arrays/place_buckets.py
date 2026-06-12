#link: https://leetcode.com/problems/minimum-number-of-food-buckets-to-feed-the-hamsters/description/?envType=company&envId=geico&favoriteSlug=geico-all
class Solution:
    def minimumBuckets(self, hamsters: str) -> int:
        ans = 0
        #we first try to place bucket to the right of the current hamster, as if there is a hamster at i+=2 then they will share, and we will be able to reduce the required buckets
        #if not then we try to place the bucket to the left of the hamster
        #if we still do not have empty spaces to the left (i-1) and right(i+1) of the hamster we return a -1
        #keep on incrementing ans
        #return ans
        N = len(hamsters)
        i = 0
        while i < N:
            if hamsters[i] == 'H':
                if i + 1 < N and hamsters[i + 1] == '.':
                    ans+=1
                    i+=3
                elif i > 0 and hamsters[i - 1] == '.':
                    ans+=1
                    i+=1
                else:
                    return -1
            else:
                i+=1
        return ans
