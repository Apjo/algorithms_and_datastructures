package dsa.java.datastructures.trees;

import java.util.HashMap;
import java.util.Map;

//LC#208: https://leetcode.com/problems/implement-trie-prefix-tree/

/**
 * A good solution: https://leetcode.com/problems/implement-trie-prefix-tree/discuss/58832/AC-JAVA-solution-simple-using-single-array/60221
 *
 * Time to build a trie: O(L*N), L being the average len of the word, and if we are given N words
 * search: O(length of word)
 * delete: O(length of word)
 */
public class MyTrie {
    private class TrieNode {
        Map<Character, TrieNode> children; // this can also be TrieNode[] children
        boolean endOfWord;
        public TrieNode() {
            children = new HashMap<>(); //children = new TrieNode[26] for english alphabet letters
            endOfWord = false;
        }
    }
    private final TrieNode root;
    /** Initialize your data structure here. */
    public MyTrie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            TrieNode existing =  current.children.get(letter); //children[letter - 'a'] would yield the TrieNode at c-'a'
            if (existing == null) {
                existing = new TrieNode();
                current.children.put(letter, existing);//children[c-'a']=new TrieNode()
            }
            current = existing;
        }
        current.endOfWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            TrieNode existing =  current.children.get(letter);
            if (existing == null) {
                return false;
            }
            current = existing;
        }
        return current.endOfWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char letter = prefix.charAt(i);
            TrieNode existing =  current.children.get(letter);
            if (existing == null) {
                return false;
            }
            current = existing;
        }
        return true;

    }

}
