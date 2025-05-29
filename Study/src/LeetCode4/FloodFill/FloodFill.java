package LeetCode4.FloodFill;

import java.util.Arrays;

public class FloodFill {

    // Main flood fill function using DFS
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];

        // Avoid unnecessary work if the new color is same as original
        if (originalColor != newColor) {
            dfs(image, sr, sc, originalColor, newColor);
        }

        return image;
    }

    // Recursive DFS function
    private void dfs(int[][] image, int r, int c, int originalColor, int newColor) {
        // Check boundaries and skip if color doesn't match
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length) return;
        if (image[r][c] != originalColor) return;

        // Paint the pixel
        image[r][c] = newColor;

        // Explore 4-directionally
        dfs(image, r + 1, c, originalColor, newColor); // down
        dfs(image, r - 1, c, originalColor, newColor); // up
        dfs(image, r, c + 1, originalColor, newColor); // right
        dfs(image, r, c - 1, originalColor, newColor); // left
    }

    // Main method for testing
    public static void main(String[] args) {
        FloodFill solver = new FloodFill();

        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        int sr = 1, sc = 1; // Starting row and column
        int newColor = 2;

        int[][] filledImage = solver.floodFill(image, sr, sc, newColor);

        System.out.println("Flood-filled Image:");
        for (int[] row : filledImage) {
            System.out.println(Arrays.toString(row));
        }
    }
}