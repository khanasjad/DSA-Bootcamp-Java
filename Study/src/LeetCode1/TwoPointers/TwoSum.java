package LeetCode1.TwoPointers;

import java.util.Arrays;

public class TwoSum {

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(numbers, target);
        System.out.println(Arrays.toString(result)); // Output: [1, 2]
    }

    private static int[] twoSum(int[] numbers, int target) {
        int[] sumArray = new int[]{-1,-1};
        int left = 0;
        int right = numbers.length-1;

        while(left < right){
            int totalSum = numbers[left] +numbers[right];
            if(target == totalSum){
                return new int[]{left+1,right+1};
            }
            if (totalSum<target){
                left++;
            }
           else{
                right--;
            }
        }



        return sumArray;
    }
}
