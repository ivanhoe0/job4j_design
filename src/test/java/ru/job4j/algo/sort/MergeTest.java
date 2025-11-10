package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;

class MergeTest {
    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenSortedThenOk2() {
        int[] array = {-12, 34, 116, 0, 0, 35, -14, -3, 5, 15};
        assertThat(Merge.mergesort(array)).containsExactly(-14, -12, -3, 0, 0, 5, 15, 34, 35, 116);
    }
}