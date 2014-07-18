package com.noveogroup.item;

import java.io.Serializable;

public abstract class TreeItem implements Serializable{
    boolean state;
    public abstract void changeState();
    public abstract boolean getState();
}
