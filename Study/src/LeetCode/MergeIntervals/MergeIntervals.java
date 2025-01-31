package LeetCode.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
        int[][] mergedIntervals = merge(intervals);

        System.out.println("Merged Intervals:");
        for (int[] interval : mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }

    private static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));

        ArrayList<int[]> merged = new ArrayList<int[]>();
        int[] currentInterval = intervals[0];

        merged.add(currentInterval);

        for (int[] interval:intervals) {
            int Start = interval[0];
            int End = interval[1];
           // int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];

            if (currentEnd >= Start){
                currentInterval[1] = Math.max(currentEnd,End);
            }else {
                currentInterval = interval;
                merged.add(currentInterval);
            }




        }
        return merged.toArray(new int[merged.size()][]);
    }
}
