from typing import List, Dict
'''
This question is asked by Amazon. Given two strings representing sentences, return the words that are not common to both strings (i.e. the words that only appear in one of the sentences). You may assume that each sentence is a sequence of words (without punctuation) correctly separated using space characters.

Ex: given the following strings...

sentence1 = "the quick", sentence2 = "brown fox", return ["the", "quick", "brown", "fox"]
sentence1 = "the tortoise beat the haire", sentence2 = "the tortoise lost to the haire", return ["beat", "to", "lost"]
sentence1 = "copper coffee pot", sentence2 = "hot coffee pot", return ["copper", "hot"]

'''
def solve(s1: str, s2: str) -> List[str]:
    freq_map = {}
    s1_arr = s1.split(" ")
    for w in s1_arr:
        if w in freq_map:
            freq_map[w]+=1
        else:
            freq_map[w]=1
    s2_arr = s2.split(" ")
    ans=[]
    for w in s2_arr:
        if w in freq_map:
            freq_map[w]+=1
        else:
            freq_map[w]=1
    for k, v in freq_map.items():
        if v == 1:
            ans.append(k)

    return ans


def main():
    assert solve("the quick", "brown fox")== ["the", "quick", "brown", "fox"]


if __name__ == '__main__':
    main()
