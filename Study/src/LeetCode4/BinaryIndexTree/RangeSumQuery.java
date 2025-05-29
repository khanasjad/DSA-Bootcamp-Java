package LeetCode4.BinaryIndexTree;

import java.util.*;

public class RangeSumQuery {
    private int[] bit;   // Binary Indexed Tree
    private int[] nums;  // Original array
    private int n;

    // Constructor to initialize BIT and array
    public RangeSumQuery(int[] nums) {
        this.n = nums.length;
        this.nums = new int[n];
        this.bit = new int[n + 1];  // BIT is 1-indexed

        // Initialize BIT with given array
        for (int i = 0; i < n; i++) {
            update(i, nums[i]);
        }
    }

    // Add delta to BIT (index is 1-based)
    private void add(int i, int delta) {
        i += 1;
        while (i <= n) {
            bit[i] += delta;
            i += i & -i;
        }
    }

    // Update nums[index] to new value and update BIT
    public void update(int index, int val) {
        int delta = val - nums[index];
        nums[index] = val;
        add(index, delta);
    }

    // Get prefix sum from index 0 to i
    private int prefixSum(int i) {
        i += 1;
        int sum = 0;
        while (i > 0) {
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }

    // Get sum from left to right (inclusive)
    public int sumRange(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    // ✅ Main method to test the implementation
    public static void main(String[] args) {
        int[] arr = {1, 3, 5};
        RangeSumQuery numArray = new RangeSumQuery(arr);

        System.out.println("Initial sumRange(0, 2): " + numArray.sumRange(0, 2)); // 1+3+5 = 9

        numArray.update(1, 2); // arr becomes [1, 2, 5]

        System.out.println("After update(1, 2), sumRange(0, 2): " + numArray.sumRange(0, 2)); // 1+2+5 = 8

        System.out.println("sumRange(1, 2): " + numArray.sumRange(1, 2)); // 2+5 = 7
    }
}