package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "third", "fourth", "fifth");
        assertThat(list).isNotEmpty()
                .allSatisfy(e -> assertThat(e.length()).isGreaterThan(3))
                .hasSize(5)
                .containsAnyOf("first", "200", "one")
                .doesNotContain("three")
                .startsWith("first")
                .endsWith("fourth", "fifth");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "third", "fourth", "fifth");
        assertThat(set).isNotEmpty()
                .anyMatch(e -> e.length() == 5)
                .noneMatch(e -> e.length() > 10)
                .contains("third");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "third", "fourth", "fifth");
        assertThat(map).hasSize(5)
                .containsKeys("first", "fifth")
                .containsValues(3, 4)
                .containsEntry("third", map.get("third"));
    }
}