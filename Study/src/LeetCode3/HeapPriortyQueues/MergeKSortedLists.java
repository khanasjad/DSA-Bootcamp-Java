package LeetCode3.HeapPriortyQueues;

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        // Step 1: Create a min-heap based on node value
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Step 2: Add the head of each list into the heap
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
                System.out.println("Inserted node with value: " + node.val);
            }
        }

        // Step 3: Create a dummy head for the result list
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // Step 4: While heap is not empty
        while (!heap.isEmpty()) {
            ListNode minNode = heap.poll(); // Get the smallest node
            System.out.println("Polled node with value: " + minNode.val);
            tail.next = minNode; // Add it to the merged list
            tail = tail.next;    // Move tail

            // Step 5: If the polled node has a next node, insert it into heap
            if (minNode.next != null) {
                heap.offer(minNode.next);
                System.out.println("Inserted next node with value: " + minNode.next.val);
            }
        }

        return dummy.next; // Return the real head
    }

    public static void main(String[] args) {
        // Create test lists
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};

        MergeKSortedLists solver = new MergeKSortedLists();
        ListNode mergedHead = solver.mergeKLists(lists);

        // Print result
        while (mergedHead != null) {
            System.out.print(mergedHead.val + " -> ");
            mergedHead = mergedHead.next;
        }
        System.out.println("null");
    }
}