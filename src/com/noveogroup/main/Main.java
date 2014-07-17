package com.noveogroup.main;

import com.noveogroup.exception.BinaryTreeException;
import com.noveogroup.item.Leaf;
import com.noveogroup.item.Peach;
import com.noveogroup.model.TreeElement;
import com.noveogroup.tree.BinaryTree;
import com.noveogroup.item.TreeItem;
import com.noveogroup.tree.BinaryTreeImpl;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer, TreeItem> tree = new BinaryTreeImpl<Integer, TreeItem>();
        try {
            tree.addElement(100, new Leaf());
            tree.addElement(50, new Leaf());
            tree.addElement(70, new Peach());
            tree.addElement(200, new Leaf());
            tree.addElement(250, new Peach());
            tree.addElement(170, new Peach());

            Iterator<TreeElement<Integer, TreeItem>> iterator = tree.getIterator();
            while(iterator.hasNext()) {
                System.out.println(iterator.next().getKey());
            }
            System.out.println("==========================");
            tree.removeElement(100);
            iterator = tree.getIterator();
            while(iterator.hasNext()) {
                System.out.println(iterator.next().getKey());
            }

            System.out.println("==========================");
            tree.removeElement(170);
            iterator = tree.getIterator();
            while(iterator.hasNext()) {
                System.out.println(iterator.next().getKey());
            }
        } catch (BinaryTreeException e) {

        }
    }
}
