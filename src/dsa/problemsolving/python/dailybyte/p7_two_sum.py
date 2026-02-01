'''
Given an array of integers, return whether or not two numbers sum to a given target, k.
Note: you may not sum a number with itself.

Ex: Given the following...

[1, 3, 8, 2], k = 10, return true (8 + 2)
[3, 9, 13, 7], k = 8, return false
[4, 2, 6, 5, 2], k = 4, return true (2 + 2)
'''


def solve(nums, k) -> bool:
    mp = {}
    for i in range(len(nums)):
        if k - nums[i] in mp:
            return True
        else:
            mp[nums[i]] = i
    return False


def main():
    assert solve([1, 3, 8, 2], 10) is True
    assert solve([3, 9, 13, 7], 8) is False
    assert solve([4, 2, 6, 5, 2], 4) is True



if __name__ == '__main__':
    main()
