package LeetCode5.ReservoirSampling;


import java.util.Random;
public class RandomNodeLinkedList {
    private Random random;

    public RandomNodeLinkedList() {
        random = new Random();
    }

    public int getRandom(ListNode head) {
        // Start by picking the first node's value as the current favorite.
        int result = head.val;
        ListNode current = head.next;
        int count = 1;  // We've already seen the first node.

        // Walk through the rest of the linked list.
        while (current != null) {
            count++; // Increase the count for the new node.
            // With probability 1/count, choose the current node's value.
            if (random.nextInt(count) == 0) {
                result = current.val;
            }
            current = current.next;
        }

        // After going through all nodes, 'result' holds a randomly selected node's value.
        return result;
    }

    // Example usage:
    public static void main(String[] args) {
        // Create a simple linked list: 10 -> 20 -> 30 -> 40
        ListNode head = new ListNode(10);
        head.next = new ListNode(20);
        head.next.next = new ListNode(30);
        head.next.next.next = new ListNode(40);

        RandomNodeLinkedList sol = new RandomNodeLinkedList();
        // Get a random node's value from the list
        int randomValue = sol.getRandom(head);
        System.out.println("Random Node Value: " + randomValue);
    }
}
