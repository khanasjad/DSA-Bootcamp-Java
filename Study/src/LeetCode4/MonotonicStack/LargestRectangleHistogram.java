package LeetCode4.MonotonicStack;



// ===== LeetCode 84: Largest Rectangle in Histogram =====
// PROBLEM: Given heights of bars, find area of largest rectangle

import java.util.*;

public class LargestRectangleHistogram {

    /*
    🎯 PROBLEM IN SIMPLE TERMS:

    You have bars of different heights: [2,1,5,6,2,3]

    Visual representation:
        6 ■
        5 ■ ■
        4   ■ ■
        3   ■ ■     ■
        2 ■ ■ ■   ■ ■
        1 ■ ■ ■ ■ ■ ■
          0 1 2 3 4 5

    Goal: Find the LARGEST rectangle you can draw
    Answer: Rectangle with height=5, width=2, area=10 (at positions 2,3)

    KEY INSIGHT: For each bar, find how far LEFT and RIGHT you can extend
    with that bar's height as the minimum height
    */

    // ===== SOLUTION 1: BRUTE FORCE (for understanding) =====
    public int largestRectangleArea_BruteForce(int[] heights) {
        System.out.println("🐌 BRUTE FORCE APPROACH:");
        System.out.println("Heights: " + Arrays.toString(heights));
        System.out.println();

        int maxArea = 0;
        int n = heights.length;

        // For each bar, try it as the "shortest bar" in rectangle
        for (int i = 0; i < n; i++) {
            int minHeight = heights[i];

            // Expand left and right while height allows
            for (int j = i; j < n; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                int width = j - i + 1;
                int area = minHeight * width;

                System.out.println("Rectangle from " + i + " to " + j +
                        ": height=" + minHeight + ", width=" + width +
                        ", area=" + area);

                maxArea = Math.max(maxArea, area);
            }
        }

        System.out.println("Max area: " + maxArea);
        return maxArea;
    }

    // ===== SOLUTION 2: MONOTONIC STACK (Optimal) =====
    public int largestRectangleArea_MonotonicStack(int[] heights) {
        /*
        🚀 MONOTONIC STACK GENIUS:

        Stack keeps bars in INCREASING order of height
        When we see a shorter bar → we know rectangles ending here!

        Think: "I'm walking through bars, keeping track of 'active' rectangles"
        When I see a shorter bar, some rectangles must end
        */

        System.out.println("🚀 MONOTONIC STACK APPROACH:");
        System.out.println("Heights: " + Arrays.toString(heights));
        System.out.println();

        Stack<Integer> stack = new Stack<>(); // Store indices, not heights!
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) { // Note: i <= n (we add a virtual 0 at end)
            int currentHeight = (i == n) ? 0 : heights[i]; // Virtual 0 forces all rectangles to end

            System.out.println("📍 Position " + i + ", height = " + currentHeight);
            System.out.println("Stack before: " + stack);

            // While current bar is shorter than stack top, calculate rectangles
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int heightIndex = stack.pop();
                int height = heights[heightIndex];

                // Calculate width: from (left boundary + 1) to (right boundary - 1)
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                int area = height * width;

                System.out.println("  🧮 Calculating rectangle:");
                System.out.println("     Bar at index " + heightIndex + " (height=" + height + ")");
                System.out.println("     Left boundary: " + (stack.isEmpty() ? -1 : stack.peek()));
                System.out.println("     Right boundary: " + i);
                System.out.println("     Width: " + width + ", Area: " + area);

                maxArea = Math.max(maxArea, area);

                if (area == maxArea) {
                    System.out.println("     🏆 NEW MAX AREA: " + maxArea);
                }
            }

            if (i < n) { // Don't push the virtual 0
                stack.push(i);
                System.out.println("  📤 Pushed index " + i + " to stack");
            }

            System.out.println("Stack after: " + stack);
            System.out.println();
        }

        System.out.println("🎉 FINAL MAX AREA: " + maxArea);
        return maxArea;
    }

    // ===== VISUAL DEMONSTRATION =====
    public void visualizeHistogram(int[] heights) {
        System.out.println("📊 VISUAL REPRESENTATION:");

        int maxHeight = Arrays.stream(heights).max().orElse(0);

        // Print from top to bottom
        for (int level = maxHeight; level >= 1; level--) {
            System.out.print(level + " ");
            for (int i = 0; i < heights.length; i++) {
                if (heights[i] >= level) {
                    System.out.print("■ ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        // Print indices
        System.out.print("  ");
        for (int i = 0; i < heights.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
    }

    // ===== COMPLETE TEST DEMONSTRATION =====
    public static void main(String[] args) {
        LargestRectangleHistogram solution = new LargestRectangleHistogram();

        int[] heights = {2, 1, 5, 6, 2, 3};

        System.out.println("🎯 LARGEST RECTANGLE IN HISTOGRAM");
        System.out.println("=".repeat(60));

        solution.visualizeHistogram(heights);

        System.out.println("🧪 TESTING BRUTE FORCE:");
        solution.largestRectangleArea_BruteForce(heights);

        System.out.println("\n" + "=".repeat(60) + "\n");

        System.out.println("🧪 TESTING MONOTONIC STACK:");
        solution.largestRectangleArea_MonotonicStack(heights);

        System.out.println("\n🎓 KEY CONCEPTS:");
        System.out.println("• Monotonic Stack = Stack that maintains order (increasing/decreasing)");
        System.out.println("• When we see shorter bar = Some rectangles must end here");
        System.out.println("• Width calculation = Key insight for getting correct area");
        System.out.println("• Virtual 0 at end = Forces all remaining rectangles to be calculated");
    }
}

/*
🧠 MONOTONIC STACK EXPLAINED LIKE YOU'RE 5:

🎪 IMAGINE YOU'RE AT A CARNIVAL:
You're walking past game booths of different heights
You want to find the biggest rectangular tent you can set up

📚 THE STACK IS LIKE A NOTEBOOK:
- You write down heights of booths as you pass them
- BUT you keep the notebook in INCREASING order
- When you see a shorter booth, you erase some notes and calculate tent sizes

🔧 WHY DOES IT WORK?

1. INCREASING STACK = All "active" rectangles
   - Each item in stack represents a rectangle that's still "growing"

2. SHORTER BAR = Time to "close" some rectangles
   - Any bar taller than current one must end here
   - Calculate their areas before removing from stack

3. WIDTH CALCULATION = Tricky part!
   - Left boundary = previous item in stack (or -1 if empty)
   - Right boundary = current position
   - Width = right - left - 1

🎯 EXAMPLE TRACE:

Heights: [2,1,5,6,2,3]

i=0, height=2: stack=[0]                    # Start first rectangle
i=1, height=1: height[0]=2 > 1, so pop 0   # Rectangle with height 2 ends
                Calculate: height=2, width=1, area=2
                stack=[1]                    # Start new rectangle with height 1
i=2, height=5: stack=[1,2]                  # Rectangle with height 5 starts
i=3, height=6: stack=[1,2,3]                # Rectangle with height 6 starts
i=4, height=2: height[3]=6 > 2, so pop 3   # Rectangle with height 6 ends
                Calculate: height=6, width=1, area=6
                height[2]=5 > 2, so pop 2   # Rectangle with height 5 ends
                Calculate: height=5, width=2, area=10  ← MAX!
                stack=[1,4]
i=5, height=3: stack=[1,4,5]
i=6 (virtual 0): Pop everything and calculate remaining rectangles

🏆 MAXIMUM AREA = 10 (height=5, width=2)

⚡ TIME COMPLEXITY: O(n) - each element pushed/popped exactly once
💾 SPACE COMPLEXITY: O(n) - for the stack

🚀 INTERVIEW TIPS:
- Draw the histogram first to visualize
- Explain the "closing rectangles" concept clearly
- Show how stack maintains increasing order
- Don't forget the virtual 0 at the end!
*/