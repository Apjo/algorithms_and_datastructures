"""
Filename: lru_cache.py
Date: 2026-10-06
"""

import os
import time
import pandas as pd
import numpy as np
import heapq
import math
import collections
from typing import Optional, List
import random
from collections import deque, defaultdict, Counter


"""
cache eviction: pop least recently used items

get/put should happen in O(1)
for fast lookups use a dict 
where does removal happen from?
from front or from back/taik? go with removing the tail, and always add back to the fron the least recently used.
so, we will have most recently used item at the head, and least recently used at the tail

several options of what the value could look like:
1. could be a singly linked list, helps in adding O(1), but removal is costly
2. could use a predefined sized array, but again size is an obstacle, and so is removal
3. could consider using doubly linked list, costly in size as we now need to remember prev, and next, but removal is O(1)

items = [vanilla, chocolate, strawberry]
and say our cache size is 3
input is vanilla, 10 our cache is empty, add it.
cache = {"vanilla": [[-999]-[vanilla,10]-[999]]}
next comes in to add "chocolate", key isn't present so we can add it.
cache = {"vanilla": [[-999]-[vanilla,10]-[999]], "chocolate":[[-999]-[chocolate,20]-[999]]}
next comes in get chocolate
-check if chocolate key exists, if not return none
-else if the key exists:
    -get the existing node for that key
    -unlink the chocolate node
    -append to head
    -return its value
next comes in as "vanilla, 15" to update 
-check if vanilla key exists, if not return none
-else if the key exists:
    -get the existing node for that key
    -update the corresponding value for this node
    -unlink the vanilla node
    -append to head
    -return 
next input comes in adding "strawberry, 5"
-check if the cache contains the key, if it does 
"""

class DLLNode:
    def __init__(self, key, val):
        self.next = None
        self.prev = None
        self.key = key
        self.value = val

class LRU:
    def __init__(self, init_capacity: int):
        self._cap = init_capacity
        self._head = DLLNode(-999, -999)
        self._tail = DLLNode(-999, -999)
        self._lru_cache = {}
        self._head.next = self._tail
        self._tail.prev = self._head
        
    #private for manipulating the DLLs
    
    def _unlink(self, node: DLLNode):
        #save the prev, and next pointers of this node that needs to be removed
        temp_prev = node.prev
        temp_next = node.next
        temp_prev.next = temp_next
        temp_next.prev = temp_prev

    def _add_to_head(self, node: DLLNode):
        #save current heads next as temp_h_next
        #make node's next point to this temp_h_next
        #make node's prev point to head
        #make head point to this new node "node"
        #make temp_h_next prev point to this node
        temp_h_next = self._head.next
        node.next = temp_h_next
        node.prev = self._head
        self._head.next = node
        temp_h_next.prev = node

    def _remove_tail(self):
        curr_tail = self._tail.prev
        self._unlink(curr_tail)
        return curr_tail



    #public methods
    def get(self, key: int) -> int:
        #if the key is missing return -1
        """
        if key is present:
            -get the corresponding node
            -unlink it from wherever its located
            -move to the head
            -capture the corresponding value for that key
            -return the value
        else:
            -return -1
        """
        if key in self._lru_cache:
            curr_node = self._lru_cache[key]
            curr_val = curr_node.value
            self._unlink(curr_node)
            self._add_to_head(curr_node)
            
            return curr_val

        else:
            return -1
    
    def put(self, key: int, val: int) -> None:
        """
        if key is present:
            -get the corresponding node
            -unlink it from wherever its located
            -move to the head
            -update its corresponding value
            -return
        else:
            -create a new DLL node
            - add it to the cache:
                -if the size of cache now  > capacity:
                    -remove the tail end node of this key
                    -del from cache
            -return
        """

        if key in self._lru_cache:
            curr_node = self._lru_cache[key]
            curr_node.value = val

            self._unlink(curr_node)
            self._add_to_head(curr_node)

            return

        else:
            new_node = DLLNode(key, val)
            self._lru_cache[key] = new_node
            if len(self._lru_cache) > self._cap:
                tail_to_remove = self._remove_tail()
                del self._lru_cache[tail_to_remove.key]
        return


if __name__ == '__main__':
    Solution().solve()