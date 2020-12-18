package datastructures;

import java.util.ArrayList;

//A simple hashtable demo
public class MyHashTableDemo<K, V> {
    private int size;
    private ArrayList<HashNode<K, V>> buckets;
    private int numBuckets;

    class HashNode<K, V> {
        private K key;
        private V value;
        HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashTableDemo() {
        buckets = new ArrayList<>();
        numBuckets = 10;
        size = 0;
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(null);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        int idx = hashCode % numBuckets;
        return idx;
    }

    public V remove(K key) {
        // get bucket index
        int indx = getBucketIndex(key);
        // get head from the index
        HashNode<K, V> head = buckets.get(indx);
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key))
                break;

            prev = head;
            head = head.next;
        }
        if (head == null)
            return null;
        size--;
        if (prev != null)
            prev.next = head.next;
        else
            buckets.set(indx, head.next);
        return head.value;
    }

    public void add(K key, V value) {
        // get bucket index
        int indx = getBucketIndex(key);
        // get head node
        HashNode<K, V> head = buckets.get(indx);
        // check if key is already present, if present update the value
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // else increase the size
        size++;
        head = buckets.get(indx);
        // create a new node with the new key-value
        HashNode<K, V> newNode = new HashNode(key, value);
        // set new node's next to the head
        newNode.next = head;
        // set bucket index to newnode
        buckets.set(indx, newNode);
        // check for load factor threshold, if >= 0.7
        if ((1.0 * size) / numBuckets >= 0.7) {
            ArrayList<HashNode<K, V>> temp = buckets;
            buckets = new ArrayList();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                buckets.add(null);
            for (HashNode<K, V> iter : temp) {
                while (iter != null) {
                    add(iter.key, iter.value);
                    iter = iter.next;
                }
            }
        }
    }

    public V get(K key) {
        int indx = getBucketIndex(key);
        HashNode<K, V> head = buckets.get(indx);
        while (head != null) {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }
        return null;
    }

    public void display() {
        ArrayList<HashNode<K, V>> temp = buckets;
        for (HashNode<K, V> node : temp) {
            while (node != null) {
                System.out.println("key = " + node.key + " value " + node.value);
                node = node.next;
            }
        }
    }

    public static void main(String[] args) {
        MyHashTableDemo<String, Integer> map = new MyHashTableDemo<>();
        map.add("a", 1);
        map.add("b", 2);
        map.add("a", 4);
        map.add("d", 5);
        map.display();
        System.out.println("Current size = " + map.getSize());
        System.out.println("Remove " + map.remove("a"));
        System.out.println("Remove " + map.remove("b"));
        System.out.println("Current size = " + map.getSize());
        map.display();
        System.out.println("Is map empty? " + map.isEmpty());
    }
}
