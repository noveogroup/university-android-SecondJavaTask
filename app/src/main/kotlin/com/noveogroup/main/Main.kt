package com.noveogroup.main

import com.noveogroup.exception.BinaryTreeException
import com.noveogroup.model.ElementForExample
import com.noveogroup.model.TreeElement
import com.noveogroup.tree.BinaryTreeImpl


fun main(args: Array<String>) {
    //You can check your implementation here.
    //For example:

    //create BinaryTree
    val tree = BinaryTreeImpl<Int, TreeElement>()
    try {
        //add element
        tree.addElement(10, ElementForExample())
        //remove element
        tree.removeElement(10)
        //get iterator
        val iterator = tree.iterator
        while (iterator.hasNext()) {
            val next = iterator.next()
            println(next.id)
        }
    } catch (error: BinaryTreeException) {
        //handle the exception
    }

}
