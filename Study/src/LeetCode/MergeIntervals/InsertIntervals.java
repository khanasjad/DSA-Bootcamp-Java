package LeetCode.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;

public class InsertIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] mergedIntervals = insert(intervals, newInterval);

        System.out.println("Result:");
        for (int[] interval : mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }

    private static int[][] insert(int[][] intervals, int[] newInterval) {

        int newStartValue = newInterval[0];
        int newEndValue = newInterval[1];
        ArrayList<int[]> arrayList = new ArrayList<int[]>();
        int n = intervals.length;
        int i = 0;
        while (i < n && intervals[i][1] <= newStartValue) {
            arrayList.add(intervals[i]);
            i++;
        }

        while (i < n && newEndValue >= intervals[i][0]) {
            newInterval[0] = Math.min(newStartValue, intervals[i][0]);
            newInterval[1] = Math.max(newEndValue, intervals[i][1]);
            i++;

        }
        arrayList.add(newInterval);

        while (i < n) {
            arrayList.add(intervals[i]);
            i++;
        }

        return arrayList.toArray(new int[arrayList.size()][]);


    }
}
