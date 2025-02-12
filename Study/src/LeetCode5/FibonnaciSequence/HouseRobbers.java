package LeetCode5.FibonnaciSequence;

public class HouseRobbers {

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(rob(nums)); // Output: 12
    }

    private static int rob(int[] nums) {

        return(helper(nums,nums.length-1));
    }

    private static int helper(int[] nums, int i) {
            if (i < 0) return 0;
            return Math.max(helper(nums,i - 1),nums[i] + helper(nums,i-2));



    }

}
