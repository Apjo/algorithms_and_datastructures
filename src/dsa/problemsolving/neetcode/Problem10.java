package dsa.problemsolving.neetcode;

import java.util.ArrayList;
/*
Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 */
public class Problem10 {
    class HashNode {
        Integer key, value;
        HashNode next;
        public HashNode(int k, int v) {
            this.key = k;
            this.value = v;
            this.next=null;
        }
    }
    class MyHashMap {
        ArrayList<HashNode> buckets;
        private int size;
        private int numBuckets;

        public MyHashMap() {
            this.buckets = new ArrayList<>();
            this.size = 0;
            this.numBuckets = 10;
            for(int i=0; i < numBuckets; i++) {
                buckets.add(null);
            }
        }
        private int getIndex(Integer k) {
            int code = k.hashCode();
            int indx = code % this.numBuckets;
            return indx;
        }
        public void put(int key, int value) {
        /*
        get bucket index
        get head node
        while the head node is not null,check to see if a key already
        if it does, update the corresponding value for it, and return
        Else, increase size by 1
        set head node from getting a node from buckets.get(indx)
        create a new node with the given key/val pair
        set new node's head to head
        update the "indx" inside buckets list to contain this newly created node
        Consider whether or not to resize the table to handle collisions etc.
        */
            int bucketIndex = getIndex(key);
            HashNode head = buckets.get(bucketIndex);
            while(head != null) {
                if (head.key.equals(key)) {
                    head.value=value;
                    return;
                }
                head = head.next;
            }
            this.size++;
            head = buckets.get(bucketIndex);
            HashNode n = new HashNode(key, value);
            n.next=head;
            buckets.set(bucketIndex, n);
            if ((1.0 * size / numBuckets) >= 0.75) {
                ArrayList<HashNode> temp = buckets;
                size=0;
                numBuckets = 2 * numBuckets;
                for(int i=0; i < numBuckets; i++) {
                    buckets.add(null);
                }
                for(HashNode h : temp) {
                    while(h != null) {
                        put(h.key, h.value);
                        h = h.next;
                    }
                }
            }
        }

        public int get(int key) {
        /*
        get bucket index
        get head node
        while the head node is not null,
            check to see if a key already exists, and is equal to input key,
            if it does, return the value
        else return -1
        */
            int bucketIndex = getIndex(key);
            HashNode head = buckets.get(bucketIndex);
            while(head != null) {
                if (head.key.equals(key)) {
                    return head.value;
                }
                head = head.next;
            }
            return -1;
        }

        public void remove(int key) {
            int bucketIndex = getIndex(key);
            HashNode prev = null;
            HashNode head = buckets.get(bucketIndex);
            while(head != null) {
                if (head.key.equals(key)) {
                    break;
                } else {
                    prev=head;
                    head = head.next;
                }
            }
            if(head == null) {
                return;
            }
            size--;
            if (prev != null) {
                prev.next = head.next;
            } else {
                buckets.set(bucketIndex, head.next);
            }
        }
    }
    public static void main(String[] args) {
    }
}
