package dsa.java.neetcode;

import java.util.HashMap;
import java.util.Map;
/*
Design a HashSet without using any built-in hash table libraries.

Implement MyHashSet class:

void add(key) Inserts the value key into the HashSet.
bool contains(key) Returns whether the value key exists in the HashSet or not.
void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.

 */
//date:07/23/2025
public class Problem9 {
    class MyHashSet {
        Map<Integer, Integer> hs;
        Integer timeT;
        public MyHashSet() {
            this.timeT=0;
            hs = new HashMap<>();
        }

        public void add(int key) {
            if (!hs.containsKey(key)) {
                hs.put(key, this.timeT+1);
            }
        }

        public void remove(int key) {
            if (hs.containsKey(key)) {
                hs.remove(key);
            }
        }

        public boolean contains(int key) {
            return hs.containsKey(key);
        }
    }
    public static void main(String[] args) {
    }
}
