package com.noveogroup.tree;

import com.noveogroup.exception.BinaryTreeException;

import java.util.Iterator;

/**
 * Binary tree interface. Please add your exceptions to methods.
 */
public interface BinaryTree <K extends Comparable<? super K>,V> {
    void addElement(K key, V element) throws BinaryTreeException;
    void removeElement(K key) throws BinaryTreeException;
    Iterator<V> getIterator();
}
