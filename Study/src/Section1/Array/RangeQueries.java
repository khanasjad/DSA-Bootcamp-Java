package Section1.Array;

public class RangeQueries {
    private int[] prefixSum;

    public RangeQueries(int[] arr) {
        int n = arr.length;
        prefixSum = new int[n + 1];

        // Calculate prefix sum
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i - 1];
        }
    }

    public int rangeSumQuery(int left, int right) {
        // Calculate the sum in the range [left, right]
        return prefixSum[right + 1] - prefixSum[left];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        // Create a RangeQueries object
        RangeQueries rangeQueries = new RangeQueries(arr);

        // Perform range sum queries
        int sum1 = rangeQueries.rangeSumQuery(1, 3);
        int sum2 = rangeQueries.rangeSumQuery(0, 4);

        // Print the results
        System.out.println("Sum in range [1, 3]: " + sum1);
        System.out.println("Sum in range [0, 4]: " + sum2);
    }
}
