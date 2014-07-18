package com.noveogroup.tree;

import com.noveogroup.model.TreeElement;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeIterator<K, V> implements Iterator<TreeElement<K, V>> {
    TreeElement<K, V> next_element;

    public TreeIterator(TreeElement<K, V> starting_element) {
        next_element = starting_element;
    }

    @Override
    public boolean hasNext() {
        if(next_element != null) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public TreeElement<K, V> next() {
        if(next_element != null) {
            TreeElement<K, V> temp = next_element;
            findNext();
            return temp;
        }
        else {
            throw (new NoSuchElementException());
        }

    }

    @Override
    public void remove() {
        throw (new UnsupportedOperationException());
    }

    protected void findNext() {
        if(next_element.getChild(false) != null) {
            next_element = next_element.getChild(false);
            return;
        }
        if(next_element.getChild(true) != null) {
            next_element = next_element.getChild(true);
            return;
        }
        goBack();
    }

    protected void goBack() {
        if(next_element.getParent() == null) {
            next_element = null;
            return;
        }
        if(next_element.getParent().getChild(false) == next_element
           && next_element.getParent().getChild(true) != null) {
            next_element = next_element.getParent().getChild(true);
            return;
        }
        next_element = next_element.getParent();
        goBack();
    }
}
