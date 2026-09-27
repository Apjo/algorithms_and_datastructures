'''
Given an array of strings, return the longest common prefix that is shared amongst all strings.
Note: you may assume all strings only contain lowercase alphabetical characters.
'''
def solve(ips: list):
    #sort the input list, to give a lexicographically sorted strings
    # set s1=first string, s2=last string
    # set 2 pointers i, and j init to 0 to iterate over the 2 strings, and ans variable to 0
    # while i and j iterate over s1, and s2
        #if at any time s1[i] != s2[j] break
        # else, increment i and j, and the ans variable
    # finally return a substring of s1 from 0 to ans
    i, j, ans= 0,0,0
    ips.sort()
    #print(ips)
    s1 = ips[0]
    #print(s1)
    s2 = ips[-1]
    #print(s2)
    while i < len(s1) and j < len(s2):
        if s1[i] != s2[j]:
            break
        i+=1
        j+=1
        ans+=1
    return s1[0:ans]


def main():
     assert solve(["colorado", "color", "cold"]) == "col"
     assert solve(["dance","dag","danger","damage"]) == "da"


if __name__ == '__main__':
    main()
