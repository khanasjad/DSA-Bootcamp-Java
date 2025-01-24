package LinkedList;

import java.util.LinkedList;
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
       // Node node = flipString(root);

       //printLevelOrder(node);
    int[] preorder = new int[]{3,9,20,15,7};
    int[] inorder  = new int[]{9,3,15,20,7};

   Node node = buildTree(preorder,inorder);
       printLevelOrder(node);

   }

    private static Node buildTree(int[] preorder, int[] inorder) {
       return helper(0,0,inorder.length-1,preorder,inorder);
    }

    private static Node helper(int preStart, int inStart, int inEnd,  int[] preorder, int[] inorder) {
     if (preStart>preorder.length-1 || inStart>inEnd)
         return null;
       Node root = new Node(preorder[preStart]);
       int inIndex = 0;
       for(int i =inStart;i<=inEnd ;i++) {
           if (root.data == preorder[i]) {
               inIndex = i;
           }

   }
           root.left=   helper(preStart+1,inStart,inIndex-1,preorder,inorder);
           root.right=   helper(preStart+inIndex-inStart+1,inIndex+1,inEnd,preorder,inorder);



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

