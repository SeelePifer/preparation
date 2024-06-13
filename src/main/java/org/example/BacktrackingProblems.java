package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingProblems {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int[] nums , int index, List<Integer> current, List<List<Integer>> result){
        result.add(new ArrayList<>(current));
        for(int i = index; i < nums.length; i++){
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackForCombinationSum(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrackForCombinationSum(int[] candidates, int target, int index, List<Integer> current, List<List<Integer>> result){
        if(target == 0){
            result.add(new ArrayList<>(current));
            return;
        }
        if(target < 0){
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrackForCombinationSum(candidates, target - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackForPermute(nums, new ArrayList<>(), result);
        return result;
    }
    private void backtrackForPermute(int[] nums, List<Integer> current, List<List<Integer>> result){
        if(current.size() == nums.length){
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(current.contains(nums[i])){
                continue;
            }
            current.add(nums[i]);
            backtrackForPermute(nums, current, result);
            current.remove(current.size() - 1);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrackForSubsetsWithDup(nums, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrackForSubsetsWithDup(int[] nums, int index, List<Integer> current, List<List<Integer>> result){
        result.add(new ArrayList<>(current));
        for (int i = index; i < nums.length; i++) {
            if(i > index && nums[i] == nums[i - 1]){
                continue;
            }
            current.add(nums[i]);
            backtrackForSubsetsWithDup(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrackForCombinationSum2(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrackForCombinationSum2(int[] candidates, int target, int index, List<Integer> current, List<List<Integer>> result){
        if(target == 0){
            result.add(new ArrayList<>(current));
            return;
        }
        if(target < 0){
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if(i > index && candidates[i] == candidates[i - 1]){
                continue;
            }
            current.add(candidates[i]);
            backtrackForCombinationSum2(candidates, target - candidates[i], i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0){
            return false;
        }
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(backtrackForExist(board, i, j, word, 0, visited)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean backtrackForExist(char[][] board, int i, int j, String word, int index, boolean[][] visited){
        if(index == word.length()){
            return true;
        }
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] != word.charAt(index)){
            return false;
        }
        visited[i][j] = true;
        if(backtrackForExist(board, i + 1, j, word, index + 1, visited) ||
                backtrackForExist(board, i - 1, j, word, index + 1, visited) ||
                backtrackForExist(board, i, j + 1, word, index + 1, visited) ||
                backtrackForExist(board, i, j - 1, word, index + 1, visited)){
            return true;
        }
        visited[i][j] = false;
        return false;
    }
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrackForPartition(s, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrackForPartition(String s, int index, List<String> current, List<List<String>> result){
        if(index == s.length()){
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if(isPalindrome(s, index, i)){
                current.add(s.substring(index, i + 1));
                backtrackForPartition(s, i + 1, current, result);
                current.remove(current.size() - 1);
            }
        }
    }
    private boolean isPalindrome(String s, int start, int end){
        while(start < end){
            if(s.charAt(start++) != s.charAt(end--)){
                return false;
            }
        }
        return true;
    }
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() == 0){
            return result;
        }
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtrackForLetterCombinations(digits, 0, "", result, mapping);
        return result;
    }
    private void backtrackForLetterCombinations(String digits, int index, String current, List<String> result, String[] mapping){
        if(index == digits.length()){
            result.add(current);
            return;
        }
        String letters = mapping[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            backtrackForLetterCombinations(digits, index + 1, current + letters.charAt(i), result, mapping);
        }
    }

}
