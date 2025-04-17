package ru.job4j.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class SlidingWindowExample {

    public static int[] findMaxSubSequence(int[] array) {
        int currSum = 0;
        int leftIndex = 0;
        class MaxSumArray {
            int start;
            int end;
            int maxSum;

            public MaxSumArray(int start, int end, int maxSum) {
                this.start = start;
                this.end = end;
                this.maxSum = maxSum;
            }
        }

        MaxSumArray maxSumArray = null;
        for (int rightIndex = 0; rightIndex < array.length; rightIndex++) {
            currSum += array[rightIndex];
            if (maxSumArray == null || currSum > maxSumArray.maxSum) {
                maxSumArray = new MaxSumArray(leftIndex, rightIndex, currSum);
            }
            if (currSum < 0) {
                currSum = 0;
                leftIndex = rightIndex + 1;
            }
        }
        return Arrays.copyOfRange(array, maxSumArray.start, maxSumArray.end + 1);
    }

    public static void main(String[] args) {
        int[] array3 = {-1, 4, 8, -10, 18, 2, -17, 19};
        int[] array2 = {25, -10, 14, -29, 1, 4};
        int[] array = {11, -12, 1, 25, -17};
        System.out.println(Arrays.toString(findMaxSubSequence(array)));
        System.out.println(Arrays.toString(findMaxSubSequence(array2)));
        System.out.println(Arrays.toString(findMaxSubSequence(array3)));
    }
}
