package LeetCode4.Trie;


import java.util.*;

public class Trie {
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26]; // for a-z
        boolean isEndOfWord = false;
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert word into Trie
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a'; // Map 'a' to 0, 'b' to 1, etc.
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    // Search full word
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndOfWord;
    }

    // Check if prefix exists
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    // Helper: Navigate the Trie
    private TrieNode searchNode(String str) {
        TrieNode current = root;
        for (char ch : str.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }

    // ✅ Main method to test
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");

        System.out.println(trie.search("apple"));    // true
        System.out.println(trie.search("app"));      // true
        System.out.println(trie.search("appl"));     // false
        System.out.println(trie.startsWith("app"));  // true
        System.out.println(trie.startsWith("bat"));  // false
    }
}