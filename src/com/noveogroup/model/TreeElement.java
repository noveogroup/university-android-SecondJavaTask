package com.noveogroup.model;

import java.io.Serializable;

/**
 * Interface for tree elements. You can change it as you want.
 */
public interface TreeElement extends Serializable {
    int getId();
    String getText();
}
