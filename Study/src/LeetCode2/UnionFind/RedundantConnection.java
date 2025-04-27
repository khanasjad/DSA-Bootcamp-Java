package LeetCode2.UnionFind;

import java.util.*;

public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        // Step 1: Graph using adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Step 2: Go through each edge
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            Set<Integer> visited = new HashSet<>();

            // Step 3: If both nodes are already connected, we found the cycle
            if (hasPath(graph, u, v, visited)) {
                return edge;
            }

            // Step 4: Otherwise, add edge to the graph
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return new int[0];
    }

    // Simple DFS to check if a path exists
    private boolean hasPath(Map<Integer, List<Integer>> graph, int src, int dest, Set<Integer> visited) {
        if (!graph.containsKey(src)) {
            return false;
        }
        if (src == dest) {
            return true;
        }
        visited.add(src);

        for (int neighbor : graph.get(src)) {
            if (!visited.contains(neighbor)) {
                if (hasPath(graph, neighbor, dest, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RedundantConnection solver = new RedundantConnection();

        int[][] edges = {{1,2}, {1,3}, {2,3}};
        int[] result = solver.findRedundantConnection(edges);

        System.out.println("Redundant edge: " + Arrays.toString(result)); // Output: [2,3]
    }
}