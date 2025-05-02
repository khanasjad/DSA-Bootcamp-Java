package LeetCode3.KadanesAlgo;

public class MaximumCircularSubarray {

    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int currMax = nums[0], maxSum = nums[0];
        int currMin = nums[0], minSum = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            total += n;

            // Kadane for max subarray (standard)
            currMax = Math.max(n, currMax + n);
            maxSum = Math.max(maxSum, currMax);

            // Kadane for min subarray (used in wrap case)
            currMin = Math.min(n, currMin + n);
            minSum = Math.min(minSum, currMin);

            // Debug info
            System.out.println("i: " + i + ", n: " + n + ", currMax: " + currMax + ", maxSum: " + maxSum + ", currMin: " + currMin + ", minSum: " + minSum);
        }

        // Edge case: all numbers are negative
        if (maxSum < 0) return maxSum;

        return Math.max(maxSum, total - minSum);
    }

    public static void main(String[] args) {
        MaximumCircularSubarray solver = new MaximumCircularSubarray();

        int[] nums1 = {1, -2, 3, -2};
        System.out.println("Max Circular Sum: " + solver.maxSubarraySumCircular(nums1)); // 3

        int[] nums2 = {5, -3, 5};
        System.out.println("Max Circular Sum: " + solver.maxSubarraySumCircular(nums2)); // 10

        int[] nums3 = {-3, -2, -3};
        System.out.println("Max Circular Sum: " + solver.maxSubarraySumCircular(nums3)); // -2
    }
}