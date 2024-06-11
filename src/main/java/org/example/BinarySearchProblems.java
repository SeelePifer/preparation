package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BinarySearchProblems {


    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int lowerBound = 1;
        int upperbound = 0;

        for (int i : piles) {
            upperbound = Math.max(upperbound, i);
        }
        int mid = (lowerBound + upperbound) / 2;
        while (lowerBound < upperbound) {
            mid = (lowerBound + upperbound) / 2;
            int result = binaryS(mid, piles, h);
            if (result < 0) {
                lowerBound = mid + 1;
            } else {
                upperbound = mid;
            }
        }
        return upperbound;
    }

    private int binaryS(int speed, int[] piles, int h) {
        while (true) {
            long usedHours = 0;
            for (int i = 0; i < piles.length; i++) {
                usedHours += piles[i] / speed;
                if (piles[i] % speed != 0) {
                    usedHours++;
                }

                if (usedHours > h) {
                    return -1;
                }
            }

            if (usedHours < h) {
                return 1;
            }

            if (usedHours == h) {
                return 0;
            }
            speed++;
        }
    }

    public int searchInRotatedSortedArray(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = cols - 1;

        while (row < rows && col > -1) {
            int current = matrix[row][col];
            if (current == target) {
                return true;
            }
            if (target > current) {
                row++;
            } else {
                col--;
            }
        }
        return false;

    }

    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low];
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int k = 0;
        int j = 0;
        int arr[] = new int[n + m];
        for (int i = 0; i < n + m; i++) {
            if (k < n && j < m) {
                if (nums1[k] < nums2[j]) {
                    arr[i] = nums1[k];
                    k++;
                } else {
                    arr[i] = nums2[j];
                    j++;
                }
            } else if (k < n) {
                arr[i] = nums1[k];
                k++;
            } else if (j < m) {
                arr[i] = nums2[j];
                j++;
            }
        }
        double ans = 0.0;
        if ((m + n) % 2 == 0) {
            ans = (double) (arr[(n + m) / 2] + arr[((n + m) / 2) - 1]) / 2;
        } else {
            ans = (double) (arr[(n + m) / 2]);
        }
        return ans;
    }
    class TimeMap {
        private HashMap<String, TreeMap<Integer, String>> map;
        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            map.putIfAbsent(key, new TreeMap<>());
            map.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if(!map.containsKey(key)){
                return "";
            }
            Map.Entry<Integer, String> entry = map.get(key).floorEntry(timestamp);
            if(entry == null){
                return "";
            }
            return entry.getValue();
        }
    }
}
