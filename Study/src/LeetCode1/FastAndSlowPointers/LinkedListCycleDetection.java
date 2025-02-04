package LeetCode1.FastAndSlowPointers;

public class LinkedListCycleDetection {

    public static void main(String[] args)
    {
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;

        System.out.println("Cyclic Node created");
        System.out.println(checkCyclic(head));
    }

    public static boolean checkCyclic(ListNode head){

        ListNode slow = head,fast = head;
        while (fast.next!= null && fast.next.next != null){

            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow) return true;
        }


        return false;
    }
}
