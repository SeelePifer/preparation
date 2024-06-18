package org.example;

public class BitManipulationProblem {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums){
            result ^= num;
        }
        return result;
    }
    public int hammingWeight(int n) {
        int sum = 0;
        while (n != 0){
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }
}
