package LeetCode4.BinaryIndexTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountofSmallerNumbersAfterSelf {

    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        // Step 1: Discretize (compress)
        Map<Integer, Integer> ranks = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            ranks.putIfAbsent(sorted[i], i + 1); // 1-indexed BIT
        }

        int n = nums.length;
        int[] bit = new int[n + 2];  // BIT size
        Integer[] result = new Integer[n];

        // Step 2: Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            int rank = ranks.get(nums[i]);
            result[i] = query(bit, rank - 1); // how many smaller before
            update(bit, rank);
        }

        return Arrays.asList(result);
    }

    // Update BIT at index i
    private void update(int[] bit, int i) {
        while (i < bit.length) {
            bit[i]++;
            i += i & -i;
        }
    }

    // Query prefix sum up to index i
    private int query(int[] bit, int i) {
        int sum = 0;
        while (i > 0) {
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }

    // Main method to test
    public static void main(String[] args) {
        CountofSmallerNumbersAfterSelf cs = new CountofSmallerNumbersAfterSelf();
        int[] nums = {5, 2, 6, 1};
        List<Integer> result = cs.countSmaller(nums);
        System.out.println(result); // Output: [2, 1, 1, 0]
    }
}