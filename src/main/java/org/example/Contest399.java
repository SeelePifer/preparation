package org.example;


import java.util.*;

public class Contest399 {

    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int count = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if(nums1[i] % (nums2[j] * k) == 0){
                    count++;
                }
            }
        }
        return count;
    }
    public String compressedString(String word) {
        StringBuilder compression = new StringBuilder();
        int count = 0;
        char currentChar = word.charAt(0);
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == currentChar){
                count++;
                if(count == 10){
                    compression.append(9).append(currentChar);
                    count = 1;
                }
            }else{
                compression.append(count).append(currentChar);
                currentChar = word.charAt(i);
                count = 1;
            }
        }
        compression.append(count).append(currentChar);

        return compression.toString();
    }
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums2){
            map.put(num * k, map.getOrDefault(num * k, 0)+1);
        }
        long count = 0;
        for(int num: nums1){
            for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                if (num % entry.getKey() == 0){
                    count += entry.getValue();
                }
            }
        }
        return count;
    }
    public long numberOfPairsV2(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for(int num : nums2){
            map.put(num * k, map.getOrDefault(num * k, 0)+1);
            set.add(num * k);
        }
        long count = 0;
        for(int num: nums1){
            for (int i : set){
                if(num % i == 0){
                    count += map.get(i);
                }
            }
        }
        return count;
    }
    public long numberOfPairsV3AndThatPassesOMG(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums2){
            map.put(num * k, map.getOrDefault(num * k, 0)+1);
        }
        long count = 0;
        for(int num: nums1){
            for(int i = 1; i * i <= num; i++){
                if(num % i == 0){
                    if(map.containsKey(i)){
                        count += map.get(i);
                    }
                    if(i != num / i && map.containsKey(num / i)){
                        count += map.get(num / i);
                    }
                }
            }
        }
        return count;
    }

}
