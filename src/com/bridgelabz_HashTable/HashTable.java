package com.bridgelabz_HashTable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

//A node of chains
public class HashTable<K,V> {
    K key;
    V value;
    final int HashTable;

    // Reference to next node
    HashTable<K, V> next;

    // Parameterized Constructor
    public HashTable(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.HashTable = hashCode;
    }
}

// Class to represent entire hash table
 class Map<K, V> {
    // bucketArray is used to store array of chains
    private ArrayList<HashTable<K, V>> bucketArray;

    // Current capacity of array list
    private int numBuckets;

    // Current size of array list
    private int size;

    // Constructor (Initializes capacity, size and
    // empty chains.
    public Map() {
        bucketArray = new ArrayList<>(); // Create arraylist
        numBuckets = 10;
        size = 0;

        // Create empty chains
        for (int i = 0; i < numBuckets; i++)
            bucketArray.add(null);
    }

    private final int hashCode(K key) {
        return Objects.hashCode(key);
    }

    // This implements hash function to find index
    // for a key
    private int getBucketIndex(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % numBuckets;

        // key.hashCode() could be negative. -3 * -1 ==> 3
        index = index < 0 ? index * -1 : index;

        return index; // return bucket number
    }

    // Method to remove a given key
    public void remove(K key) {
        // Apply hash function to find index for given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        // Get head of chain
        HashTable<K, V> head = bucketArray.get(bucketIndex);

        // If key was not there
        if (head == null)
            return;
        else if (head.next == null && head.key.equals(key) && hashCode == head.HashTable) {
            head = null;
            bucketArray.set(bucketIndex, head); // ******** Added Newly
            return;
        }

        HashTable<K, V> temp = head;
        HashTable<K, V> left = temp;
        HashTable<K, V> right = left.next;

        // Delete First
        if (temp.key.equals(key) && hashCode == temp.HashTable) {
            temp = temp.next;
        }

        while (right != null) {

            if (right.key.equals(key) && hashCode == right.HashTable) {
                left.next = right.next;
                break;
            }
            left = left.next;
            right = right.next;
        }

        // Reduce size
        size--;

        bucketArray.set(bucketIndex, temp);

    }

    // Returns value for a key
    public K get(K key) {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);

        HashTable<K, V> head = bucketArray.get(bucketIndex);

        // Search key in chain
        while (head != null) {
            if (head.key.equals(key) && head.HashTable == hashCode)
                return head.key;
            head = head.next;
        }

        // If key not found
        return null;
    }

    // Adds a key value pair to hash
    public void add(K key, V value) {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key); // 1
        int hashCode = hashCode(key);

        HashTable<K, V> head = bucketArray.get(bucketIndex);

        // Check if key is already present
        while (head != null) {
            if (head.key.equals(key) && head.HashTable == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // Insert key in chain
        size++;
        head = bucketArray.get(bucketIndex); // head = null;
        HashTable<K, V> newNode = new HashTable<K, V>(key, value, hashCode);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

    }

    public void display() {

        for (HashTable<K, V> head : bucketArray) {
            HashTable<K, V> temp = head;
            if (head != null)
                System.out.print(" [ " + bucketArray.indexOf(head) + " ]  ==> ");
            else
                System.out.println(" [   ]");
            while (temp != null) {
                System.out.print("[ Key: " + temp.key + " Value: " + temp.value + "] ==>");
                temp = temp.next;
            }
            if (head != null)
                System.out.println();
        }

    }

    // Driver method to test Map class
    public static void main(String[] args) {
        Map<String, Integer> map = new Map<>();
        String input2="Paranoids are not paranoid because they are paranoid but because they keep putting themselves deliberately into paranoid avoidable situations";
        String[] input3=input2.toLowerCase().split(" ");
        for (int i=0; i<input3.length; i++) {
            int freq = 1;
            if (input3[i].equalsIgnoreCase((String) map.get(input3[i]))) {
                freq++;
                map.add(input3[i].toLowerCase(), freq);
            } else {
                map.add(input3[i], freq);
            }
        }
        map.display();
        //Removing  avoidable from the string
        System.out.println("Map After removing the avoidable string");
        map.remove("avoidable");
        map.display();

    }
}
