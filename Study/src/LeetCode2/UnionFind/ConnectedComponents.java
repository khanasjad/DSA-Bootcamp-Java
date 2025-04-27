package LeetCode2.UnionFind;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponents {
    public static void main(String[] args) {
        ConnectedComponents cc = new ConnectedComponents();

        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println("Output: " + cc.countComponents(5, edges1)); // ➜ 2

        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println("Output: " + cc.countComponents(5, edges2)); // ➜ 1
    }
    public int countComponents(int n, int[][] edges) {
        // Step 1: Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());  // Initialize list for each node
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);  // Because it's undirected
        }

        // Step 2: Track visited nodes
        boolean[] visited = new boolean[n];
        int components = 0;

        // Step 3: DFS on each unvisited node
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                System.out.println("Starting new DFS from node: " + i);
                dfs(graph, visited, i);
                components++;  // Found a new component
            }
        }


        return components;
    }

    private void dfs(List<List<Integer>> graph, boolean[] visited, int node) {
        visited[node] = true;
        System.out.println("Visited node: " + node);

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(graph, visited, neighbor);
            }
        }
    }

}
