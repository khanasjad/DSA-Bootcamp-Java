package LeetCode4.SegmentTrees;

// ===== SEGMENT TREE FOR RANGE MINIMUM QUERY - COMPLETE SOLUTIONS =====

import java.util.*;

public class SegmentTreeRangeMinimum {

    /*
    🎯 SEGMENT TREE IN SIMPLE TERMS:

    Problem: You have array [1, 3, 2, 7, 9, 11]
    Queries: "What's the minimum in range [1, 4]?" → Answer: 2

    NAIVE: Check every element in range → O(n) per query
    SMART: Build a tree that stores pre-computed minimums → O(log n) per query!

    Think of it like a TOURNAMENT BRACKET:
    - Leaf nodes = original array elements
    - Internal nodes = minimum of their children
    - Root = minimum of entire array

    Visual Tree for [1, 3, 2, 7]:
                    1
                  /   \
                 1     2
               /  \   /  \
              1    3 2    7
    */

    // ===== PROBLEM 1: LeetCode 307 - Range Sum Query - Mutable (Modified for Min) =====
    static class SegmentTree {
        private int[] tree;
        private int n;

        public SegmentTree(int[] nums) {
            /*
            🏗️ BUILDING THE TREE:

            Array: [1, 3, 2, 7]
            Tree array: [1, 1, 2, 1, 3, 2, 7, 0]
                         0  1  2  3  4  5  6  7

            Index mapping:
            - Root at index 1
            - For node i: left child = 2*i, right child = 2*i+1
            - For leaf i: parent = i/2
            */

            this.n = nums.length;
            this.tree = new int[2 * n];

            System.out.println("🏗️ Building Segment Tree for: " + Arrays.toString(nums));

            // Step 1: Copy array elements to second half of tree (leaves)
            for (int i = 0; i < n; i++) {
                tree[n + i] = nums[i];
            }
            System.out.println("Leaves: " + Arrays.toString(Arrays.copyOfRange(tree, n, 2*n)));

            // Step 2: Build internal nodes from bottom up
            for (int i = n - 1; i > 0; i--) {
                tree[i] = Math.min(tree[2 * i], tree[2 * i + 1]);
                System.out.println("tree[" + i + "] = min(tree[" + (2*i) + "], tree[" + (2*i+1) + "]) = " +
                        "min(" + tree[2*i] + ", " + tree[2*i+1] + ") = " + tree[i]);
            }

            System.out.println("🌳 Complete tree: " + Arrays.toString(tree));
            visualizeTree();
        }

        public void update(int index, int value) {
            /*
            🔄 UPDATE OPERATION:

            Change array[index] = value
            Then update all parent nodes up to root

            Like: Change a leaf in family tree, update all ancestors
            */

            System.out.println("\n🔄 Updating index " + index + " to value " + value);

            // Set value at position n + index (leaf position)
            int pos = index + n;
            tree[pos] = value;
            System.out.println("Updated leaf tree[" + pos + "] = " + value);

            // Update parents
            while (pos > 1) {
                pos /= 2; // Move to parent
                tree[pos] = Math.min(tree[2 * pos], tree[2 * pos + 1]);
                System.out.println("Updated parent tree[" + pos + "] = " + tree[pos]);
            }

            System.out.println("🌳 Tree after update: " + Arrays.toString(tree));
        }

        public int rangeMinQuery(int left, int right) {
            /*
            🔍 RANGE MINIMUM QUERY:

            Find minimum in range [left, right] inclusive

            Strategy: Start from leaves, move up tree
            - If current node completely inside range → use its value
            - If partially inside → need to check children
            - If completely outside → ignore
            */

            System.out.println("\n🔍 Range Min Query [" + left + ", " + right + "]");

            left += n;  // Convert to tree indices
            right += n;
            int minimum = Integer.MAX_VALUE;

            System.out.println("Converted to tree indices: [" + left + ", " + right + "]");

            while (left <= right) {
                System.out.println("Checking range [" + left + ", " + right + "]");

                // If left is odd, it's a right child, so include it
                if (left % 2 == 1) {
                    minimum = Math.min(minimum, tree[left]);
                    System.out.println("  Left " + left + " is odd (right child), include tree[" +
                            left + "] = " + tree[left] + ", min = " + minimum);
                    left++;
                }

                // If right is even, it's a left child, so include it
                if (right % 2 == 0) {
                    minimum = Math.min(minimum, tree[right]);
                    System.out.println("  Right " + right + " is even (left child), include tree[" +
                            right + "] = " + tree[right] + ", min = " + minimum);
                    right--;
                }

                // Move to next level up
                left /= 2;
                right /= 2;
                System.out.println("  Move up: new range [" + left + ", " + right + "]");
            }

            System.out.println("🎯 Range minimum: " + minimum);
            return minimum;
        }

        private void visualizeTree() {
            System.out.println("\n🌳 TREE VISUALIZATION:");
            System.out.println("                " + tree[1]);
            if (n > 1) {
                System.out.println("              /     \\");
                System.out.println("            " + tree[2] + "       " + tree[3]);
            }
            if (n > 2) {
                System.out.println("          /   \\   /   \\");
                String level3 = "";
                for (int i = 4; i < 8 && i < tree.length; i++) {
                    level3 += tree[i] + "   ";
                }
                System.out.println("        " + level3);
            }
            System.out.println();
        }
    }

    // ===== PROBLEM 2: LeetCode 315 - Count of Smaller Numbers After Self =====
    public List<Integer> countSmaller(int[] nums) {
        /*
        🎯 ADVANCED APPLICATION:

        For each element, count how many smaller elements are to its right

        Example: [5,2,6,1] → [2,1,1,0]
        - 5: has 2,1 smaller to right → count = 2
        - 2: has 1 smaller to right → count = 1
        - 6: has 1 smaller to right → count = 1
        - 1: nothing to right → count = 0

        Strategy: Use segment tree with coordinate compression
        */

        System.out.println("🎯 LEETCODE 315: Count Smaller Numbers After Self");
        System.out.println("Input: " + Arrays.toString(nums));

        // Coordinate compression: map values to indices
        Set<Integer> valueSet = new TreeSet<>();
        for (int num : nums) valueSet.add(num);
        List<Integer> sortedValues = new ArrayList<>(valueSet);

        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < sortedValues.size(); i++) {
            valueToIndex.put(sortedValues.get(i), i);
        }

        System.out.println("Coordinate mapping: " + valueToIndex);

        // Build segment tree for counts
        CountSegmentTree countTree = new CountSegmentTree(sortedValues.size());
        List<Integer> result = new ArrayList<>();

        // Process from right to left
        for (int i = nums.length - 1; i >= 0; i--) {
            int value = nums[i];
            int index = valueToIndex.get(value);

            // Query count of smaller elements (range [0, index-1])
            int count = (index > 0) ? countTree.rangeSum(0, index - 1) : 0;
            result.add(0, count);

            // Add current element to tree
            countTree.update(index, 1);

            System.out.println("Processing " + value + " at original index " + i +
                    ": smaller count = " + count);
        }

        System.out.println("🎯 Result: " + result);
        return result;
    }

    // ===== HELPER: SEGMENT TREE FOR COUNTING =====
    static class CountSegmentTree {
        private int[] tree;
        private int n;

        public CountSegmentTree(int size) {
            this.n = size;
            this.tree = new int[2 * n];
        }

        public void update(int index, int delta) {
            index += n;
            tree[index] += delta;
            while (index > 1) {
                index /= 2;
                tree[index] = tree[2 * index] + tree[2 * index + 1];
            }
        }

        public int rangeSum(int left, int right) {
            left += n;
            right += n;
            int sum = 0;
            while (left <= right) {
                if (left % 2 == 1) sum += tree[left++];
                if (right % 2 == 0) sum += tree[right--];
                left /= 2;
                right /= 2;
            }
            return sum;
        }
    }

    // ===== PROBLEM 3: Range Minimum Query with Updates =====
    public static class RangeMinimumQuery {
        /*
        🎯 CLASSIC RMQ PROBLEM:

        Given array, support two operations:
        1. update(index, value) - change array[index] = value
        2. rangeMin(left, right) - find min in range [left, right]

        Both operations in O(log n) time!
        */

        private SegmentTree segTree;

        public RangeMinimumQuery(int[] nums) {
            segTree = new SegmentTree(nums);
        }

        public void update(int index, int val) {
            segTree.update(index, val);
        }

        public int rangeMin(int left, int right) {
            return segTree.rangeMinQuery(left, right);
        }
    }

    // ===== COMPREHENSIVE TESTING =====
    public static void main(String[] args) {
        System.out.println("🌳 SEGMENT TREE RANGE MINIMUM - COMPLETE GUIDE");
        System.out.println("=".repeat(70));

        // Test basic segment tree
        int[] nums = {1, 3, 2, 7, 9, 11};
        System.out.println("📊 Testing with array: " + Arrays.toString(nums));

        SegmentTree segTree = new SegmentTree(nums);

        System.out.println("\n🔍 TESTING RANGE QUERIES:");
        segTree.rangeMinQuery(1, 4); // Query range [1, 4]
        segTree.rangeMinQuery(0, 2); // Query range [0, 2]
        segTree.rangeMinQuery(2, 5); // Query range [2, 5]

        System.out.println("\n🔄 TESTING UPDATES:");
        segTree.update(3, 1); // Change index 3 to value 1
        segTree.rangeMinQuery(1, 4); // Query same range again

        System.out.println("\n" + "=".repeat(70));

        // Test advanced application
        System.out.println("\n🎯 ADVANCED APPLICATION:");
        SegmentTreeRangeMinimum solution = new SegmentTreeRangeMinimum();
        int[] testArray = {5, 2, 6, 1};
        solution.countSmaller(testArray);

        System.out.println("\n🎓 KEY TAKEAWAYS:");
        System.out.println("• Segment Tree = Binary tree for range queries");
        System.out.println("• Build: O(n), Query: O(log n), Update: O(log n)");
        System.out.println("• Perfect for problems with frequent range operations");
        System.out.println("• Can adapt for: sum, min, max, GCD, etc.");

        System.out.println("\n🚀 COMMON LEETCODE PATTERNS:");
        System.out.println("• Range Sum/Min/Max with updates");
        System.out.println("• Count inversions/smaller elements");
        System.out.println("• Dynamic range queries");
        System.out.println("• Coordinate compression + segment tree");
    }
}

/*
🧠 SEGMENT TREE EXPLAINED LIKE YOU'RE 5:

🏆 TOURNAMENT ANALOGY:
Imagine a tennis tournament with 8 players:

Round 1: 8 players → 4 winners (compare pairs)
Round 2: 4 players → 2 winners
Round 3: 2 players → 1 champion

SEGMENT TREE IS THE SAME:
- Leaves = original numbers
- Each level up = "winners" (minimums) of pairs below
- Root = overall minimum

🎯 WHY IT'S FAST:

QUERY: "Who's the best player in positions 2-5?"
- Don't check all 4 players individually
- Use pre-computed tournament results
- Only need to check O(log n) nodes!

UPDATE: "Player 3 got better"
- Update player 3's score
- Update all tournament brackets involving player 3
- Only O(log n) updates needed!

🔧 IMPLEMENTATION TRICKS:

1. ARRAY REPRESENTATION:
   - Use array of size 2n instead of actual tree nodes
   - Index n to 2n-1 = leaves (original array)
   - Index 1 to n-1 = internal nodes
   - Parent of i = i/2, Children of i = 2i and 2i+1

2. BUILD FROM BOTTOM UP:
   - Start with leaves (copy original array)
   - Work upwards: each parent = function of children

3. QUERY OPTIMIZATION:
   - Start from leaf level, move up intelligently
   - Include nodes that are completely inside range
   - Split ranges that are partially inside

🎯 COMPLEXITY ANALYSIS:

BUILD: O(n) - visit each node once
QUERY: O(log n) - at most 4 nodes per level, log n levels
UPDATE: O(log n) - update path from leaf to root
SPACE: O(n) - tree array

🚀 WHEN TO USE SEGMENT TREES:

✅ GOOD FOR:
- Frequent range queries with updates
- Need O(log n) performance for both operations
- Range sum, min, max, GCD, etc.

❌ OVERKILL FOR:
- Static arrays (use sparse table or just precompute)
- Single queries (just iterate)
- Simple problems with small constraints

🔥 INTERVIEW STRATEGY:

1. RECOGNIZE PATTERN: "Range queries + updates" → Think Segment Tree
2. START SIMPLE: Explain the tournament analogy
3. CODE CLEANLY: Use the 2n array representation
4. OPTIMIZE: Mention lazy propagation for range updates
5. COMPARE: Know when to use vs Binary Indexed Tree

💡 ADVANCED TOPICS:
- Lazy propagation for range updates
- Persistent segment trees
- 2D segment trees
- Segment tree + coordinate compression

MASTER THIS PATTERN AND YOU'LL CRUSH ADVANCED DATA STRUCTURE INTERVIEWS! 🏆
*/