package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class SimpleGeneratorTest {
    @Test
    void whenTwoPairsKeyValue() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";
        assertThat(expected).isEqualTo(new SimpleGenerator().produce(template, map));
    }

    @Test
    void whenThereIsNotSufficientAmountOfKeysInMapThenGetException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        Generator simpleGenerator = new SimpleGenerator();
        assertThatThrownBy(() -> simpleGenerator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenThereIsMoreKeysInMapThenGetException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        map.put("key", "value");
        Generator simpleGenerator = new SimpleGenerator();
        assertThatThrownBy(() -> simpleGenerator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }
}