package LeetCode4.FloodFill;
import java.util.*;

public class NumberOfEnclaves {

    // Main function to calculate number of enclaves
    public int numEnclaves(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Step 1: Eliminate all land cells connected to border
        for (int r = 0; r < rows; r++) {
            dfs(grid, r, 0);           // Left border
            dfs(grid, r, cols - 1);    // Right border
        }
        for (int c = 0; c < cols; c++) {
            dfs(grid, 0, c);           // Top border
            dfs(grid, rows - 1, c);    // Bottom border
        }

        // Step 2: Count the remaining 1s (enclaves)
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) count++;
            }
        }

        return count;
    }

    // DFS to turn connected land into water
    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != 1) {
            return;
        }

        grid[r][c] = 0;  // Mark visited (sink the land)

        dfs(grid, r + 1, c);  // down
        dfs(grid, r - 1, c);  // up
        dfs(grid, r, c + 1);  // right
        dfs(grid, r, c - 1);  // left
    }

    // ✅ Main method to test the code
    public static void main(String[] args) {
        NumberOfEnclaves solver = new NumberOfEnclaves();

        int[][] grid = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };

        int result = solver.numEnclaves(grid);
        System.out.println("Number of Enclaves: " + result); // Output: 3
    }
}