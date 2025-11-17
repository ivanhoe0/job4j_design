package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return new int[] {-1, -1};
        }
        var activeIntervals = new PriorityQueue<Interval>(Comparator.comparingInt(i -> i.end));
        int maxOverlap = 0;
        int maxStart = -1;
        int maxEnd = -1;
        int minStart = -1;

        for (Interval interval : intervals) {
            if (activeIntervals.isEmpty()) {
                activeIntervals.add(interval);
                minStart = interval.start;
                maxStart = interval.start;
                maxEnd = interval.end;
                continue;
            }
            while (!activeIntervals.isEmpty() && activeIntervals.peek().end <= interval.start) {
                activeIntervals.poll();
            }
            activeIntervals.add(interval);
            minStart = Math.max(minStart, interval.start);
            if (activeIntervals.size() > maxOverlap) {
                maxOverlap = activeIntervals.size();
                maxEnd = activeIntervals.peek().end;
                maxStart = minStart;
            }
        }

        return new int[] {
                maxStart, maxEnd
        };
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}
