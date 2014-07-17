package com.noveogroup.model;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface DataStructure {

    int OPERATION_ADD = 0,
        OPERATION_REMOVE = 1,
        OPERATION_GET = 2,
        OPERATION_SET = 3,
        OPERATION_ITERATE = 4;
    String[] OPERATIONS = { "add", "remove", "get", "set", "iterate" };

    public int[] value();

}
