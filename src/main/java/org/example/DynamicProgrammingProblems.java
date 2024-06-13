package org.example;

public class DynamicProgrammingProblems {
    public int climbStairs(int n) {
        if(n <= 1){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n ; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n ; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        return Math.min(dp[n-1], dp[n-2]);
    }
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i -1]);
        }
        return dp[nums.length - 1];
    }
    public int robTwo(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        return Math.max(robHelper(nums, 0, nums.length - 2), robHelper(nums, 1, nums.length - 1));
        }
    private int robHelper(int[] nums, int start, int end){
        int dp[] = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i -1]);
        }
        return dp[end];
    }
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            for (int j = 0; j < i; j++) {
                dp[j][i] = (s.charAt(i) == s.charAt(j)) && (i - j < 2 || dp[j + 1][i - 1]);
                if (dp[j][i] && (i - j + 1) > (end - start + 1)) {
                    start = j;
                    end = i;
                }
            }
        }
        return s.substring(start, end + 1);
    }
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0 ; j--) {
                if(s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1])){
                    dp[j][i] = true;
                    count++;
                }
            }
        }
        return count;
    }

}
