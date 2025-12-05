package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AvlTreeTest {

    @Test
    void symmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenFindMaximum() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenFindMinimum() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void postOrderOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        assertThat(tree.inPostOrder()).containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void preorderOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        assertThat(tree.inPreOrder()).containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void containsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        assertThat(tree.contains(3)).isTrue();
        assertThat(tree.contains(15)).isFalse();
    }
}