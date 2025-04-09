package LeetCode2.DP;

import java.util.Arrays;

public class Knapsack {


    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        System.out.println("n ;"+n);

        // Step 1: Create a 2D dp table
        int[][] dp = new int[n + 1][capacity + 1];

        // Step 2: Fill the table row by row (items), column by column (capacity)
        for (int i = 1; i <= n; i++) {
            int wt = weights[i - 1];  // weight of current item
            int val = values[i - 1];  // value of current item

            for (int w = 0; w <= capacity; w++) {
                if (wt <= w) {
                    // We can include the item: max of including or excluding
                    dp[i][w] = Math.max(
                            dp[i - 1][w],                // Don't include item
                            val + dp[i - 1][w - wt]      // Include item
                    );
                } else {
                    // Can't include the item, copy the previous value
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Step 3: Answer is at dp[n][capacity]
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int[] weights = {1, 3, 4, 5};
        int[] values  = {1, 4, 5, 7};
        int capacity = 7;

        int maxValue = knapsack(weights, values, capacity);
        System.out.println("Maximum value in knapsack: " + maxValue);
    }
}