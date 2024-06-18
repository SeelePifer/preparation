package org.example;

import java.util.*;

public class GreedyProblems {

    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
    public boolean canJump(int[] nums) {
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i > maxIndex){
                return false;
            }
            maxIndex = Math.max(maxIndex, i + nums[i]);
        }
        return true;
    }
    public int jump(int[] nums) {
        int jumps = 0;
        int currentJumpEnd = 0;
        int fartherJumpEnd = 0;
        for (int i = 0; i < nums.length; i++) {
            fartherJumpEnd = Math.max(fartherJumpEnd, i + nums[i]);
            if(i == currentJumpEnd){
                jumps++;
                currentJumpEnd = fartherJumpEnd;
            }
        }
        return jumps;
    }
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int remaining = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            remaining += gas[i] - cost[i];
            if(remaining < 0){
                start = i + 1;
                remaining = 0;
            }
        }
        if(totalGas < totalCost){
            return -1;
        }else {
            return start;
        }
    }
    public boolean isNStraightHand(int[] hand, int groupSize) {
        TreeMap<Integer, Integer> counts = new TreeMap<>();
        for (int card: hand){
            counts.put(card, counts.getOrDefault(card,0)+1);
        }
        for(int card: counts.keySet()){
            if(counts.get(card) > 0){
                for (int i = groupSize - 1; i >= 0; i--) {
                    if(counts.getOrDefault(card + i, 0) < counts.get(card)){
                        return false;
                    }
                    counts.put(card + i, counts.get(card + i) - counts.get(card));
                }
            }
        }
        return true;
    }
    public boolean checkValidString(String s) {
        Stack<Integer> left = new Stack<>(), star = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '('){
                left.push(i);
            }else if(s.charAt(i) == '*'){
                star.push(i);
            }else {
                if(!left.isEmpty()){
                    left.pop();
                }else if(!star.isEmpty()){
                    star.pop();
                }else {
                    return false;
                }
            }
        }
        while(!left.isEmpty()){
            if(star.isEmpty() || star.pop() < left.pop()) return false;
        }
        return true;
    }
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int[] result = new int[3];
        for (int [] triplet : triplets){
            if(triplet[0] <= target[0] && triplet[1] <= target[1] && triplet[2] <= target[2]){
                result[0] = Math.max(result[0], triplet[0]);
                result[1] = Math.max(result[1], triplet[1]);
                result[2] = Math.max(result[2], triplet[2]);
            }
        }
        return Arrays.equals(result, target);
    }
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        int start = 0, end = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if(i == end){
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
    }

}
