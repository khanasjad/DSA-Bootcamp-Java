package LeetCode5.ReservoirSampling;


import java.util.Random;

public class RandomPickIndex {
    private int[] nums;
    private Random random;

    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    /**
     * Picks a random index from the array where the element equals the target.
     * Each occurrence of the target is considered using reservoir sampling.
     */
    public int pick(int target) {
        int result = -1;
        int count = 0; // Count of target occurrences seen so far

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
                // With probability 1/count, choose the current index.
                if (random.nextInt(count) == 0) {
                    result = i;
                }
            }
        }

        return result;
    }

    // Example usage:
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3};  // Example array
        RandomPickIndex solution = new RandomPickIndex(nums);

        // Pick a random index for the target value 3
        int randomIndex = solution.pick(3);
        System.out.println("Randomly chosen index for target 3: " + randomIndex);
    }
}
