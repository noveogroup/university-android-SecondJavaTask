package com.noveogroup.exception;

public class TreeElementException extends BinaryTreeException {
    Object element;

    public TreeElementException(Object element, TreeExceptionType type) {
        super(type);
        this. element = element;
    }

    public Object getElement() {
        return element;
    }
}
