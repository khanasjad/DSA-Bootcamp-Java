package LeetCode2.TopologicalSort;

import java.util.*;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 🔧 Step 1: Create the graph structure
        // We use an adjacency list to represent the graph
        List<List<Integer>> graph = new ArrayList<>();

        // Initialize empty list for each course
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // 🔢 This array tracks how many prerequisites each course has
        int[] inDegree = new int[numCourses];

        // 🔄 Build the graph from the prerequisites
        // For [a, b], add an edge b → a (must finish b before a)
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prereq = pair[1];

            graph.get(prereq).add(course); // edge: prereq → course
            inDegree[course]++;            // increase in-degree of target course
        }

        // 🛫 Step 2: Add all courses that have NO prerequisites to the queue
        // These are starting points (in-degree == 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                System.out.println("Course " + i + " has no prerequisites, adding to queue.");
            }
        }

        // 📊 This counter tracks how many courses we've "completed"
        int coursesTaken = 0;

        // 🔁 Step 3: Process the queue using BFS
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll(); // Take a course with no pending prerequisites
            coursesTaken++;
            System.out.println("Taking course: " + currentCourse + ", total taken: " + coursesTaken);

            // For every course that depends on this course:
            for (int nextCourse : graph.get(currentCourse)) {
                inDegree[nextCourse]--; // One less prerequisite now
                System.out.println("Reduced in-degree of course " + nextCourse + " to " + inDegree[nextCourse]);

                // If in-degree becomes 0 → it’s ready to be taken
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                    System.out.println("Course " + nextCourse + " now has no prerequisites, adding to queue.");
                }
            }
        }

        // ✅ If we were able to take all courses → no cycle
        boolean canFinishAll = (coursesTaken == numCourses);
        System.out.println("Can finish all courses? " + canFinishAll);

        return canFinishAll;
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();

        // 🔁 Test case 1: No cycle
        int[][] prereq1 = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println("\nTest Case 1 (Expected: true):");
        cs.canFinish(4, prereq1); // Output: true

        // 🔁 Test case 2: Has a cycle
        int[][] prereq2 = {{1, 0}, {0, 1}};
        System.out.println("\nTest Case 2 (Expected: false):");
        cs.canFinish(2, prereq2); // Output: false
    }
}