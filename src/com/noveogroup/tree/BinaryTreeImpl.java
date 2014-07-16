package com.noveogroup.tree;

import com.noveogroup.exception.BinaryTreeException;

import java.util.*;

public class BinaryTreeImpl<K extends Comparable<? super K>,V> implements BinaryTree<K,V> {

    private Node<K, V> root;

    @Override
    public void addElement(K key, V element) throws BinaryTreeException {
        Node<K, V> newNode = new Node<K, V>(key, element);
        if (root == null) {
            root = newNode;
        } else {
            addNode(root, newNode);
        }
    }

    private void addNode(Node<K, V> node, Node<K, V> newNode) throws BinaryTreeException {
        int compResult = newNode.key.compareTo(node.key);
        if (compResult == 0) {
            // TODO: Throw another exception
            throw new BinaryTreeException();
        } else if (compResult < 0) {
            if (node.left == null)
                node.left = newNode;
            else
                addNode(node.left, newNode);
        } else if (compResult > 0) {
            if (node.right == null)
                node.right = newNode;
            else
                addNode(node.right, newNode);
        }
    }

    @Override
    public void removeElement(K key) {
        if (root == null) {
            // TODO: throw new EmptyTreeException
        }
        root = remove(root, key);
    }

    /**
     * My implementation of algorithm from
     * <a href="http://ru.wikipedia.org/wiki/Двоичное_дерево_поиска">Wikipedia</a>
     */
    private Node<K, V> remove(Node<K, V> node, K key) {
        int compResult = node.key.compareTo(key);
        if (compResult == 0) {
            if (node.left == null && node.right == null) {
                return null;
            } else if ((node.left == null) ^ (node.right == null)) {
                if (node.left == null)
                    return node.right;
                else
                    return node.left;
            } else {
                if (node.right.left == null) {
                    node.right.left = node.left;
                    return node.right;
                } else {
                    Node<K, V> mostLeftParent = node.right;
                    while (mostLeftParent.left.left != null) mostLeftParent = mostLeftParent.left;
                    node.key = mostLeftParent.left.key;
                    node.value = mostLeftParent.left.value;
                    mostLeftParent.left = null;
                    return node.right;
                }
            }
        } else if (compResult < 0) {
            if (node.left == null) {
                // TODO: throw exception
            } else {
                node.left = remove(node.left, key);
            }
            return node;
        } else {
            if (node.right == null) {
                // TODO: throw exception
            } else {
                node.right = remove(node.right, key);
            }
            return node;
        }
    }

    @Override
    public Iterator<V> getIterator() {
        final List<V> values = new ArrayList<V>();
        if (root == null) {
            // TODO: throw new EmptyTreeException
        }
        collectValues(root, values);
        return new Iterator<V>() {

            int index;

            @Override
            public boolean hasNext() {
                return index < values.size();
            }

            @Override
            public V next() {
                return values.get(index++);
            }
        };
    }

    private void collectValues(Node<K, V> node, List<V> list) {
        if (node.left != null) collectValues(node.left, list);
        list.add(node.value);
        if (node.right != null) collectValues(node.right, list);
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
