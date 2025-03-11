package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count + 1 > capacity * LOAD_FACTOR) {
            expand();
        }
        var result = putValue(key, value, table);
        if (result) {
            count++;
            modCount++;
        }
        return result;
    }

    @Override
    public V get(K key) {
        var hashCode = Objects.hashCode(key);
        var index = indexFor(hash(hashCode));
        MapEntry<K, V> result = table[index];
        var notEmpty = result != null && result.hashCode == hashCode
                && Objects.equals(result.key, key);
        return notEmpty ? result.value : null;
    }

    @Override
    public boolean remove(K key) {
        var hashCode = Objects.hashCode(key);
        var index = indexFor(hash(hashCode));
        MapEntry<K, V> m = table[index];
        var result = m != null;
        if (result
                && m.hashCode == hashCode
                && Objects.equals(m.key, key)) {
            table[index] = null;
            count--;
            modCount++;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int currentModCount = modCount;
            private int point = 0;
            @Override
            public boolean hasNext() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private boolean putValue(K key, V value, MapEntry<K, V>[] array) {
        int hash = hash(Objects.hashCode(key));
        int size = array.length;
        int index = hash & (size - 1);
        var result = array[index] == null;
        if (result) {
            array[index] = new MapEntry<>(key, value);
        }
        return result;
    }

    private void expand() {
        var newCapacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        Iterator<K> iterator = iterator();
        K key;
        while (iterator.hasNext()) {
            key = iterator.next();
            putValue(key, get(key), newTable);
        }
        table = newTable;
        capacity = newCapacity;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;
        int hashCode;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
            hashCode = Objects.hashCode(key);
        }
    }
}
