package com.noveogroup.tree

import com.noveogroup.exception.BinaryTreeException

/**
 * Binary tree interface. Please add your exceptions to methods.
 */
interface BinaryTree<K, V> {
    val iterator: Iterator<V>

    @Throws(BinaryTreeException::class)
    fun addElement(key: K, element: V)

    @Throws(BinaryTreeException::class)
    fun removeElement(key: K)
}
