package com.noveogroup.exception;

import com.noveogroup.model.TreeElement;

public class ElementAlreadyExistsException extends TreeElementException {
    public ElementAlreadyExistsException(Object element) {
        super(element, TreeExceptionType.ALREADY_EXISTS);
    }
}
