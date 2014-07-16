package com.noveogroup.model;

/**
 * Simple implementation for demo proposes.
 * You should use your own classes.
 */
public class ElementImpl implements TreeElement {

    private int id;
    private String text;

    public ElementImpl(int id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "ElementImpl{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
