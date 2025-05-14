package LeetCode3.MatrixTraversal;

import java.util.*;

public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        // Step 1: Initialize the queue with all rotten oranges
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0) return 0; // No fresh oranges

        int minutes = 0;
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}}; // 4-directional BFS

        // Step 2: BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottenThisRound = false;

            for (int i = 0; i < size; i++) {
                int[] orange = queue.poll();
                int x = orange[0], y = orange[1];

                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];

                    // If neighbor is fresh, rot it
                    if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && grid[nx][ny] == 1) {
                        grid[nx][ny] = 2;
                        queue.offer(new int[]{nx, ny});
                        fresh--;
                        rottenThisRound = true;
                    }
                }
            }

            // Only increment time if we rotted any orange this minute
            if (rottenThisRound) minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        RottingOranges solver = new RottingOranges();
        int[][] grid = {
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };
        System.out.println("Minutes to rot all: " + solver.orangesRotting(grid)); // Output: 4
    }
}