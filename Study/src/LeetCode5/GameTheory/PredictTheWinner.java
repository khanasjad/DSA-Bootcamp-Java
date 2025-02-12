package LeetCode5.GameTheory;

public class PredictTheWinner {
    public static void main(String[] args) {
        int[] nums1 = {1, 5, 2};
        System.out.println(PredictTheWinner(nums1)); // Output: false

        int[] nums2 = {1, 5, 233, 7};
        System.out.println(PredictTheWinner(nums2)); // Output: true
    }

    public static boolean PredictTheWinner(int[] nums){

        int length = nums.length;
        int dp [] [] = new int[length][length];

        for(int i = 0 ; i < length;i++){
            dp [i][i] = nums[i];
        }

        for(int len= 2;len<=nums.length;len++){
            for(int i =0; i<= length - len;i++){
                int j = i + len-1;
                dp [i][j] = Math.max(nums[i]-dp[i+1][j],nums[j]-dp[i][j-1]);

            }


        }


        return dp[0][length-1]>= 0;
    }


}
