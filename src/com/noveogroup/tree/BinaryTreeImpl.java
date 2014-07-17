package com.noveogroup.tree;

import com.noveogroup.exception.BinaryTreeException;
import com.noveogroup.model.TreeElement;
import com.noveogroup.model.TreeElementImpl;
import com.noveogroup.model.TreeTuple;
import com.noveogroup.item.TreeItem;

import java.util.Iterator;

/**
 * Sample implementation.
 */
public class BinaryTreeImpl<K extends Comparable<? super K>, V extends TreeItem> implements BinaryTree <K, V> {
    TreeElement<K, V> root;

    @Override
    public void addElement(K key, V item) throws BinaryTreeException {
        TreeElementImpl<K, V> element = new TreeElementImpl<K, V>(key, item);
        addElement(element, null);
    }

    @Override
    public void removeElement(K key) throws BinaryTreeException {
        TreeTuple<K, V> tuple = search(key, null);

        if(tuple.child == null) {
            System.out.println("Here an exception throw should be.");
        }
        else {
            if(tuple.child.getChild(false) != null) {
                putElement(tuple.child.getChild(false), tuple.parent, tuple.is_big);
                if(tuple.child.getChild(true) != null) {
                    addElement(tuple.child.getChild(true), tuple.child.getChild(false));
                }
            }
            else {
                if(tuple.child.getChild(true) != null) {
                    putElement(tuple.child.getChild(true), tuple.parent, tuple.is_big);
                }
                else {
                    putElement(null, tuple.parent, tuple.is_big);
                }
            }
        }
    }

    @Override
    public Iterator<TreeElement<K, V>> getIterator() {
        //TODO
        return null;
    }

    protected void putElement(TreeElement<K, V> child, TreeElement<K, V> parent, boolean is_big) {
        if(child != null) {
            child.setParent(parent);
        }
        if(parent != null) {
            parent.setChild(child, is_big);
        }
        else {
            root = child;
        }
    }

    protected void addElement(TreeElement<K, V> element, TreeElement<K, V> starting_element) throws BinaryTreeException {
        TreeTuple<K, V> tuple = search(element.getKey(), starting_element);
        if(tuple.child != null) {
            System.out.println("Here an exception throw should be.");
        }
        else {
            putElement(element, tuple.parent, tuple.is_big);
        }
    }

    protected TreeTuple<K, V> search(K key, TreeElement<K, V> starting_element) {
        if(starting_element == null) {
            starting_element = root;
        }
        if(starting_element == null) {
            return (new TreeTuple<K, V>(null, null, false));
        }

        TreeElement <K, V> temp = starting_element;
        TreeElement <K, V> temp_parent = temp.getParent();
        boolean is_big = (temp_parent.getChild(true) == temp);

        while(true) {
            if(temp == null || temp.getKey().compareTo(key) == 0) {
                return (new TreeTuple<K, V>(temp, temp_parent, is_big));
            }
            if(temp.getKey().compareTo(key) > 0) {
                is_big = false;
            }
            if(temp.getKey().compareTo(key) < 0) {
                is_big = true;
            }
            temp_parent = temp;
            temp = temp.getChild(is_big);
        }
    }
}
