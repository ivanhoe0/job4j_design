package ru.job4j.ood.productstore;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class TrashTest {
    @Test
    void whenRemainderIsMoreThenOne() {
        var food = new Food(
                "Bread",
                new GregorianCalendar(2025, Calendar.AUGUST, 1, 15, 35),
                new GregorianCalendar(2025, Calendar.AUGUST, 6, 15, 35),
                35.5,
                0
        );
        var trash = new Trash();
        assertThat(trash.add(food)).isTrue();
    }

    @Test
    void whenRemainderIsLessThenOne() {
        var food = new Food(
                "Bread",
                new GregorianCalendar(2025, Calendar.AUGUST, 1, 15, 35),
                new GregorianCalendar(2025, Calendar.AUGUST, 8, 15, 35),
                35.5,
                0
        );
        var trash = new Trash();
        assertThat(trash.add(food)).isFalse();
    }
}