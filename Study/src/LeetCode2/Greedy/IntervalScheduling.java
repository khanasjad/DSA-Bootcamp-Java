package LeetCode2.Greedy;
import java.util.*;

public class IntervalScheduling {

    static class Interval {
        int start, end;
        Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    public static int maxNonOverlappingIntervals(List<Interval> intervals) {
        // Step 1: Sort by end time
        intervals.sort(Comparator.comparingInt(a -> a.end));

        int count = 0;
        int lastEnd = Integer.MIN_VALUE;

        for (Interval interval : intervals) {
            if (interval.start >= lastEnd) {
                count++;
                lastEnd = interval.end;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(
                new Interval(1, 3),
                new Interval(2, 4),
                new Interval(3, 5),
                new Interval(0, 6),
                new Interval(5, 7),
                new Interval(8, 9)
        );

        System.out.println("Max non-overlapping intervals: " + maxNonOverlappingIntervals(intervals));
    }
}