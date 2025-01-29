package Section1.Array;

public class TwoPointer {
    public static boolean hasPairWithSum(int[] arr, int targetSum) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int currentSum = arr[left] + arr[right];

            if (currentSum == targetSum) {
                // Found a pair with the target sum
                System.out.println("Pair with sum " + targetSum + ": (" + arr[left] + ", " + arr[right] + ")");
                return true;
            } else if (currentSum < targetSum) {
                // Move the left pointer to increase the sum
                left++;
            } else {
                // Move the right pointer to decrease the sum
                right--;
            }
        }

        // No pair found
        System.out.println("No pair found with sum " + targetSum);
        return false;
    }

    public static void main(String[] args) {
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int targetSum = 10;

        hasPairWithSum(sortedArray, targetSum);
    }
}
