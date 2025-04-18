package LeetCode2.TopologicalSort;

import java.util.*;

public class AlienDictionary {

    public String alienOrder(String[] words) {
        // 🧱 Step 1: Create graph + in-degree map
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        // ⚙️ Step 1.1: Initialize all characters (nodes)
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        // 🔁 Step 2: Build edges based on word order
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int minLen = Math.min(word1.length(), word2.length());
            boolean foundEdge = false;

            // Compare character by character
            for (int j = 0; j < minLen; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        // Add edge c1 → c2
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                        System.out.println("Edge added: " + c1 + " → " + c2);
                    }
                    foundEdge = true;
                    break;
                }
            }

            // ❗ Edge case: "abc" before "ab" → invalid
            if (!foundEdge && word1.length() > word2.length()) {
                System.out.println("Invalid prefix order: " + word1 + " before " + word2);
                return "";
            }
        }

        // 🛫 Step 3: Topological Sort (Kahn's BFS)
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
                System.out.println("Node with 0 in-degree: " + c);
            }
        }

        StringBuilder order = new StringBuilder();

        while (!queue.isEmpty()) {
            char current = queue.poll();
            order.append(current);
            System.out.println("Processing: " + current);

            for (char neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                System.out.println("Decreased in-degree of " + neighbor + " to " + inDegree.get(neighbor));
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                    System.out.println("Added to queue: " + neighbor);
                }
            }
        }

        // ✅ If we used all characters → valid order
        if (order.length() == inDegree.size()) {
            System.out.println("Valid order found: " + order.toString());
            return order.toString();
        } else {
            System.out.println("Cycle detected — cannot resolve order.");
            return ""; // Cycle exists
        }
    }

    public static void main(String[] args) {
        AlienDictionary solver = new AlienDictionary();

        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println("\nOutput 1: " + solver.alienOrder(words1)); // Expected: "wertf"

        String[] words2 = {"z", "x", "z"};
        System.out.println("\nOutput 2: " + solver.alienOrder(words2)); // Expected: "" (cycle)
    }
}
