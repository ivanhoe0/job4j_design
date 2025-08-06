package ru.job4j.ood.productstore;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    void whenRemainderLessThen25Percent() {
        var food = new Food(
                "Bread",
                new GregorianCalendar(2025, Calendar.AUGUST, 1, 15, 35),
                new GregorianCalendar(2025, Calendar.AUGUST, 15, 15, 35),
                325.5,
                0
        );
        var shop = new Shop();
        assertThat(shop.add(food)).isTrue();
    }

    @Test
    void whenRemainderMoreThen75percent() {
        var food = new Food(
                "Bread",
                new GregorianCalendar(2025, Calendar.AUGUST, 1, 15, 35),
                new GregorianCalendar(2025, Calendar.AUGUST, 7, 15, 35),
                325.5,
                0
        );
        var shop = new Shop();
        assertThat(shop.add(food)).isTrue();
        assertThat(food.getDiscount()).isEqualTo(20);
    }
}