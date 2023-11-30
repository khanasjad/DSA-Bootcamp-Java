package Array;

public class SlidingWindow {
    public static int maxSumSubarray(int[] arr, int k) {
        int n = arr.length;

        // Compute the sum of the first window of size k
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        int maxSum = sum;

        // Slide the window through the array and update maxSum
        for (int i = k; i < n; i++) {
            sum = sum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 1, 7, 8, 1, 2, 8, 1, 0};
        int k = 3;

        int result = maxSumSubarray(arr, k);
        System.out.println("Maximum Sum Subarray of Size " + k + ": " + result);
    }

}
