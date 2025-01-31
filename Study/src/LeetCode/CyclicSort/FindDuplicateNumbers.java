package LeetCode.CyclicSort;
import java.util.*;
public class FindDuplicateNumbers {
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println("Duplicate Numbers: " + findDuplicates(nums)); // Output: [2, 3]
    
    }
    private static void swap(int[] arr, int i, int correctIndex) {
        int temp = arr[i];
        arr[correctIndex] = arr[i];
        arr[i] =temp;
    }

    private static List<Integer> findDuplicates(int[] nums) {
       List<Integer> duplicates = new ArrayList<>();
        int i = 0; int n =nums.length;
        while(i<n){
            int correctIndex = nums[i]-1;
            if(nums[correctIndex] != nums [i]){
                swap (nums,i,correctIndex);
            }else {
                i++;
            }
        }





        for(int j=0;j<nums.length;j++){
            if(j != nums[j]){
               duplicates.add(nums[j]);
            }
        }
      return duplicates;
    }
}
