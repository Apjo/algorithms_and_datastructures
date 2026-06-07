"""
Filename: design_twitter.py
Date: 2026-06-05
"""

import collections
import heapq
from typing import List


class Solution:
    # Using MAX HEAP time and space: O(NlogN), space: O(N)
    class Twitter:
        def __init__(self):
            self.user_to_tweets = collections.defaultdict(list)
            self.user_to_followers = collections.defaultdict(set)
            self.UserTweet = collections.namedtuple("UserTweet", ["ts", "tweetId"])
            self.ts = 0

        def postTweet(self, userId: int, tweetId: int) -> None:
            user_tweet = self.UserTweet(ts=self.ts, tweetId=tweetId)
            self.user_to_tweets[userId].append(user_tweet)
            self.ts += 1

        def getNewsFeed(self, userId: int) -> List[int]:
            h = []
            # get current followers of this user
            curr_followers = self.user_to_followers[userId]
            # dump all of them including this user into a collection
            all_userids = set()
            all_userids.add(userId)
            all_userids.update(curr_followers)

            # for userid in all users:
            # fetch their tweets
            # for userid's post dump into a pq sorted on ts in a max heap
            # if the len of max heap > 10, poll
            for userid in all_userids:
                all_tweets = self.user_to_tweets[userid]
                for tweet in all_tweets:
                    # heapq.heappush(self.min_h, (tweet.ts, tweet.tweetId))
                    heapq.heappush(h, (-tweet.ts, tweet.tweetId))
                    # if len(self.min_h) > 10:
                    # heapq.heappop(self.min_h)
            # once done with all users, the pq will contain all 10 most recent tweetids
            res = []

            # while self.min_h:
            #     priority, tweetid = heapq.heappop(self.min_h)

            #     res.append(tweetid)
            # #return those as a list
            # res.reverse() #O(n) in place

            while h and len(res) < 10:
                _, tweetid = heapq.heappop(h)

                res.append(tweetid)

            return res

        def follow(self, followerId: int, followeeId: int) -> None:
            self.user_to_followers[followerId].add(followeeId)
            return None

        def unfollow(self, followerId: int, followeeId: int) -> None:
            current_followees = self.user_to_followers[followerId]
            if current_followees:
                current_followees.remove(followeeId)
            return None

    # Using MIN HEAP time and space: O(NlogK), space of heap: O(K)
    class Twitter_:
        def __init__(self):
            self.user_to_tweets = collections.defaultdict(list)
            self.user_to_followers = collections.defaultdict(set)
            self.UserTweet = collections.namedtuple("UserTweet", ["ts", "tweetId"])
            self.ts = 0

        def postTweet(self, userId: int, tweetId: int) -> None:
            user_tweet = self.UserTweet(ts=self.ts, tweetId=tweetId)
            self.user_to_tweets[userId].append(user_tweet)
            self.ts += 1

        def getNewsFeed(self, userId: int) -> List[int]:
            h = []
            curr_followers = self.user_to_followers[userId]

            all_userids = set()
            all_userids.add(userId)
            all_userids.update(curr_followers)

            # for userid in all users:
            # fetch their tweets
            # for userid's post dump into a pq sorted on ts in a MIN heap
            # if the len of MIN heap > 10, poll
            for userid in all_userids:
                all_tweets = self.user_to_tweets[userid]
                for tweet in all_tweets:
                    heapq.heappush(h, (tweet.ts, tweet.tweetId))
                    if len(h) > 10:
                        heapq.heappop(h)

            # once done with all users, the pq will contain all 10 most recent tweetids
            res = []
            while h:
                _, tweetid = heapq.heappop(h)
                res.append(tweetid)

            # return those as a list
            res.reverse()  # O(n) in place
            return res

        def follow(self, followerId: int, followeeId: int) -> None:
            self.user_to_followers[followerId].add(followeeId)
            return None

        def unfollow(self, followerId: int, followeeId: int) -> None:
            current_followees = self.user_to_followers[followerId]
            if current_followees:
                current_followees.remove(followeeId)
            return None


# Your Twitter object will be instantiated and called as such:
# obj = Twitter()
# obj.postTweet(userId,tweetId)
# param_2 = obj.getNewsFeed(userId)
# obj.follow(followerId,followeeId)
# obj.unfollow(followerId,followeeId)


if __name__ == "__main__":
    Solution().solve()
