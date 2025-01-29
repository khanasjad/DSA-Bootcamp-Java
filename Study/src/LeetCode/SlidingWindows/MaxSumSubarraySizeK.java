package LeetCode.SlidingWindows;

public class MaxSumSubarraySizeK {
    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println(maxSumSubarray(nums, k)); // Output: 9
    }

    private static int maxSumSubarray(int[] nums, int k) {
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
         for (int i= 0; i<k;i++){
             windowSum  += nums[i];
         }

         maxSum =  windowSum;

         for(int j = k ; j< nums.length;j++){
             windowSum +=  nums[j] - nums[j-k];

             maxSum = Math.max(maxSum,windowSum);
         }

        return maxSum;
    }
}
