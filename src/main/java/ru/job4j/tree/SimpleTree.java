package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> result = findBy(parent);
        boolean parentIsPresent = result.isPresent();
        boolean childIsAbsent = false;
        if (parentIsPresent) {
            childIsAbsent = findBy(child).isEmpty();
        }
        if (childIsAbsent) {
            result.get().children.add(new Node<>(child));
        }
        return parentIsPresent && childIsAbsent;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.value.equals(value)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }
}
