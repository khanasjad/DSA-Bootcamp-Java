package LeetCode4.GraphTraversal;

// ===== LeetCode 743: Network Delay Time =====
// PROBLEM: You have a network of n nodes labeled 1 to n.
// Given times (edges with weights) and a signal sent from node k,
// return the time it takes for all nodes to receive the signal.
// If impossible, return -1.

import java.util.*;

class ShortestPathinWeightedGraph {
    public int networkDelayTime(int[][] times, int n, int k) {
        /*
        🎯 SUPER SIMPLE EXPLANATION:
        Think of this like WiFi spreading through a building:
        - You turn on router at room K
        - WiFi spreads to nearby rooms with different delays
        - Question: "When does the LAST room get WiFi?"

        Example: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
        Translation:
        - From room 2 → room 1 takes 1 second
        - From room 2 → room 3 takes 1 second
        - From room 3 → room 4 takes 1 second
        */

        // Step 1: Build the graph (adjacency list)
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int weight = time[2];
            graph.get(from).add(new int[]{to, weight});
        }

        // Step 2: Dijkstra's Algorithm using Priority Queue
        // PriorityQueue stores: [node, shortest_time_to_reach_this_node]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0}); // Start at node k with time 0

        // Track shortest time to reach each node
        Map<Integer, Integer> shortestTimes = new HashMap<>();

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int timeToReachNode = current[1];

            // Skip if we already found shorter path to this node
            if (shortestTimes.containsKey(node)) continue;

            // This is the shortest time to reach this node!
            shortestTimes.put(node, timeToReachNode);

            // Update times for all neighbors
            for (int[] edge : graph.get(node)) {
                int neighbor = edge[0];
                int travelTime = edge[1];

                // Only add to queue if we haven't found shortest path to neighbor yet
                if (!shortestTimes.containsKey(neighbor)) {
                    pq.offer(new int[]{neighbor, timeToReachNode + travelTime});
                }
            }
        }

        // Step 3: Check if all nodes are reachable
        if (shortestTimes.size() != n) {
            return -1; // Some nodes unreachable
        }

        // Step 4: Return the maximum time (when last node gets signal)
        return Collections.max(shortestTimes.values());
    }

    // ===== STEP-BY-STEP TRACE FOR EXAMPLE =====
    public static void main(String[] args) {
        ShortestPathinWeightedGraph sol = new ShortestPathinWeightedGraph();

        // Test case: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int result = sol.networkDelayTime(times, 4, 2);

        System.out.println("Answer: " + result); // Should print 2

        /*
        🔍 MANUAL TRACE:

        Graph looks like:
        2 → 1 (cost 1)
        2 → 3 (cost 1)
        3 → 4 (cost 1)

        Dijkstra's execution:

        Initial: pq = [(2, 0)]  // Start at node 2 with time 0

        Step 1: Poll (2, 0)
        - shortestTimes = {2: 0}
        - Add neighbors: pq = [(1, 1), (3, 1)]

        Step 2: Poll (1, 1)  // Node 1 with time 1
        - shortestTimes = {2: 0, 1: 1}
        - Node 1 has no outgoing edges
        - pq = [(3, 1)]

        Step 3: Poll (3, 1)  // Node 3 with time 1
        - shortestTimes = {2: 0, 1: 1, 3: 1}
        - Add neighbor: pq = [(4, 2)]  // 1 + 1 = 2

        Step 4: Poll (4, 2)  // Node 4 with time 2
        - shortestTimes = {2: 0, 1: 1, 3: 1, 4: 2}
        - pq = []

        Final: All nodes reached! Times = [0, 1, 1, 2]
        Maximum time = 2 ← Answer!
        */

        System.out.println("\n🎯 Why the answer is 2:");
        System.out.println("Signal starts at node 2 (time 0)");
        System.out.println("├─ Reaches node 1 at time 1");
        System.out.println("├─ Reaches node 3 at time 1");
        System.out.println("└─ Reaches node 4 at time 2 (through 2→3→4)");
        System.out.println("Last node gets signal at time 2!");
    }
}

/*
🧠 KEY INSIGHTS FOR INTERVIEWS:

1. **WHY DIJKSTRA'S?**
   - Weighted edges (different travel times)
   - Need shortest path from one source to all nodes
   - No negative weights

2. **WHY PRIORITY QUEUE?**
   - Always process closest node first
   - Guarantees we find optimal paths in correct order

3. **WHY CHECK IF VISITED?**
   - Once we've found shortest path to a node, we never need to update it again
   - Dijkstra's guarantees the first time we reach a node is optimal

4. **COMMON MISTAKES:**
   - Forgetting to check if all nodes are reachable
   - Using wrong data structure (array vs Map for sparse graphs)
   - Not handling 1-indexed vs 0-indexed nodes

5. **FOLLOW-UP QUESTIONS:**
   - "What if there are negative weights?" → Use Bellman-Ford
   - "What's the time complexity?" → O((V + E) log V)
   - "Can you optimize space?" → Use arrays instead of HashMap if nodes are consecutive

🚀 INTERVIEW SUCCESS TIP:
Practice explaining this algorithm out loud! Say:
"I'll use Dijkstra's algorithm because we have weighted edges and need shortest paths.
The key insight is always processing the closest unvisited node first."
*/