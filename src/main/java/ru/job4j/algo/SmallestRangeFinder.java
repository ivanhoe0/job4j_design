package ru.job4j.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallestRangeFinder {
    public static int[] findSmallestRange(int[] nums, int k) {
        int[] result = null;
        int left = 0;
        int right = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (; right < nums.length; right++) {
            if (map.get(nums[right]) == null || map.get(nums[right]) < left) {
                map.put(nums[right], right);
                count++;
            } else {
                left = right;
                count = 1;
            }
            if (count == k) {
                result = new int[]{left, right};
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}