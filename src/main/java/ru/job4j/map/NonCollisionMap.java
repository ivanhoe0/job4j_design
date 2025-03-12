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
        int index = indexByKey(key);
        var result = table[index] == null;
        if (result) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return result;
    }

    @Override
    public V get(K key) {
        var index = indexByKey(key);
        MapEntry<K, V> result = table[index];
        return findByKey(key) ? result.value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexByKey(key);
        boolean result = findByKey(key);
        if (result) {
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

    private int indexByKey(K key) {
        int hash = hash(Objects.hashCode(key));
        return indexFor(hash);
    }

    private boolean findByKey(K key) {
        var hashCode = Objects.hashCode(key);
        var index = indexByKey(key);
        MapEntry<K, V> result = table[index];
        return result != null && result.hashCode == hashCode
                && Objects.equals(result.key, key);
    }

    private void expand() {
        var newCapacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        for (var entry : table) {
            if (entry == null) {
                continue;
            }
            int hash = hash(Objects.hashCode(entry.key));
            int index = hash & (newCapacity - 1);
            var result = newTable[index] == null;
            if (result) {
                newTable[index] = new MapEntry<>(entry.key, entry.value);
            }
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
