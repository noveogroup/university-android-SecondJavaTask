package com.noveogroup.main;

import com.noveogroup.exception.*;
import com.noveogroup.item.Leaf;
import com.noveogroup.item.Peach;
import com.noveogroup.model.TreeElement;
import com.noveogroup.tree.BinaryTree;
import com.noveogroup.item.TreeItem;
import com.noveogroup.tree.BinaryTreeImpl;

import java.io.*;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        BinaryTreeImpl<Integer, TreeItem> tree = new BinaryTreeImpl<Integer, TreeItem>();
        Iterator<TreeElement<Integer, TreeItem>> iterator;
        try {
            System.out.println("Add some elements to the tree.");
            tree.addElement(100, new Leaf());
            tree.addElement(50, new Peach());
            tree.addElement(70, new Peach());
            tree.addElement(200, new Leaf());
            tree.addElement(250, new Peach());
            tree.addElement(170, new Leaf());

            System.out.print(tree.count() + " elements: ");
            iterator = tree.getIterator();
            while(iterator.hasNext()) {
                System.out.print(iterator.next().getKey() + " ");
            }
            System.out.println("\n==========");

            System.out.println("Write the tree in file.");
            FileOutputStream file_out = new FileOutputStream("tree.dat");
            ObjectOutputStream stream_out = new ObjectOutputStream(file_out);
            stream_out.writeObject(tree);
            stream_out.close();

            System.out.println("Read the tree from file.");
            FileInputStream file_in = new FileInputStream("tree.dat");
            ObjectInputStream stream_in = new ObjectInputStream(file_in);
            @SuppressWarnings("unchecked")
            BinaryTree<Integer, TreeItem> tree_from_file = (BinaryTree<Integer, TreeItem>)stream_in.readObject();
            stream_in.close();
            System.out.print("Elements of the tree from file: ");
            iterator = tree_from_file.getIterator();
            while(iterator.hasNext()) {
                System.out.print(iterator.next().getKey() + " ");
            }
            System.out.println("\n==========");

            System.out.println("Try to add one more element.");
            tree.addElement(200, new Peach());

        } catch (TreeElementException exception)  {
            if(exception.getType() == TreeExceptionType.ALREADY_EXISTS) {
                @SuppressWarnings("unchecked")
                TreeElement<Integer, TreeItem> element = (TreeElement<Integer, TreeItem>) exception.getElement();
                System.out.println("Exception: Element "
                        + element.getKey()
                        + " you are willing to add is already on the tree. It's a "
                        + element.getItem().getClass().getSimpleName() + ".");
            }
        }

        try {
            System.out.println("==========");
            System.out.println("Remove the root element.");
            tree.removeElement(100);
            System.out.print(tree.count() + " elements: ");
            iterator = tree.getIterator();
            while(iterator.hasNext()) {
                System.out.print(iterator.next().getKey() + " ");
            }
            System.out.println("");

            System.out.println("Remove one more element.");
            tree.removeElement(170);
            System.out.print(tree.count() + " elements: ");
            iterator = tree.getIterator();
            while(iterator.hasNext()) {
                System.out.print(iterator.next().getKey() + " ");
            }
            System.out.println("");

            System.out.println("Try to remove one more element.");
            tree.removeElement(500);

        } catch (BinaryTreeException exception) {
            if(exception.getType() == TreeExceptionType.IS_ABSENT) {
                int key = (Integer)(((TreeElementException)exception).getElement());
                System.out.println("Exception: Element " + key
                                   + " you are willing to remove doesn't exist at all. Be more attentive, please!");
            }
        }

        try {
            System.out.println("==========");
            System.out.println("Try to remove a Peach.");
            tree.removeElement(250);

        } catch (BinaryTreeException exception) {
            if(exception.getType() == TreeExceptionType.IMPROPER_STATE) {
                if((((TreeElementException)exception).getElement()).getClass().getSimpleName().equals("Peach")) {
                    System.out.println("Exception: The peach is unripe yet. Wait for some time before taking it.");
                }
            }
        }

        tree.changeState(250);
        System.out.println("Change the state. Now the peach is ready to be gathered.");
        try {
            System.out.println("Try to remove the Peach once again.");
            tree.removeElement(250);
            System.out.print(tree.count() + " elements: ");
            iterator = tree.getIterator();
            while(iterator.hasNext()) {
                System.out.print(iterator.next().getKey() + " ");
            }
            System.out.println("\nThe Peach has been taken off.\nBon appetit!");

        } catch (BinaryTreeException exception) {
            System.out.println("This string is never printed.");
        }
    }
}
