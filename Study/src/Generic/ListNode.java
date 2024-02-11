package Generic;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ListNode {

   public static class Node{
       int data;
       Node right;
       Node left;

       Node(int data){
        this.data =data;
       }
   }

   public static void main(String[] args){

       Node root= new Node(4);
       root.left = new Node (2);
       root.left.left = new Node (1);
       root.left.right = new Node (3);
       root.right = new Node (7);
       root.right.left = new Node (6);
       root.right.right = new Node (9);
      // printLevelOrder(root);
        Node node = flipString(root);

       printLevelOrder(node);



   }

    private static Node flipString(Node root) {

        if ( root ==null)
            return root;
//        if(root.left !=null) {
//            int temp = root.left.data;
//            root.left.data = root.right.data;
//            root.right.data = temp;
//            root = root.left;
//
//        }
//        Node flipped = flipString(root);
//
////        if(root.right != null){
////            root = root.right;
////            flipString(root);
////        }
        Node left=  flipString(root.left);
        Node right=  flipString(root.right);

        root.right=left;
        root.left= right;
        return root;
    }

    public static void printLevelOrder(Node root)
    {
        // Base Case
        if(root==null)
            return ;

        // Create an empty queue for level order traversal
        Queue<Node> q=new LinkedList<>();
        // Enqueue Root and initialize height
        q.add(root);
        while(true)
        {
            // nodeCount (queue size) indicates number
            // of nodes at current level.
            int nodeCount = q.size();
            if (nodeCount == 0)
                break;

            // Dequeue all nodes of current level and
            // Enqueue all nodes of next level
            while (nodeCount > 0)
            {
                Node node = q.remove();
                System.out.print(node.data+" ");
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
                nodeCount--;
            }
            System.out.println();
        }
    }


}

