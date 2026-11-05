#link: https://neetcode.io/problems/lru-cache/question

class DLLNode:
    def __init__(self, key, val):
        self.val=val
        self.key=key
        self.prev=None
        self.next=None
    def __repr__(self):
        return f"DLLNode(key='{self.key}', val={self.val})"

class LRUCache:

    def __init__(self, capacity: int):
        self.cap = capacity
        self.my_lru = dict()
        self.head = DLLNode(-999, -999)
        self.tail = DLLNode(-999, -999)
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def unlink(self, nn):
        temp_pre = nn.prev
        temp_nxt = nn.next
        temp_pre.next = temp_nxt
        temp_nxt.prev = temp_pre

    def move_to_head(self, nn):
        temp_h = self.head.next
        nn.next = temp_h
        nn.prev = self.head
        self.head.next = nn
        temp_h.prev = nn

    def remove_from_tail(self):
        temp_t = self.tail.prev
        temp_v = temp_t.key
        self.unlink(temp_t)
        
        return temp_v

    def get(self, key: int) -> int:
        #if the key doesn't exist return -1
        if key not in self.my_lru:
            return -1
        #else, fetch the corresponding value
        curr = self.my_lru[key]
            #unlink it
        self.unlink(curr)
            #move it to head
        self.move_to_head(curr)
            #return the value associated with it
        
        return curr.val
        

    def put(self, key: int, value: int) -> None:
        #if the key does not exist
        if key not in self.my_lru:
            print(f"key={key} not in lru")
            #create the key-value pair
            nn = DLLNode(key, value)
            # add to the lru cache
            self.my_lru[key] = nn
            print(f"created a DLL={nn}, now lru cache={self.my_lru}")
            # if after adding to the cache the size increases
                # remove the node from tail
            if len(self.my_lru) > self.cap:
                print(f"capacity of lru cache exceeds={self.cap}")
                to_remove_key = self.remove_from_tail()
                print(f"removing key={to_remove_key}")
                del self.my_lru[to_remove_key]
            #else, add to the head this newly created node
            self.move_to_head(nn)
        
        else:
            #else, fetch the correspnding value
            existing = self.my_lru[key]
            #update with new value
            existing.val = value
            #unlink,and move to head
            self.unlink(existing)
            self.move_to_head(existing)
        
        return None
