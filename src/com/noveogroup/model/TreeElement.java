package com.noveogroup.model;

import java.io.Serializable;

/**
 * Interface for tree elements. You can change it as you want.
 */
public interface TreeElement<K, V> extends Serializable{
    K getKey();
    V getItem();
    TreeElement<K, V> getParent();
    TreeElement<K, V> getChild(boolean is_big);
    void setParent(TreeElement<K, V> element);
    void setChild(TreeElement<K, V> element, boolean is_big);
}
