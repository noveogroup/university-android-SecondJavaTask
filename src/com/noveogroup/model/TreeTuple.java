package com.noveogroup.model;

public class TreeTuple<K, V> {
    public TreeElement<K, V> child;
    public TreeElement<K, V> parent;
    public boolean is_big;

    public TreeTuple(TreeElement<K, V> child, TreeElement<K, V> parent, boolean is_big) {
        this.child = child;
        this.parent = parent;
        this.is_big = is_big;
    }
}
