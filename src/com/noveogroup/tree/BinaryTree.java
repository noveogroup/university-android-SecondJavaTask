package com.noveogroup.tree;

import com.noveogroup.exception.BinaryTreeException;
import com.noveogroup.model.TreeElement;

import java.util.Iterator;

/**
 * Binary tree interface. Please add your exceptions to methods.
 */
public interface BinaryTree <K,V> {
    void addElement(K key, V item) throws BinaryTreeException;
    void removeElement(K key) throws BinaryTreeException;
    Iterator<TreeElement<K, V>> getIterator();
    int count();
}
