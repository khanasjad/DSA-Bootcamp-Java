package LeetCode4.MonotonicStack;

// ===== NEXT GREATER ELEMENT - COMPLETE LEETCODE SOLUTIONS =====

import java.util.*;

public class NextGreaterElementSolutions {

    /*
    🎯 THE CONCEPT IN SIMPLE TERMS:

    For each number, find the first number to its RIGHT that is BIGGER

    Example: [2, 1, 2, 4, 3, 1]
    Results: [4, 2, 4, -1, -1, -1]

    Why?
    - 2 → first bigger number to right is 4
    - 1 → first bigger number to right is 2
    - 2 → first bigger number to right is 4
    - 4 → no bigger number to right, so -1
    - 3 → no bigger number to right, so -1
    - 1 → no bigger number to right, so -1
    */

    // ===== PROBLEM 1: LeetCode 496 - Next Greater Element I =====
    public int[] nextGreaterElement_I(int[] nums1, int[] nums2) {
        /*
        PROBLEM: nums1 is subset of nums2. For each element in nums1,
        find its next greater element in nums2.

        Example:
        nums1 = [4,1,2], nums2 = [1,3,4,2]
        Answer: [-1,3,-1]

        Why? 4→no greater, 1→3, 2→no greater
        */

        System.out.println("🧪 LEETCODE 496: Next Greater Element I");
        System.out.println("nums1: " + Arrays.toString(nums1));
        System.out.println("nums2: " + Arrays.toString(nums2));

        // Step 1: Build next greater map for all elements in nums2
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        System.out.println("\n🔍 Building next greater map:");

        for (int i = 0; i < nums2.length; i++) {
            int current = nums2[i];
            System.out.println("\nProcessing " + current + " at index " + i);

            // Pop elements smaller than current (they found their next greater!)
            while (!stack.isEmpty() && stack.peek() < current) {
                int smaller = stack.pop();
                nextGreaterMap.put(smaller, current);
                System.out.println("  ✅ " + smaller + " → " + current + " (next greater found!)");
            }

            stack.push(current);
            System.out.println("  📤 Pushed " + current + " to stack");
            System.out.println("  Stack now: " + stack);
        }

        // Remaining elements in stack have no next greater element
        while (!stack.isEmpty()) {
            nextGreaterMap.put(stack.pop(), -1);
        }

        System.out.println("\n📊 Next Greater Map: " + nextGreaterMap);

        // Step 2: Build result for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.get(nums1[i]);
        }

        System.out.println("🎯 Final result: " + Arrays.toString(result));
        return result;
    }

    // ===== PROBLEM 2: LeetCode 503 - Next Greater Element II (Circular) =====
    public int[] nextGreaterElements_Circular(int[] nums) {
        /*
        PROBLEM: Same as above, but array is CIRCULAR!
        You can wrap around to beginning.

        Example: [1,2,1]
        Answer: [2,-1,2]

        Why?
        - First 1 → next greater is 2
        - 2 → no greater element even wrapping around, so -1
        - Last 1 → wrapping around, next greater is 2
        */

        System.out.println("🔄 LEETCODE 503: Next Greater Element II (Circular)");
        System.out.println("nums: " + Arrays.toString(nums));

        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // Initialize with -1 (no next greater found)

        Stack<Integer> stack = new Stack<>(); // Store indices

        System.out.println("\n🔍 Processing (going around twice for circular):");

        // Go through array TWICE to handle circular nature
        for (int i = 0; i < 2 * n; i++) {
            int currentIndex = i % n; // Wrap around using modulo
            int current = nums[currentIndex];

            System.out.println("\nRound " + (i/n + 1) + ", Position " + currentIndex +
                    ", Value " + current);

            // Pop indices whose next greater element is current
            while (!stack.isEmpty() && nums[stack.peek()] < current) {
                int smallerIndex = stack.pop();
                result[smallerIndex] = current;
                System.out.println("  ✅ nums[" + smallerIndex + "]=" + nums[smallerIndex] +
                        " → next greater is " + current);
            }

            // Only push indices in first round (avoid duplicates)
            if (i < n) {
                stack.push(currentIndex);
                System.out.println("  📤 Pushed index " + currentIndex + " to stack");
            }

            System.out.println("  Stack: " + stack);
        }

        System.out.println("\n🎯 Final result: " + Arrays.toString(result));
        return result;
    }

    // ===== PROBLEM 3: LeetCode 739 - Daily Temperatures =====
    public int[] dailyTemperatures(int[] temperatures) {
        /*
        PROBLEM: For each day, find how many days until a warmer temperature

        Example: [73,74,75,71,69,72,76,73]
        Answer:  [1,1,4,2,1,1,0,0]

        Why?
        - Day 0 (73°): Next warmer is day 1 (74°) → 1 day wait
        - Day 1 (74°): Next warmer is day 2 (75°) → 1 day wait
        - Day 2 (75°): Next warmer is day 6 (76°) → 4 days wait
        */

        System.out.println("🌡️ LEETCODE 739: Daily Temperatures");
        System.out.println("Temperatures: " + Arrays.toString(temperatures));

        int n = temperatures.length;
        int[] result = new int[n]; // Days to wait for warmer temperature
        Stack<Integer> stack = new Stack<>(); // Store indices of days

        System.out.println("\n🔍 Processing each day:");

        for (int i = 0; i < n; i++) {
            int currentTemp = temperatures[i];
            System.out.println("\nDay " + i + ": " + currentTemp + "°");

            // For all previous colder days, today is their "warmer day"!
            while (!stack.isEmpty() && temperatures[stack.peek()] < currentTemp) {
                int colderDayIndex = stack.pop();
                int daysToWait = i - colderDayIndex;
                result[colderDayIndex] = daysToWait;

                System.out.println("  ✅ Day " + colderDayIndex + " (" +
                        temperatures[colderDayIndex] + "°) waits " +
                        daysToWait + " days for " + currentTemp + "°");
            }

            stack.push(i);
            System.out.println("  📤 Added day " + i + " to waiting list");
            System.out.println("  Stack (waiting days): " + stack);
        }

        System.out.println("\n🎯 Final result: " + Arrays.toString(result));
        return result;
    }

    // ===== GENERIC NEXT GREATER ELEMENT TEMPLATE =====
    public int[] nextGreaterElement_Generic(int[] nums) {
        /*
        🎯 GENERIC TEMPLATE FOR ANY "NEXT GREATER" PROBLEM:

        1. Use stack to store indices (or values)
        2. For each element, pop smaller elements from stack
        3. Current element is "next greater" for popped elements
        4. Push current element to stack
        5. Elements remaining in stack have no next greater
        */

        System.out.println("🎯 GENERIC NEXT GREATER ELEMENT");
        System.out.println("Input: " + Arrays.toString(nums));

        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // Default: no next greater element

        Stack<Integer> stack = new Stack<>(); // Monotonic decreasing stack

        System.out.println("\n🔍 Step-by-step execution:");

        for (int i = 0; i < n; i++) {
            System.out.println("\n📍 Index " + i + ", Value " + nums[i]);
            System.out.println("Stack before: " + stackToString(stack, nums));

            // Pop all elements smaller than current
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int index = stack.pop();
                result[index] = nums[i];
                System.out.println("  ✅ nums[" + index + "]=" + nums[index] +
                        " found next greater: " + nums[i]);
            }

            stack.push(i);
            System.out.println("  📤 Pushed index " + i);
            System.out.println("Stack after: " + stackToString(stack, nums));
        }

        System.out.println("\n🎯 Final result: " + Arrays.toString(result));
        return result;
    }

    private String stackToString(Stack<Integer> stack, int[] nums) {
        if (stack.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < stack.size(); i++) {
            int index = stack.get(i);
            sb.append(index).append("(").append(nums[index]).append(")");
            if (i < stack.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // ===== COMPREHENSIVE TESTING =====
    public static void main(String[] args) {
        NextGreaterElementSolutions solution = new NextGreaterElementSolutions();

        System.out.println("🎯 NEXT GREATER ELEMENT - COMPLETE GUIDE");
        System.out.println("=".repeat(70));

        // Test Case 1: LeetCode 496
        System.out.println("\n1️⃣ LEETCODE 496:");
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        solution.nextGreaterElement_I(nums1, nums2);

        System.out.println("\n" + "=".repeat(70));

        // Test Case 2: LeetCode 503
        System.out.println("\n2️⃣ LEETCODE 503 (Circular):");
        int[] circular = {1, 2, 1};
        solution.nextGreaterElements_Circular(circular);

        System.out.println("\n" + "=".repeat(70));

        // Test Case 3: LeetCode 739
        System.out.println("\n3️⃣ LEETCODE 739 (Daily Temperatures):");
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        solution.dailyTemperatures(temps);

        System.out.println("\n" + "=".repeat(70));

        // Test Case 4: Generic template
        System.out.println("\n4️⃣ GENERIC TEMPLATE:");
        int[] generic = {2, 1, 2, 4, 3, 1};
        solution.nextGreaterElement_Generic(generic);

        System.out.println("\n🎓 ALGORITHM SUMMARY:");
        System.out.println("┌─────────────────┬──────────┬───────────┬─────────────────┐");
        System.out.println("│ Problem         │ Time     │ Space     │ Key Insight     │");
        System.out.println("├─────────────────┼──────────┼───────────┼─────────────────┤");
        System.out.println("│ Next Greater I  │ O(n+m)   │ O(n)      │ HashMap lookup  │");
        System.out.println("│ Next Greater II │ O(n)     │ O(n)      │ Circular array  │");
        System.out.println("│ Daily Temps     │ O(n)     │ O(n)      │ Distance calc   │");
        System.out.println("└─────────────────┴──────────┴───────────┴─────────────────┘");
    }
}

/*
🧠 MONOTONIC STACK PATTERNS:

🎯 CORE CONCEPT:
Monotonic Stack = Stack that maintains elements in sorted order
- Monotonic DECREASING: Top element is always smallest
- Monotonic INCREASING: Top element is always largest

🔍 FOR "NEXT GREATER ELEMENT":
Use DECREASING monotonic stack:
- When we see bigger element → it's "next greater" for smaller elements in stack
- Pop smaller elements and record their answer
- Push current element

🚀 THE MAGIC:
- Each element is pushed ONCE and popped AT MOST ONCE
- Total operations = O(n), not O(n²)!

🎪 THINK OF IT LIKE:
You're in a line at amusement park, looking for someone taller behind you:
- Keep track of people in front who are still looking
- When tall person arrives → they're the answer for shorter people
- Remove shorter people from "waiting list" (they found their answer)

📊 VISUAL EXAMPLE:
Array: [2, 1, 2, 4, 3, 1]

i=0, val=2: stack=[2]                    # 2 is waiting
i=1, val=1: stack=[2,1]                  # 1 is waiting (2 still waiting)
i=2, val=2: 1 found next greater (2)    # Pop 1, stack=[2]
            2 found next greater (2)    # Pop 2, stack=[]
            stack=[2]                   # Push new 2
i=3, val=4: 2 found next greater (4)    # Pop 2, stack=[]
            stack=[4]                   # Push 4
i=4, val=3: stack=[4,3]                 # 3 waiting (4 still waiting)
i=5, val=1: stack=[4,3,1]               # Everyone waiting

Result: [4, 2, 4, -1, -1, -1]

🔥 INTERVIEW TIPS:

1. START WITH BRUTE FORCE:
   "I could check every element against every element to its right - O(n²)"

2. OPTIMIZE WITH STACK:
   "But I can use monotonic stack to do it in O(n)"

3. EXPLAIN THE INSIGHT:
   "Key insight: when I see a bigger element, it answers multiple previous elements"

4. TRACE THROUGH EXAMPLE:
   Always walk through small example to show understanding

5. HANDLE EDGE CASES:
   - Empty array
   - All elements decreasing (no next greater)
   - All elements increasing (each finds next)

🎯 RELATED PROBLEMS:
- LeetCode 84: Largest Rectangle in Histogram
- LeetCode 85: Maximal Rectangle
- LeetCode 42: Trapping Rain Water
- LeetCode 901: Online Stock Span

ALL use the same monotonic stack pattern! 🚀
*/