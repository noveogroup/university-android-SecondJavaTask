package com.noveogroup.model;

import java.io.Serializable;

public class TreeElement implements Serializable {

    private int id;
    private String text;

    public TreeElement(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
