package com.noveogroup.main;

import com.noveogroup.exception.BinaryTreeException;
import com.noveogroup.exception.ElementAlreadyExistsException;
import com.noveogroup.exception.NoSuchTreeElementException;
import com.noveogroup.model.ElementImpl;
import com.noveogroup.model.TreeElement;
import com.noveogroup.tree.BinaryTree;
import com.noveogroup.tree.BinaryTreeImpl;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //create BinaryTree
        BinaryTree<Integer, TreeElement> tree = generateTree();

        for (Iterator<TreeElement> iterator = tree.getIterator(); iterator.hasNext(); )
            System.out.println(iterator.next());

        System.out.println();
        try {
            tree.removeElement(5);
        } catch (NoSuchTreeElementException e) {
            System.out.println(e);
        }
        for (Iterator<TreeElement> iterator = tree.getIterator(); iterator.hasNext(); )
            System.out.println(iterator.next());

        try {
            tree.addElement(2, new ElementImpl(2, "2"));
        } catch (BinaryTreeException e) {
            System.out.println(e);
        }

        try {
            tree.removeElement(20);
        } catch (BinaryTreeException e) {
            System.out.println(e);
        }
    }

    static BinaryTree<Integer, TreeElement> generateTree() {
        List<TreeElement> list = new ArrayList<TreeElement>();
        String[] numbers = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" };
        for (int i = 0; i < numbers.length; i++) {
            list.add(new ElementImpl(i + 1, numbers[i]));
        }
        Random rnd = new Random(0);
        Collections.shuffle(list, rnd);
        BinaryTree<Integer, TreeElement> tree = new BinaryTreeImpl<Integer, TreeElement>();
        try {
            for (TreeElement elem : list)
                tree.addElement(elem.getId(), elem);
        } catch (ElementAlreadyExistsException e) {
            // It's impossible
            throw new RuntimeException("Something goes wrong", e);
        }
        return tree;
    }

}
