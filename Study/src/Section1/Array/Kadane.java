package Section1.Array;

public class Kadane {
    public static int maxSubArraySum(int[] nums) {
        // Initialize variables
        int maxEndingHere = nums[0];
        int maxSoFar = nums[0];

        // Iterate through the array
        for (int i = 1; i < nums.length; i++) {
            // Calculate the maximum sum subarray ending at index i
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);

            // Update the overall maximum sum subarray
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        // Return the result
        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 2, -1, 2, 90, -1, 39};
        int maxSum = maxSubArraySum(nums);
        System.out.println("Maximum Sum Subarray: " + maxSum);
    }
}
