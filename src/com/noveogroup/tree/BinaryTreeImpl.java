package com.noveogroup.tree;

import com.noveogroup.exception.*;
import com.noveogroup.model.TreeElement;
import com.noveogroup.model.TreeElementImpl;
import com.noveogroup.model.TreeTuple;
import com.noveogroup.item.TreeItem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

/**
 * Sample implementation.
 */
public class BinaryTreeImpl<K extends Comparable<? super K>, V extends TreeItem>
             implements BinaryTree <K, V> {
    TreeElement<K, V> root;

    @Override
    public void addElement(K key, V item) throws ElementAlreadyExistsException {
        TreeElementImpl<K, V> element = new TreeElementImpl<K, V>(key, item);
        addElement(element, null);
    }

    @Override
    public void removeElement(K key) throws BinaryTreeException {
        TreeTuple<K, V> tuple = search(key, null);

        if(tuple.child == null) {
            throw (new ElementIsAbsentException(key));
        }
        else {
            if(!tuple.child.getItem().getState()) {
                throw (new ElementImproperStateException(tuple.child.getItem()));
            }
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
        return (new TreeIterator<K, V>(root));
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

    protected void addElement(TreeElement<K, V> element, TreeElement<K, V> starting_element)
                   throws ElementAlreadyExistsException {
        TreeTuple<K, V> tuple = search(element.getKey(), starting_element);
        if(tuple.child != null) {
            throw (new ElementAlreadyExistsException(tuple.child));
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
        boolean is_big = true;
        if(temp_parent != null) {
            is_big = (temp_parent.getChild(true) == temp);
        }

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

    @Override
    public int count() {
        Iterator<TreeElement<K, V>> iterator = getIterator();
        int n = 0;
        while(iterator.hasNext()) {
            iterator.next();
            ++n;
        }
        return n;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException{
        stream.defaultWriteObject();
        stream.writeInt(count());

    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
        System.out.println("Overall quantity of elements has been read. " +
                "The tree contains " + stream.readInt() + " elements.");
    }

    public void changeState(K key) {
        TreeTuple<K, V> tuple = search(key, null);
        if(tuple.child != null) {
            tuple.child.getItem().changeState();
        }
    }
}
