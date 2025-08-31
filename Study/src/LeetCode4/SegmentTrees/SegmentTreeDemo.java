package LeetCode4.SegmentTrees;

class SegmentTree {
    /**
     * Segment Tree implementation for Range Sum Queries
     *
     * Time Complexity:
     * - Build: O(n)
     * - Query: O(log n)
     * - Update: O(log n)
     *
     * Space Complexity: O(n)
     */

    private int n;
    private int[] tree;
    private int[] nums;

    public SegmentTree(int[] nums) {
        this.n = nums.length;
        this.tree = new int[4 * n]; // 4*n is sufficient for all nodes
        this.nums = nums.clone(); // Keep a copy of original array

        if (nums.length > 0) {
            build(0, 0, n - 1);
        }
    }

    /**
     * Recursively build the segment tree
     *
     * @param node Current node index in the tree
     * @param start Start index of current segment
     * @param end End index of current segment
     */
    private void build(int node, int start, int end) {
        if (start == end) {
            // Leaf node - store the array element
            tree[node] = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;

            // Recursively build left and right subtrees
            build(leftChild, start, mid);
            build(rightChild, mid + 1, end);

            // Internal node stores sum of its children
            tree[node] = tree[leftChild] + tree[rightChild];
        }
    }

    /**
     * Update the value at index idx to val
     *
     * @param idx Index to update (0-indexed)
     * @param val New value
     */
    public void update(int idx, int val) {
        if (idx >= 0 && idx < n) {
            nums[idx] = val;
            update(0, 0, n - 1, idx, val);
        }
    }

    /**
     * Recursively update the segment tree
     *
     * @param node Current node index
     * @param start Start of current segment
     * @param end End of current segment
     * @param idx Index to update
     * @param val New value
     */
    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            // Leaf node - update the value
            tree[node] = val;
        } else {
            int mid = start + (end - start) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;

            if (idx <= mid) {
                // Update left subtree
                update(leftChild, start, mid, idx, val);
            } else {
                // Update right subtree
                update(rightChild, mid + 1, end, idx, val);
            }

            // Update current node with sum of children
            tree[node] = tree[leftChild] + tree[rightChild];
        }
    }

    /**
     * Query the sum of elements in range [left, right] (inclusive)
     *
     * @param left Left boundary of range (0-indexed)
     * @param right Right boundary of range (0-indexed)
     * @return Sum of elements in the given range
     */
    public int rangeSum(int left, int right) {
        if (n == 0 || left > right || left < 0 || right >= n) {
            return 0;
        }
        return rangeSum(0, 0, n - 1, left, right);
    }

    /**
     * Recursively compute range sum
     *
     * @param node Current node index
     * @param start Start of current segment
     * @param end End of current segment
     * @param left Left boundary of query range
     * @param right Right boundary of query range
     * @return Sum of elements in the query range
     */
    private int rangeSum(int node, int start, int end, int left, int right) {
        // No overlap
        if (right < start || left > end) {
            return 0;
        }

        // Complete overlap - return the node value
        if (left <= start && end <= right) {
            return tree[node];
        }

        // Partial overlap - query both children
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        int leftSum = rangeSum(leftChild, start, mid, left, right);
        int rightSum = rangeSum(rightChild, mid + 1, end, left, right);

        return leftSum + rightSum;
    }
}

/**
 * LeetCode 307: Range Sum Query - Mutable
 *
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
class NumArray {
    private SegmentTree segTree;

    public NumArray(int[] nums) {
        this.segTree = new SegmentTree(nums);
    }

    public void update(int index, int val) {
        segTree.update(index, val);
    }

    public int sumRange(int left, int right) {
        return segTree.rangeSum(left, right);
    }
}

/**
 * LeetCode 303: Range Sum Query - Immutable
 * Note: For immutable arrays, prefix sum is more efficient than segment tree
 * But here's the segment tree solution for learning purposes
 */
class NumArrayImmutable {
    private SegmentTree segTree;

    public NumArrayImmutable(int[] nums) {
        this.segTree = new SegmentTree(nums);
    }

    public int sumRange(int left, int right) {
        return segTree.rangeSum(left, right);
    }
}

/**
 * Alternative: Prefix Sum Solution for Immutable Range Sum Query
 * More efficient for LeetCode 303
 */
class NumArrayPrefixSum {
    private int[] prefixSum;

    public NumArrayPrefixSum(int[] nums) {
        prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }
}

/**
 * Test class to demonstrate usage
 */
public class SegmentTreeDemo {
    public static void main(String[] args) {
        testSegmentTree();
    }

    public static void testSegmentTree() {
        System.out.println("=== Segment Tree Range Sum Query Test ===");

        // Test case 1: Basic operations
        int[] nums = {1, 3, 5, 7, 9, 11};
        SegmentTree segTree = new SegmentTree(nums);

        System.out.println("Original array: " + java.util.Arrays.toString(nums));
        System.out.println("Sum of range [1, 3]: " + segTree.rangeSum(1, 3)); // 3 + 5 + 7 = 15
        System.out.println("Sum of range [0, 5]: " + segTree.rangeSum(0, 5)); // 1 + 3 + 5 + 7 + 9 + 11 = 36
        System.out.println("Sum of range [2, 4]: " + segTree.rangeSum(2, 4)); // 5 + 7 + 9 = 21

        // Test update
        System.out.println("\nAfter updating index 1 to 10:");
        segTree.update(1, 10);
        System.out.println("Sum of range [0, 2]: " + segTree.rangeSum(0, 2)); // 1 + 10 + 5 = 16
        System.out.println("Sum of range [1, 3]: " + segTree.rangeSum(1, 3)); // 10 + 5 + 7 = 22

        // Test case 2: LeetCode 307 style
        System.out.println("\n=== LeetCode 307 Test ===");
        NumArray numArray = new NumArray(new int[]{1, 3, 5});
        System.out.println("sumRange(0, 2): " + numArray.sumRange(0, 2)); // 9
        numArray.update(1, 2);
        System.out.println("sumRange(0, 2) after update: " + numArray.sumRange(0, 2)); // 8

        // Test edge cases
        System.out.println("\n=== Edge Cases ===");
        SegmentTree emptyTree = new SegmentTree(new int[]{});
        System.out.println("Empty tree range sum: " + emptyTree.rangeSum(0, 0)); // 0

        SegmentTree singleTree = new SegmentTree(new int[]{42});
        System.out.println("Single element range sum: " + singleTree.rangeSum(0, 0)); // 42

        // Performance comparison hint
        System.out.println("\n=== Performance Notes ===");
        System.out.println("Segment Tree: O(log n) query, O(log n) update");
        System.out.println("Prefix Sum: O(1) query, O(n) update");
        System.out.println("Use Segment Tree when you have many updates!");
    }
}

/*
DETAILED EXPLANATION:

1. Segment Tree Structure:
   - Binary tree where each node represents a segment/range of the array
   - Leaf nodes contain individual array elements
   - Internal nodes contain the sum of their children's ranges
   - Root node contains the sum of the entire array

2. Array Representation:
   - Tree stored in array format
   - For node at index i:
     * Left child: 2*i + 1
     * Right child: 2*i + 2
     * Parent: (i-1)/2
   - Size 4*n ensures enough space for all nodes

3. Key Operations:

   Build O(n):
   - Start from root (index 0)
   - Recursively build left and right subtrees
   - Leaf nodes store array elements
   - Internal nodes store sum of children

   Query O(log n):
   - Three cases for overlap between query range and node range:
     1. No overlap: return 0
     2. Complete overlap: return node value
     3. Partial overlap: recursively query both children

   Update O(log n):
   - Navigate to the leaf node containing the index
   - Update the leaf and propagate changes up to root
   - Each ancestor recalculates its sum

4. LeetCode Problem Applications:

   Problem 307 - Range Sum Query Mutable:
   - Perfect fit for segment trees
   - Handles both range queries and point updates efficiently

   Problem 303 - Range Sum Query Immutable:
   - Prefix sum is actually more efficient (O(1) query)
   - But segment tree solution provided for learning

5. Java-Specific Considerations:
   - Use int[] for tree storage
   - Handle integer overflow if needed (use long)
   - Proper encapsulation with private helper methods
   - Clone input array to avoid external modifications

6. Complexity Analysis:
   - Time: O(n) build, O(log n) query/update
   - Space: O(n) for tree storage
   - Better than naive O(n) query approach when many operations

7. Common Variations:
   - Range Minimum/Maximum Query
   - Range Update with Lazy Propagation
   - 2D Segment Trees for matrix problems
*/