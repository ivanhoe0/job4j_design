package ru.job4j.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean put(T key, V value) {
        // TODO реализуйте метод fsfeq
        return false;
    }

    public boolean remove(T key) {
        // TODO реализуйте метод
        return false;
    }

    public V get(T key) {
        // TODO реализуйте метод
        return null;
    }

    public Set<T> keySet() {
        // TODO реализуйте метод
        return null;
    }

    public Collection<V> values() {
        // TODO реализуйте метод
        return null;
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}