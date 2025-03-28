package LeetCode2.BFS;


import java.util.ArrayList;
        import java.util.LinkedList;
        import java.util.List;
        import java.util.Queue;

public class BinaryTreeLevel {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // If the tree is empty, return an empty list.
        if (root == null) {
            return result;
        }

        // Initialize the queue and add the root.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Process nodes level by level.
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Number of nodes at current level
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at the current level.
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                // Enqueue left child if it exists.
                if (node.left != null) {
                    queue.offer(node.left);
                }

                // Enqueue right child if it exists.
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // Add the current level's list of values to the result.
            result.add(currentLevel);
        }

        return result;
    }

    // Example usage:
    public static void main(String[] args) {
        // Construct a simple binary tree:
        //       3
        //      / \
        //     9  20
        //        /  \
        //       15   7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        BinaryTreeLevel sol = new BinaryTreeLevel();
        List<List<Integer>> levels = sol.levelOrder(root);
        System.out.println("Level Order Traversal: " + levels);
    }
}
