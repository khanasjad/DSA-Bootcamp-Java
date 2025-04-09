package LeetCode2.DP;

import java.util.Arrays;

public class LongestIncreasingSequence {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of LIS: " + lengthOfLIS(nums));
    }

    private static int lengthOfLIS(int[] nums) {
        int LIS  = 1;
        int n = nums.length;
        int[] dp  =new int[n];
        Arrays.fill(dp,1);

        for (int i = 1;i<n;i++){
            for (int j = 0;j<i ;j++){
                if (nums[j] <nums[i]){
                    dp[i] = Math.max(dp[i],dp [j]+1);

                }
                LIS = Math.max(LIS,dp [i]);
            }
        }

        return LIS;
    }
}
