package org.example;

import java.util.*;

public class Contest400 {
    public int minimumChairs(String s) {
        int chairs = 0;
        int people = 0;
        for(char c: s.toCharArray()){
            if (c == 'E'){
                people++;
                chairs = Math.max(chairs, people);
            }else{
                people--;
            }
        }
        return chairs;
    }
    public int countDays(int days, int[][] meetings) {
        boolean[] isMeeting = new boolean[days+1];
        for(int[] meeting : meetings){
            for(int i = meeting[0]; i <= meeting[1]; i++){
                isMeeting[i] = true;
            }
        }
        int count = 0;
        for (int i = 1; i <= days ; i++) {
            if(!isMeeting[i]){
                count++;
            }
        }
        return count;
    }
    public int countDaysV2(int days, int[][] meetings) {
        List<int[]> events = new ArrayList<>();
        for (int[] meeting : meetings) {
            events.add(new int[]{meeting[0], 1}); // meeting starts
            events.add(new int[]{meeting[1] + 1, -1}); // meeting ends
        }
        Collections.sort(events, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        int activeMeetings = 0, prevDay = 1, freeDays = 0;
        for (int[] event : events) {
            if (activeMeetings == 0) {
                freeDays += event[0] - prevDay;
            }
            activeMeetings += event[1];
            prevDay = event[0];
        }
        if (activeMeetings == 0) {
            freeDays += days - prevDay + 1;
        }
        return freeDays;
    }
    public String clearStars(String s) {
        LinkedList<Character> list = new LinkedList<>();
        LinkedList<Character> minStack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c != '*') {
                list.add(c);
                if (minStack.isEmpty() || c <= minStack.peek()) {
                    minStack.push(c);
                }
            } else if (!list.isEmpty() && !minStack.isEmpty()) {
                char minChar = minStack.peek();
                ListIterator<Character> it = list.listIterator(list.size());
                while (it.hasPrevious()) {
                    if (it.previous() == minChar) {
                        it.remove();
                        break;
                    }
                }
                while (!minStack.isEmpty() && minStack.peek() == minChar) {
                    minStack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            sb.append(c);
        }
        return sb.toString();
    }
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int andValue = nums[i];
            for (int j = i; j < n; j++) {
                andValue &= nums[j];
                minDiff = Math.min(minDiff, Math.abs(andValue - k));
                if(andValue < k){
                    break;
                }
            }
        }
        return minDiff;
    }

}
