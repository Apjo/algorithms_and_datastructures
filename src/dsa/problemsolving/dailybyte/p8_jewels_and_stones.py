#Given a string representing your stones and another string representing a list of jewels,
# return the number of stones that you have that are also jewels.
def solve(jewels:str, stones:str) -> int:
    # freq1={}
    # freq2={}
    freq={}
    cnt=0
    #store the frq.of chars from the bigger/jewels str
    for char in jewels:
        if char in freq:
            freq[char]+=1
        else:
            freq[char]=1
    # then check for existence of smaller/stones in the larger str
    for char in stones:
        if char in freq:
            cnt+=1
    return cnt


def main():
    assert solve(jewels = "abc", stones = "ac") == 2
    assert solve(jewels = "Af", stones = "AaaddfFf") == 3
    assert solve(jewels = "AYOPD", stones = "ayopd") == 0


if __name__ == '__main__':
    main()
