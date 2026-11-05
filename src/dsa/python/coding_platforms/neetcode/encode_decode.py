import json
from typing import List

class Solution:
    #solution directly involving json
    def encode(self, strs: List[str]) -> str:
    # res=""
        hm={}
        for i in range(len(strs)):
            # print(f"looking at={strs[i]}, i={i}")
            inner_hm={}
            for cc in range(len(strs[i])):
                inner_hm[cc]=strs[i][cc]
            hm[i]=inner_hm
        return json.dumps(hm)

    def decode(self, s: str) -> List[str]:
        res=[]
        hm1 = json.loads(s)
        for k,inner_dict in hm1.items():
            inner_str=""
            for inner_k, inner_v in inner_dict.items():
                inner_str+=inner_v
            res.append(inner_str)
        return res

    #during encoding we attach each string along with its length!, so for every string we write len(str)#string
    #so during decoding we simply read all characters until we reach a # and we find the length of that string, and then read exactly that many characters as the string
    #time:O(m),space:O(m+n) n:len of 1 string,m=sum of len of all strings
    def encode2(self, strs: List[str]) -> str:
        res=""
        for curr_string in strs:
            res+=str(len(curr_string))+"#"+curr_string
        return res

    def decode2(self, s: str) -> List[str]:
        res=[]
        i=0
        while i < len(s):
            j=i
            while s[j] != "#":
                j+=1
            curr_len = int(s[i:j])
            i = j + 1 #string starts after the #
            j = i + curr_len
            res.append(s[i:j])
            i=j

        return res