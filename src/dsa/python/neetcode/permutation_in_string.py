#link: https://neetcode.io/problems/permutation-string/question
class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        M,N = len(s1), len(s2)
        if M > N:
            return False
        #build a freq map for s1
        f1 = {}
        f2={}
        for i in range(M):
            if s1[i] in f1:
                f1[s1[i]]+=1
            else:
                f1[s1[i]]=1
        for i in range(M):
            if s2[i] in f2:
                f2[s2[i]]+=1
            else:
                f2[s2[i]]=1
        if f1 == f2:
            return True
        #build a freq map for s2 too
        
        for i in range(M, N):
            if s2[i] in f2:
                f2[s2[i]]+=1
            else:
                f2[s2[i]]=1
            to_remove = i - M
            
            f2[s2[to_remove]]-=1
            if f2[s2[to_remove]] == 0:
                del f2[s2[to_remove]]
            if f1 == f2:
                return True
        #slide over s2, while maintaining a fixed window of size = len(s1)
            #we continue adding characters at the right i.e. at index i, updating frequency
            #remove character at index i - k while updating frequency, and removing those keys who value == 0
            #after this if the frequency maps for both s1 and s2 match, we immediately return True
        # else return False
        return False
