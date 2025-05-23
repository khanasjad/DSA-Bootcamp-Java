package LeetCode3.SlidingWindow;

public class MaximumSumSubarrayofSizeK {

    public int maxSum(int[] arr, int k) {
        if (arr.length < k) return 0;

        int windowSum = 0, maxSum = 0;

        // First window
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;

        // Slide the window
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k]; // add next, remove first
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaximumSumSubarrayofSizeK ms = new MaximumSumSubarrayofSizeK();
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("Max sum of subarray of size k = " + ms.maxSum(arr, k)); // Output: 9
    }
}