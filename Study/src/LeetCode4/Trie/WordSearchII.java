package LeetCode4.Trie;

import java.util.*;

public class WordSearchII {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // when a word ends at this node
    }

    private final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        // Step 1: Build the Trie
        TrieNode root = buildTrie(words);

        // Step 2: Traverse each cell in board
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, r, c, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> result) {
        char ch = board[r][c];

        // Invalid cell or already visited or not in Trie
        if (ch == '#' || node.children[ch - 'a'] == null) return;

        node = node.children[ch - 'a'];
        if (node.word != null) {
            result.add(node.word);     // Found a word
            node.word = null;          // Avoid duplicates
        }

        board[r][c] = '#'; // mark visited

        for (int[] dir : directions) {
            int nr = r + dir[0], nc = c + dir[1];
            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) {
                dfs(board, nr, nc, node, result);
            }
        }

        board[r][c] = ch; // backtrack
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int i = ch - 'a';
                if (node.children[i] == null)
                    node.children[i] = new TrieNode();
                node = node.children[i];
            }
            node.word = word;
        }
        return root;
    }

    // ✅ Main method to test
    public static void main(String[] args) {
        WordSearchII solver = new WordSearchII();
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};

        List<String> found = solver.findWords(board, words);
        System.out.println("Words Found: " + found);  // [oath, eat]
    }
}