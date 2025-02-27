package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkWhenParseWithoutString() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkWhenInvalidFormatOfString() {
        NameLoad nameLoad = new NameLoad();
        String keyValue = "Key:value";
        assertThatThrownBy(() -> nameLoad.parse(keyValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(keyValue);
    }

    @Test
    void whenEmptyKey() {
        NameLoad nameLoad = new NameLoad();
        String keyValue = "=Value";
        assertThatThrownBy(() -> nameLoad.parse(keyValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(keyValue)
                .hasMessageContaining("not contain a key");
    }

    @Test
    void whenEmptyValue() {
        NameLoad nameLoad = new NameLoad();
        String keyValue = "Key=";
        assertThatThrownBy(() -> nameLoad.parse(keyValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(keyValue, "not contain a value");
    }
}