package ru.job4j.collection.binarytree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        Node previous;
        Node current = node;
        while (true) {
            previous = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
                if (current == null) {
                    previous.left = new Node(key);
                    return true;
                }
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
                if (current == null) {
                    previous.right = new Node(key);
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    private Node find(Node node, T key) {
        Node current = node;
        while (true) {
            if (key.compareTo(current.key) == 0) {
                return current;
            } else if (key.compareTo(current.key) < 0) {
                current = current.left;
                if (current == null) {
                    return null;
                }
            } else {
                current = current.right;
                if (current == null) {
                    return null;
                }
            }
        }
    }

    public boolean remove(T key) {
        /* Метод будет реализован в следующих уроках */
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        return inPreOrder(root, result);
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            inPreOrder(localRoot.left, list);
            inPreOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        return inPostOrder(root, result);
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
        return list;
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        Node result = node;
        while (true) {
            if (result.left == null) {
                return result;
            }
            result = result.left;
        }
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        Node result = node;
        while (true) {
            if (result.right == null) {
                return result;
            }
            result = result.right;
        }
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return key.toString();
        }
    }
}