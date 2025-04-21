package ru.job4j.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SlidingWindowExampleTest {
    @Test
    void testMethodOne() {
        int[] array = {11, -3, 0, 25, -17};
        int[] expected = {11, -3, 0, 25};
        assertThat(SlidingWindowExample.findMaxSubSequence(array)).isEqualTo(expected);
    }

    @Test
    void testMethodTwo() {
        int[] array = {11, -12, 1, 25, -17};
        int[] expected = {1, 25};
        assertThat(SlidingWindowExample.findMaxSubSequence(array)).isEqualTo(expected);
    }

    @Test
    void testMethodThree() {
        int[] array = {11, -12, 0, 25, -17, 25};
        int[] expected = {0, 25, -17, 25};
        assertThat(SlidingWindowExample.findMaxSubSequence(array)).isEqualTo(expected);
    }

    @Test
    void whenAllNegative() {
        int[] array = {-11, -12, -25, -7, -17};
        int[] expected = {-7};
        assertThat(SlidingWindowExample.findMaxSubSequence(array)).isEqualTo(expected);
    }
}