package org.example;

import java.util.*;

public class ArraysLeetCode {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> array = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (array.contains(nums[i])) {
                return true;
            }
            array.add(nums[i]);
        }
        return false;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] charArray1 = s.toCharArray();
        char[] charArray2 = t.toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        return Arrays.equals(charArray1, charArray2);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> response = new ArrayList<>();
        List<String> anagrams = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        for (String i : strs) {
            char[] charArray = i.toCharArray();
            Arrays.sort(charArray);
            anagrams.add(new String(charArray));
        }
        Set<String> set = new HashSet<>(anagrams);
        for (String i : set) {
            strings.clear();
            for (int j = 0; j < anagrams.size(); j++) {
                if (anagrams.get(j).equals(i)) {
                    strings.add(strs[j]);
                    anagrams.set(j, "0");
                }
            }
            response.add(new ArrayList<>(strings));
        }
        return response;
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int[] response = new int[nums.length];
        left[0] = 1;
        right[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < nums.length; i++) {
            response[i] = left[i] * right[i];
        }
        return response;
    }

    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int longestStreak = 1;
        int currentStreak = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i-1]){
                if(nums[i] == nums[i-1] + 1){
                    currentStreak += 1;
                }else{
                    currentStreak = 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums){
            if(map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            }else{
                map.put(i, 1);
            }
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((a,b) -> map.get(b) - map.get(a));
        int response[] = new int[k];
        for(int i = 0; i < k; i++){
            response[i] = list.get(i);
        }
        return response;
    }
    public boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                char number = board[i][j];
                if(number != '.'){
                    if( !seen.add(number + "row "+ i)
                            || !seen.add(number + "colum "+ j)
                            || !seen.add(number + "block " + i/3 + " - "+ j/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

        public static void main (String[]args){
            System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
            System.out.println(new ArraysLeetCode().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
            System.out.println(Arrays.toString(new ArraysLeetCode().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        }
    }
