package com.noveogroup.tree

/**
 * Sample implementation.
 */
class BinaryTreeImpl<K, V> : BinaryTree<K, V> {

    final override val iterator: Iterator<V>
        get() = object : Iterator<V> {
            override fun hasNext(): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun next(): V {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

    override fun addElement(key: K, element: V) {
        //TODO
    }

    override fun removeElement(key: K) {
        //TODO
    }

}
