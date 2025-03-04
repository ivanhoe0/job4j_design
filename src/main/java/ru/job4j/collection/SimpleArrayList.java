package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    private T[] grow() {
        return Arrays.copyOf(container,
                container.length == 0 ? 1
                        : container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T result = container[index];
        if (size == container.length) {
            container = grow();
        }
        System.arraycopy(container, index, container, index + 1, size - index - 1);
        container[index] = newValue;
        return result;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T result = container[index];
        System.arraycopy(container, index + 1, container, index, size - 1 - index);
        container[size - 1] = null;
        size--;
        modCount++;
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private int point = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}
