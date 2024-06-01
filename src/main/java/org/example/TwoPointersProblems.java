package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointersProblems {
    public boolean isPalindrome(String s) {
        String trim = s.trim();
        int right = trim.length() - 1;
        int left = 0;
        while (left < right) {
            if (!Character.isLetterOrDigit(trim.charAt(left))) {
                left++;
            } else if (!Character.isLetterOrDigit(trim.charAt(right))) {
                right--;
            } else if (Character.toLowerCase(trim.charAt(left)) != Character.toLowerCase(trim.charAt(right))) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
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
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while( left < right){
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if(height[left] < height[right]) {
                left++;
            }else{
                right--;
            }
        }
        return maxArea;
    }
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int total = 0;
        while(left < right){
            if(height[left] < height[right]){
                if(height[left] >= leftMax){
                    leftMax = height[left];
                }else{
                    total += leftMax - height[left];
                }
                left++;
            }else{
                if(height[right] >= rightMax){
                    rightMax = height[right];
                }else{
                    total += rightMax - height[right];
                }
                right--;
            }
        }
        return total;
    }
}
