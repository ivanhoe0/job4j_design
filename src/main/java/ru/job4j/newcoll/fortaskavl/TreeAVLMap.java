package ru.job4j.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean put(T key, V value) {
        var result = false;
        if (Objects.nonNull(key)) {
            root = put(root, key, value);
            result = true;
        }
        return result;
    }

    private Node put(Node node, T key, V value) {
        Node result = new Node(key, value);
        if (Objects.nonNull(node)) {
            int cmp = key.compareTo(node.key);
            if (cmp > 0) {
                node.right = put(node.right, key, value);
            } else if (cmp < 0) {
                node.left = put(node.left, key, value);
            } else {
                node.value = value;
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    private boolean contains(T key) {
        if (Objects.nonNull(root)) {
            return Objects.nonNull(find(root, key));
        }
        return false;
    }

    private Node find(Node node, T key) {
        if (Objects.isNull(node)) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        } else if (cmp > 0) {
            return find(node.right, key);
        } else {
            return find(node.left, key);
        }
    }

    public boolean remove(T key) {
        var result = false;
        if (Objects.nonNull(root) && Objects.nonNull(key) && contains(key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T key) {
        if (Objects.isNull(node)) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (Objects.isNull(node.right)) {
                return node.left;
            } else if (Objects.isNull(node.left)) {
                return node.right;
            } else {
                if (node.left.height > node.right.height) {
                    var heir = maximum(node);
                    node.key = heir.key;
                    node.value = heir.value;
                    node.left = remove(node.left, heir.key);
                } else {
                    var heir = minimum(node);
                    node.key = heir.key;
                    node.value = heir.value;
                    node.right = remove(node.right, heir.key);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    public V get(T key) {
        var node = find(root, key);
        return Objects.nonNull(node) ? node.value : null;
    }

    public Set<T> keySet() {
        var set = new HashSet<T>();
        return keySet(root, set);
    }

    private Set<T> keySet(Node node, Set<T> set) {
        if (Objects.nonNull(node)) {
            keySet(node.left, set);
            set.add(node.key);
            keySet(node.right, set);
        }
        return set;
    }

    public Collection<V> values() {
        var collection = new ArrayList<V>();
        return values(root, collection);
    }

    private Collection<V> values(Node node, Collection<V> collection) {
        if (Objects.nonNull(node)) {
            values(node.left, collection);
            collection.add(node.value);
            values(node.right, collection);
        }
        return collection;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        } else if (node.balanceFactor < -1) {
            if (node.left.balanceFactor <= 0) {
                result = rightRotation(node);
            } else {
                result = leftRightCase(node);
            }
        }
        return result;
    }

    private Node maximum(Node node) {
        if (Objects.isNull(node.right)) {
            return node;
        } else {
            return maximum(node.right);
        }
    }

    private Node minimum(Node node) {
        if (Objects.isNull(node.left)) {
            return node;
        } else {
            return minimum(node.left);
        }
    }

    private void updateHeight(Node node) {
        int leftHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
        node.balanceFactor = rightHeight - leftHeight;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
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