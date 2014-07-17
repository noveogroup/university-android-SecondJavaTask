package com.noveogroup.model;

public class TreeElementImpl <K, V> implements TreeElement <K, V> {
    protected K key;
    protected V item;
    protected TreeElement <K, V> small_child;
    protected TreeElement <K, V> big_child;
    protected TreeElement <K, V> parent;

    public TreeElementImpl(K key, V item) {
        this.key = key;
        this.item = item;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getItem() {
        return item;
    }

    @Override
    public TreeElement<K, V> getParent() {
        return parent;
    }

    @Override
    public TreeElement<K, V> getChild(boolean is_big) {
        if(is_big) {
            return big_child;
        }
        else {
            return small_child;
        }
    }

    @Override
    public void setParent(TreeElement<K, V> element) {
        parent = element;
    }

    @Override
    public void setChild(TreeElement<K, V> element, boolean is_big) {
        if(is_big) {
            big_child = element;
        }
        else {
            small_child = element;
        }
    }
}
