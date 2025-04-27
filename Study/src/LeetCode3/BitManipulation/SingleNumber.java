package LeetCode3.BitManipulation;

public class SingleNumber {

    public int singleNumber(int[] nums) {
        int result = 0; // Start with 0 because 0 ^ x = x

        for (int num : nums) {
            result ^= num; // XOR each number
            System.out.println("After XOR with " + num + ", result is: " + result);
        }

        return result; // Single number left
    }

    public static void main(String[] args) {
        SingleNumber solver = new SingleNumber();

        int[] nums1 = {2, 2, 1};
        System.out.println("Single Number is: " + solver.singleNumber(nums1)); // Output: 1

        int[] nums2 = {4, 1, 2, 1, 2};
        System.out.println("Single Number is: " + solver.singleNumber(nums2)); // Output: 4
    }
}