package LeetCode3.PrefixSum;


import java.util.HashMap;

public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        HashMap<Integer, Integer> prefixMap = new HashMap<>();

        // Base case: empty prefix sum
        prefixMap.put(0, 1);

        for (int num : nums) {
            currentSum += num;

            // Check if (currentSum - k) exists in map
            if (prefixMap.containsKey(currentSum - k)) {
                count += prefixMap.get(currentSum - k);
            }

            // Add currentSum to map
            prefixMap.put(currentSum, prefixMap.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        SubarraySumEqualsK solver = new SubarraySumEqualsK();
        int[] nums = {1, 2, 3};
        int k = 3;
        System.out.println("Subarrays summing to k: " + solver.subarraySum(nums, k)); // Output: 2
    }
}