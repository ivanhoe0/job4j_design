package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {
    @Test
    void whenFizz() {
        String expected = "Fizz";
        assertThat(expected).isEqualTo(Fool.check(12));
    }

    @Test
    void whenBuzz() {
        String expected = "Buzz";
        assertThat(expected).isEqualTo(Fool.check(25));
    }

    @Test
    void whenFizzBuzz() {
        String expected = "FizzBuzz";
        assertThat(expected).isEqualTo(Fool.check(45));
    }

    @Test
    void whenNotFizzNorBuzz() {
        String expected = "14";
        assertThat(expected).isEqualTo(Fool.check(14));
    }
}