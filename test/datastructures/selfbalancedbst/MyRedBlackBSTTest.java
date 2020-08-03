package datastructures.selfbalancedbst;

import static org.junit.Assert.assertEquals;

import datastructures.selfbalancedbst.MyColorNode.Color;
import org.junit.Test;

public class MyRedBlackBSTTest {

    @Test
    public void testAdd() {
        MyRedBlackBST myTree = new MyRedBlackBST();
        myTree.add(8, new Object());
        myTree.add(10, new Object());
        myTree.add(6, new Object());
        myTree.add(2, new Object());
        myTree.add(5, new Object());
        myTree.add(7, new Object());
        myTree.add(9, new Object());
        myTree.add(3, new Object());

        assertEquals(8, myTree.getSize());
    }

    @Test
    public void testToString() {
        MyRedBlackBST myTree = new MyRedBlackBST();

        myTree.add(8, new Object());
        myTree.add(6, new Object());
        myTree.add(10, new Object());
        myTree.add(9, new Object());
        myTree.add(3, new Object());
        myTree.add(7, new Object());
        myTree.add(2, new Object());
        myTree.add(4, new Object());

        assertEquals("[234678910]", myTree.toString());
    }

    @Test
    public void numberOfBlackNodes() {
        MyRedBlackBST myTree = new MyRedBlackBST();
        myTree.add(8, new Object());
        myTree.add(6, new Object());
        myTree.add(10, new Object());
        myTree.add(9, new Object());
        myTree.add(3, new Object());
        myTree.add(7, new Object());
        myTree.add(2, new Object());
        myTree.add(4, new Object());
        myTree.add(11, new Object());
        myTree.add(20, new Object());
        myTree.add(15, new Object());
        myTree.add(30, new Object());
        myTree.add(1, new Object());
        myTree.add(25, new Object());
        myTree.add(27, new Object());
        myTree.add(31, new Object());
        myTree.add(32, new Object());
        myTree.add(33, new Object());
        myTree.add(34, new Object());
        myTree.add(35, new Object());
        myTree.add(36, new Object());
        myTree.add(37, new Object());
        myTree.add(38, new Object());
        myTree.add(39, new Object());

        MyColorNode root = myTree.getRoot();

        int leftCount = 1;
        MyColorNode aux = root;
        while (aux.getLeft() != null) {
            if (aux.getColor().equals(Color.BLACK)) {
                leftCount++;
            }
            aux = aux.getLeft();
        }

        int rightCount = 1;
        aux = root;
        while (aux.getRight() != null) {
            if (aux.getColor().equals(Color.BLACK)) {
                rightCount++;
            }
            aux = aux.getRight();
        }

        assertEquals(leftCount, rightCount);
    }
}
