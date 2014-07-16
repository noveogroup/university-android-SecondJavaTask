package com.noveogroup.main;

import com.noveogroup.exception.BinaryTreeException;
import com.noveogroup.exception.ElementAlreadyExistsException;
import com.noveogroup.exception.NoSuchTreeElementException;
import com.noveogroup.model.ElementImpl;
import com.noveogroup.model.TreeElement;
import com.noveogroup.tree.BinaryTree;
import com.noveogroup.tree.BinaryTreeImpl;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //create BinaryTree
        BinaryTree<Integer, TreeElement> tree = generateTree();
        printTree(tree);

        System.out.println();
        try {
            tree.removeElement(5);
        } catch (NoSuchTreeElementException e) {
            System.out.println(e);
        }
        printTree(tree);

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

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(baos).writeObject(tree);
        } catch (IOException e) { }
        byte[] array = baos.toByteArray();
        System.out.println("Tree size in bytes: " + array.length);

        try {
            @SuppressWarnings("unchecked")
            BinaryTree<Integer, TreeElement> tree2 =
                    (BinaryTree<Integer, TreeElement>)new ObjectInputStream(new ByteArrayInputStream(array)).readObject();
            printTree(tree2);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    static void printTree(BinaryTree<?, ?> tree) {
        for (Iterator<?> iterator = tree.getIterator(); iterator.hasNext(); )
            System.out.println(iterator.next());
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
