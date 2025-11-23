package ru.job4j.newcoll.tree;
import ru.job4j.newcoll.Queue;
import ru.job4j.newcoll.SimpleQueue;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Queue<Node<T>> queue = new SimpleQueue<>();
        int count = 0;
        queue.push(root);
        while (!queue.isEmpty()) {
            queue.poll().getChildren().forEach(queue::push);
            count++;
        }
        return count;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Queue<Node<T>> queue = new SimpleQueue<>();
        List<T> result = new ArrayList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            var node = queue.poll();
            node.getChildren().forEach(queue::push);
            result.add(node.getValue());
        }
        return result;
    }
}