'''
Given two integer arrays, return their intersection.
Note: the intersection is the set of elements that are common to both arrays.
'''


def find_common(larger, smaller) -> list:
    i=0
    res = set()
    while i < len(larger):
        j=0
        while j < len(smaller):
            if larger[i] == smaller[j]:
                res.add(larger[i])
                i+=1
            j+=1
        i+=1
    return list(res)


def solve(nums1, nums2) -> list:
    nums1.sort()
    nums2.sort()
    if len(nums1) > len(nums2):
        return find_common(nums1, nums2)
    else:
        return find_common(nums2, nums1)



def main():
    assert solve([],[]) == []
    assert solve([],[3]) == []
    assert solve([4,2,2,4],[3,2,4]) == [2,4]
    assert solve([1, 2, 3, 3],[3,3]) == [3]
    assert solve([1, 2, 3, 4],[5,6,7,8]) == []





if __name__ == '__main__':
    main()
