package com.noveogroup.model;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface DataStructure {

    enum Operation { ADD, REMOVE, GET, SET, ITERATE }
    enum StructureType { ARRAY, LINKED_LIST, TREE, HASH_MAP }

    public Operation[] operations();
    public StructureType type();

}
