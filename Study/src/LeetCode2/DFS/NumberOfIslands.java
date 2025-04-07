package LeetCode2.DFS;

public class NumberOfIslands {

    public static void main(String[] args) {
        NumberOfIslands solution = new NumberOfIslands();
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        int result = solution.numIslands(grid);
        System.out.println("Number of Islands: " + result); // Output: 3
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int count = 0;
        int rows = grid.length, cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        int rows = grid.length, cols = grid[0].length;

        // Boundary or already visited
        if (i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] != '1') return;

        grid[i][j] = '0'; // Mark visited

        // Visit all 4 directions
        dfs(grid, i + 1, j); // Down
        dfs(grid, i - 1, j); // Up
        dfs(grid, i, j + 1); // Right
        dfs(grid, i, j - 1); // Left
    }
}
