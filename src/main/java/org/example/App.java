package org.example;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> response = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;

            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0){
                    response.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    while( j < k && nums[j] == nums[j + 1]){
                        j++;
                    }
                    while(j < k && nums[k] == nums[k-1]){
                        k--;
                    }
                    j++;
                    k--;
                }else if (sum < 0){
                    j++;
                }else {
                    k--;
                }
            }
        }
        return response;
    }
    public static int RecursionChallenge(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 1;
        for (int i = 1; i <= num; i++) {
            dp[i] = i * dp[i - 1];
        }
        return dp[num];
    }

    public int[][] merge(int[][] intervals) {
        int lengthOfIntervals = intervals.length; // 4
        List<List<Integer>> response = new ArrayList<>();

        if(lengthOfIntervals == 1){
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[] currentInterval = intervals[0];
        response.add(Arrays.asList(currentInterval[0], currentInterval[1]));
        for (int i = 1; i < lengthOfIntervals; i++) {
            int currentBegin = intervals[i][0];
            int currentEnd = intervals[i][1];
            int lastEnd = response.get(response.size() - 1).get(1);

            if(currentBegin <= lastEnd) {
                response.get(response.size() - 1).set(1, Math.max(lastEnd, currentEnd));
            } else {
                response.add(Arrays.asList(currentBegin, currentEnd));
            }
        }
        int[][] arrayResponse = new int[response.size()][2];
        for (int i = 0; i < response.size(); i++) {
            arrayResponse[i][0] = response.get(i).get(0);
            arrayResponse[i][1] = response.get(i).get(1);
        }

        return arrayResponse;

    }


}
