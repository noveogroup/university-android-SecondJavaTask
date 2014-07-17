package com.noveogroup.exception;

/**
 * This is the base exception of your binary tree.
 * You can change it and create subclasses (ElementAlreadyExistsException, for example).
 */
public class BinaryTreeException extends Exception {
    protected TreeExceptionType type;

    public BinaryTreeException(TreeExceptionType type) {
        this.type = type;
    }

    public TreeExceptionType getType() {
        return type;
    }
}
