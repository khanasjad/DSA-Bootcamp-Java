package LeetCode2.BFS;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0; // End word must be in the dictionary.
        }

        // Preprocessing: Build a map of all intermediate states to all words which have that state.
        Map<String, List<String>> allComboDict = new HashMap<>();
        int wordLen = beginWord.length();

        for (String word : wordSet) {
            for (int i = 0; i < wordLen; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        }

        System.out.println(allComboDict);

        // Queue for BFS: store pairs of (currentWord, level)
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));

        // Visited to make sure we don't repeat processing a word.
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            Pair node = queue.poll();
            String currentWord = node.word;
            int level = node.level;

            for (int i = 0; i < wordLen; i++) {
                String intermediate = currentWord.substring(0, i) + "*" + currentWord.substring(i + 1);

                List<String> adjacentWords = allComboDict.getOrDefault(intermediate, new ArrayList<>());
                for (String adjacentWord : adjacentWords) {
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.contains(adjacentWord)) {
                        visited.add(adjacentWord);
                        queue.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }

    // Helper class to hold the word and the level (transformation count)
    private class Pair {
        String word;
        int level;
        Pair(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }

    // Example usage:
    public static void main(String[] args) {
        WordLadder solution = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int result = solution.ladderLength(beginWord, endWord, wordList);
        System.out.println("Length of shortest transformation: " + result);
    }
}
