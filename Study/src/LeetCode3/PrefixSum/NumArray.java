package LeetCode3.PrefixSum;

public class NumArray {
    private int[] prefix;

    // Step 1: Build prefix sum array
    public NumArray(int[] nums) {
        prefix = new int[nums.length + 1]; // 1 extra for easier math
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    // Step 2: Return range sum in O(1) time
    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }

    public static void main(String[] args) {
        NumArray obj = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(obj.sumRange(0, 2)); // 1
        System.out.println(obj.sumRange(2, 5)); // -1
        System.out.println(obj.sumRange(0, 5)); // -3
    }
}