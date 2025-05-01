package LeetCode3.HeapPriortyQueues;

import java.util.PriorityQueue;

public class KthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        // Step 1: Create a min-heap (smallest element on top)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Step 2: Add all numbers to the heap
        for (int num : nums) {
            minHeap.offer(num); // Add number
            System.out.println("Inserted: " + num + ", Heap: " + minHeap);

            // Step 3: Maintain size k
            if (minHeap.size() > k) {
                int removed = minHeap.poll(); // Remove smallest
                System.out.println("Removed smallest: " + removed + ", Heap after removal: " + minHeap);
            }
        }

        // Step 4: Top of heap is the kth largest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        KthLargestElement solver = new KthLargestElement();

        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println("Kth Largest Element: " + solver.findKthLargest(nums, k)); // Output: 5

        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        k = 4;
        System.out.println("Kth Largest Element: " + solver.findKthLargest(nums2, k)); // Output: 4
    }
}