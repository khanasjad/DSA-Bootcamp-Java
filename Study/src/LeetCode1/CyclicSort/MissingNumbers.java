package LeetCode1.CyclicSort;

public class MissingNumbers {
    public static void main(String[] args) {
        int[] nums = {3, 0, 1};
        System.out.println("Missing Number: " + findMissingNumber(nums)); // Output: 2
    }

    private static int findMissingNumber(int[] nums) {
        int i = 0; int n =nums.length;

        while(i<n){
            int correctIndex = nums[i];
            if(nums[i] < n && nums[correctIndex] != nums [i]){
                swap (nums,i,correctIndex);
            }else {
                i++;
            }
        }

        for(int j=0;j<n;j++){
            if(j != nums[j]){
                return j;
            }
        }




        return -1;
    }

    private static void swap(int[] arr, int i, int correctIndex) {
        int temp = arr[i];
        arr[correctIndex] = arr[i];
        arr[i] =temp;
    }
}
