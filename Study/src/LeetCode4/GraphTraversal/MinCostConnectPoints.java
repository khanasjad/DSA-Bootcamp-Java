package LeetCode4.GraphTraversal;

import java.util.*;

public class MinCostConnectPoints {

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        // Step 1: Create all possible edges with their cost
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) +
                        Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{i, j, dist});
            }
        }

        // Step 2: Sort edges by weight
        edges.sort(Comparator.comparingInt(a -> a[2]));

        // Step 3: Kruskal's using Union Find
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int mstCost = 0, edgesUsed = 0;

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], cost = edge[2];

            if (find(parent, u) != find(parent, v)) {
                union(parent, u, v);
                mstCost += cost;
                edgesUsed++;
                if (edgesUsed == n - 1) break;
            }
        }

        return mstCost;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]); // path compression
        return parent[x];
    }

    private void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y);
    }

    // ✅ Main method to test
    public static void main(String[] args) {
        MinCostConnectPoints solver = new MinCostConnectPoints();
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        int cost = solver.minCostConnectPoints(points);
        System.out.println("Minimum cost to connect all points: " + cost);
    }
}
