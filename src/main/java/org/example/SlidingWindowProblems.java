package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SlidingWindowProblems {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices){
            if(price < minPrice){
                minPrice = price;
            } else if (price - minPrice > maxProfit){
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int start = 0;
        int end = 0;
        int maxLen = 0;
        while(end < s.length()){
            if(!set.contains(s.charAt(end))){
                set.add(s.charAt(end));
                end++;
                maxLen = Math.max(maxLen, set.size());
            }else{
                set.remove(s.charAt(start));
                start++;
            }
        }
        return maxLen;
    }
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int start = 0;
        int maxCount = 0;
        int maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            if (end - start +1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
    public boolean checkInclusion(String s1, String s2) {
        if( s1.length() > s2.length()){
            return false;
        }
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }
        for (int i = s1.length(); i < s2.length(); i++){
            if(Arrays.equals(count1, count2)){
                return true;
            }
            count2[s2.charAt(i) - 'a']++;
            count2[s2.charAt(i - s1.length()) - 'a']--;
        }
        return Arrays.equals(count1, count2);
    }
}
