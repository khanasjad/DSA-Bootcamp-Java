package LeetCode3.KadanesAlgo;

public class MaximumSubarrayKadane {

    public int maxSubArray(int[] nums) {
        int currentMax = nums[0]; // Max sum ending here
        int globalMax = nums[0];  // Max sum seen so far

        for (int i = 1; i < nums.length; i++) {
            // Decide: extend previous sum OR start fresh from current element
            currentMax = Math.max(nums[i], currentMax + nums[i]);

            // Update global max if current one is better
            globalMax = Math.max(globalMax, currentMax);

            // Debug output
            System.out.println("i: " + i + ", currentMax: " + currentMax + ", globalMax: " + globalMax);
        }

        return globalMax;
    }

    public static void main(String[] args) {
        MaximumSubarrayKadane solver = new MaximumSubarrayKadane();

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("Max Subarray Sum: " + solver.maxSubArray(nums)); // Output: 6
    }
}