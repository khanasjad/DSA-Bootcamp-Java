package LeetCode3.MatrixTraversal;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Step 1: Fill first row and column with 1s
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        // Step 2: Fill the rest of the table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // The number of ways to reach (i, j)
                // = from above + from left
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        // Bottom-right corner has the answer
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        UniquePaths solver = new UniquePaths();
        System.out.println("Unique paths (3x7): " + solver.uniquePaths(3, 7)); // Output: 28
    }
}