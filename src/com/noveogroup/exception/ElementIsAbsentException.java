package com.noveogroup.exception;

public class ElementIsAbsentException extends TreeElementException {
    public ElementIsAbsentException(Object element) {
        super(element, TreeExceptionType.IS_ABSENT);
    }
}
