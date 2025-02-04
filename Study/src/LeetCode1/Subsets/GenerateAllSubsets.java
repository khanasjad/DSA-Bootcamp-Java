package LeetCode1.Subsets;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllSubsets {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println("Subsets: " + subsets(nums));

    }

    private static List<List<Integer>>  subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(0, nums, new ArrayList<>(), result);
        return result;
    }

    private static void generateSubsets(int index, int[] nums, ArrayList<Integer> current, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Exclude current element
        generateSubsets(index + 1, nums, current, result);

        // Include current element
        current.add(nums[index]);
        generateSubsets(index + 1, nums, current, result);

        // Backtrack (remove last element)
        current.remove(current.size() - 1);
    }
}
