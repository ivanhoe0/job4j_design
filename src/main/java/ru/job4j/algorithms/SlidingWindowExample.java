package ru.job4j.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class SlidingWindowExample {

    public static int[] findMaxSubSequence(int[] array) {
        int currSum = 0;
        int leftIndex = 0;
        int rightIndex = 0;
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
        for (; rightIndex < array.length; rightIndex++) {
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
}
