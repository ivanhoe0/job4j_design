package ru.job4j.newcoll.tree;
import ru.job4j.collection.SimpleStack;
import ru.job4j.newcoll.Queue;
import ru.job4j.newcoll.SimpleQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        var node = findByKey(root, parent);
        boolean result = false;
        if (node.isPresent() && findByKey(root, child).isEmpty()) {
            result = node.get().getChildren().add(new Node<>(child));
        }
        return result;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Optional<Node<T>> result = Optional.empty();
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            var node = stack.pop();
            if (node.getValue().equals(key)) {
                result = Optional.of(node);
                break;
            }
            node.getChildren().forEach(stack::push);
        }
        return result;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        if (root.getValue().equals(key)) {
            return Optional.of(root);
        }
        var queue = new SimpleQueue<Node<T>>();
        queue.push(root);
        while (!queue.isEmpty()) {
            var node = queue.poll();
            for (var child : node.getChildren()) {
                if (child.getValue().equals(key)) {
                    node.getChildren().remove(child);
                    return Optional.of(child);
                }
            }
            node.getChildren().forEach(queue::push);
        }
        return Optional.empty();
    }
}