package ru.job4j.ood.productstore;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class WareHouseTest {
    @Test
    void whenRemainderIsLessThen25() {
        var food = new Food(
                "Bread",
                new GregorianCalendar(2025, Calendar.AUGUST, 5, 15, 35),
                new GregorianCalendar(2025, Calendar.AUGUST, 15, 15, 35),
                35.5,
                0
        );
        var warehouse = new WareHouse();
        assertThat(warehouse.add(food)).isTrue();
    }

    @Test
    void whenRemainderIsMoreThen25() {
        var food = new Food(
                "Bread",
                new GregorianCalendar(2025, Calendar.JULY, 28, 15, 35),
                new GregorianCalendar(2025, Calendar.AUGUST, 15, 15, 35),
                35.5,
                0
        );
        var warehouse = new WareHouse();
        assertThat(warehouse.add(food)).isFalse();
    }
}