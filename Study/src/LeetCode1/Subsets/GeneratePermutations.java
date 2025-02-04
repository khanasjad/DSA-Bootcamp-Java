package LeetCode1.Subsets;
import java.util.*;
public class GeneratePermutations {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println("Permutations: " + permute(nums));
        
    }

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generatePermutations(nums, 0, result);
        return result;
    }

    private static void generatePermutations(int[] nums, int index, List<List<Integer>> result) {
       if (index==nums.length){
           List<Integer> permutaions = new ArrayList<>();
           for (int num:nums){
               permutaions.add(num);
           }

           result.add(permutaions);
           return;
       }

        for(int i= index;i<nums.length;i++){
            swap(nums,i,index); //Moves an element to a new position
            generatePermutations(nums,index+1,result); //Generates all permutations of remaining elements
            swap(nums,i,index); //Restores original order (Backtracking)
        }


    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
