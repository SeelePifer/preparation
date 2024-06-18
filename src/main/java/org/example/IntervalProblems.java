package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalProblems {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        for(int[] interval: intervals){
            if(interval[1] < newInterval[0]) {
                result.add(interval);
            }else if (interval[0] > newInterval[1]){
                result.add(newInterval);
                newInterval = interval;
            }else{
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }
        result.add(newInterval);
        return result.toArray(new int[result.size()][]);
    }
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int end = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < end){
                count++;
            }else{
                end = intervals[i][1];
            }
        }
        return count;
    }
}
