package ru.job4j.newcoll;

public interface Queue<T> {
    T poll();

    void push(T value);

    boolean isEmpty();
}
