package com.noveogroup.item;

public final class Peach extends TreeItem {
    public Peach() {
        state = false;
    }
    public void changeState() {
        state = !state;
    }
    public boolean getState() {
        return state;
    }
}
