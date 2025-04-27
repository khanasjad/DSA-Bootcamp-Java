package LeetCode3.DivideAndConquer;

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Step 1: Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0, high = m;

        while (low <= high) {
            // Step 2: Partition nums1 and nums2
            int partition1 = (low + high) / 2;
            int partition2 = (m + n + 1) / 2 - partition1;

            // Step 3: Edge cases
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            System.out.println("Partitions -> nums1: " + partition1 + ", nums2: " + partition2);
            System.out.println("Lefts -> " + maxLeft1 + ", " + maxLeft2);
            System.out.println("Rights -> " + minRight1 + ", " + minRight2);

            // Step 4: Found correct partition
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((m + n) % 2 == 0) {
                    return ((double)Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2;
                } else {
                    return (double)Math.max(maxLeft1, maxLeft2);
                }
            }
            // Step 5: Move binary search
            else if (maxLeft1 > minRight2) {
                high = partition1 - 1; // Move left
            } else {
                low = partition1 + 1; // Move right
            }
        }

        // Invalid input
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays solver = new MedianOfTwoSortedArrays();

        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Median: " + solver.findMedianSortedArrays(nums1, nums2)); // Output: 2.0

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Median: " + solver.findMedianSortedArrays(nums3, nums4)); // Output: 2.5
    }
}
