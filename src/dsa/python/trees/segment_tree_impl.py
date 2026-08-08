# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/11/26
"""

class SegTreeImpl:
    def __init__(self, input_arr):
        self.size = len(input_arr)
        self.tree = [0] * (4 * self.size)
        self.build_tree(input_arr=input_arr, curr_index=1, start=0, end=self.size - 1)
        print("finished building tree")

    def build_tree(self, input_arr, curr_index, start, end):
        if start == end:
            self.tree[curr_index] = input_arr[start]
            return

        mid = (start + end) // 2
        self.build_tree(input_arr, curr_index * 2, start, mid)
        self.build_tree(input_arr, curr_index * 2 + 1, mid + 1, end)
        self.tree[curr_index] = self.tree[2 * curr_index] + self.tree[2 * curr_index + 1]


    def sum_range_query(self, curr_index, start, end, query_st, query_end):
        #complete overlap
        # print(f"Got l={query_st}, r={query_end}, start={start}, end={end}")
        if query_st <= start and query_end >= end:
            # print("complete overlap")
            return self.tree[curr_index]

        #no overlap
        if query_st > end or query_end < start:
            # print("No overlap!")
            return 0

        #partial overlap, recurse
        # print("Partial overlap, recurse!")
        mid = (start + end) // 2
        # print(f"Current mid={mid}")
        p1 = self.sum_range_query((2 * curr_index), start, mid, query_st, query_end)
        # print(f"sum from left={p1}")
        p2 = self.sum_range_query(((2 * curr_index) + 1), mid + 1, end, query_st, query_end)
        # print(f"sum from right={p2}")
        # self.tree[curr_index] = p1 + p2
        return p1 + p2

    def update(self, start, end, curr_index, pos, new_value):
        print("")
        if start == end:
            self.tree[curr_index] = new_value
            return
        mid = (start + end) // 2
        if pos <= mid:
            self.update(start, mid, 2 * curr_index, pos, new_value)
        else:
            self.update(mid + 1, end, 2 * curr_index + 1, pos, new_value)
        self.tree[curr_index] = self.tree[2 * curr_index] + self.tree[2 * curr_index + 1]


def main():
    nums=[1,3,2,-8,7]
    N = len(nums)
    seg_tree = SegTreeImpl(nums)
    # Example 1: Query range sum from index 1 to 4 (inclusive: 3 + 2 - 8 + 7)
    query_sum = seg_tree.sum_range_query(1, 0, N - 1, 1, 4)
    print(f"Sum of elements from index 1 to 4: {query_sum}") # Output: 4
    # Example 2: Update element at index 2 (value 5) to 10
    seg_tree.update(start=1, end=N-1, curr_index=0, pos=2, new_value=10)
    # Query the same range again (3 + 10 + 7 + 9)
    # updated_sum = seg_tree.sum_range_query(curr_index=1, start=0,end=N - 1, query_st=1, query_end=4)
    # print(f"Sum after updating index 2 to 10: {updated_sum}") # Output:



if __name__ == '__main__':
    main()
