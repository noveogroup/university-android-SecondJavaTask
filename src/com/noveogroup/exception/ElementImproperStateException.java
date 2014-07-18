package com.noveogroup.exception;

public class ElementImproperStateException extends TreeElementException {
    public ElementImproperStateException(Object element) {
        super(element, TreeExceptionType.IMPROPER_STATE);
    }
}
