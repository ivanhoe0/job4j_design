package ru.job4j.algo;

import java.util.ArrayList;
import java.util.Arrays;

public class IntervalMerge {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        var result = new ArrayList<int[]>();
        var start = intervals[0][0];
        var end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end >= intervals[i][0]) {
                end = Math.max(end, intervals[i][1]);
            } else {
                result.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        result.add(new int[]{start, end});
        return  result.toArray(new int[result.size()][]);
    }
}
