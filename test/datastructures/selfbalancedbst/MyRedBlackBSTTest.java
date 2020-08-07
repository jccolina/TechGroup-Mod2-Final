package datastructures.selfbalancedbst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import datastructures.linkedlist.MyLinkedList;
import datastructures.selfbalancedbst.MyColorNode.Color;
import org.junit.Assert;
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

        assertEquals("[2.03.04.06.07.08.09.010.0]", myTree.toString());
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

    @Test
    public void testGetValuesGreaterThanRightSide() {
        MyRedBlackBST myTree = new MyRedBlackBST();
        myTree.add(50, "luis");
        myTree.add(30, "brenda");
        myTree.add(40, "carlos");
        myTree.add(20, "saul");
        myTree.add(55, "ana");
        // Expected values:
        myTree.add(60, "maria");
        myTree.add(65, "cecilia");
        myTree.add(70, "javier");
        myTree.add(80, "armando");
        myTree.add(75, "rosario");
        MyLinkedList actual = myTree.getValuesGreaterThan(60);

        assertEquals( 5, actual.size());
        assertTrue(actual.contains("javier"));
        assertTrue(actual.contains("armando"));
        assertTrue(actual.contains("rosario"));
        assertTrue(actual.contains("maria"));
        assertTrue(actual.contains("cecilia"));
    }

    @Test
    public void testGetValuesGreaterThanLeftSide() {
        MyRedBlackBST myTree = new MyRedBlackBST();
        myTree.add(30, "brenda");
        myTree.add(20, "saul");
        // Expected values:
        myTree.add(50, "luis");
        myTree.add(40, "carlos");
        myTree.add(55, "ana");
        myTree.add(60, "maria");
        myTree.add(65, "cecilia");
        MyLinkedList actual = myTree.getValuesGreaterThan(40);

        assertEquals( 5, actual.size());
        assertTrue(actual.contains("maria"));
        assertTrue(actual.contains("cecilia"));
        assertTrue(actual.contains("luis"));
        assertTrue(actual.contains("carlos"));
        assertTrue(actual.contains("ana"));
    }

    @Test
    public void testGetValuesLessThanLeftSide() {
        MyRedBlackBST myTree = new MyRedBlackBST();
        myTree.add(60, "maria");
        myTree.add(65, "cecilia");
        myTree.add(70, "javier");
        myTree.add(75, "rosario");
        // Expected values:
        myTree.add(55, "ana");
        myTree.add(50, "luis");
        myTree.add(40, "carlos");
        myTree.add(30, "brenda");
        MyLinkedList actual = myTree.getValuesLessThan(55);

        assertEquals( 4, actual.size());
        assertTrue(actual.contains("ana"));
        assertTrue(actual.contains("luis"));
        assertTrue(actual.contains("carlos"));
        assertTrue(actual.contains("brenda"));
    }

    @Test
    public void testGetValuesLessThanRightSide() {
        MyRedBlackBST myTree = new MyRedBlackBST();
        myTree.add(70, "javier");
        myTree.add(75, "rosario");
        // Expected values:
        myTree.add(65, "cecilia");
        myTree.add(60, "maria");
        myTree.add(55, "ana");
        myTree.add(50, "luis");
        myTree.add(40, "carlos");
        myTree.add(30, "brenda");
        MyLinkedList actual = myTree.getValuesLessThan(65);

        assertEquals( 6, actual.size());
        assertTrue(actual.contains("maria"));
        assertTrue(actual.contains("cecilia"));
        assertTrue(actual.contains("ana"));
        assertTrue(actual.contains("luis"));
        assertTrue(actual.contains("carlos"));
        assertTrue(actual.contains("brenda"));
    }

    @Test
    public void getValuesLeftOpenInterval() {
        MyRedBlackBST myTree = new MyRedBlackBST();
        myTree.add(75, "rosario");
        myTree.add(30, "brenda");
        // Expected values:
        myTree.add(70, "javier");
        myTree.add(60, "maria");
        myTree.add(50, "luis");
        myTree.add(55, "ana");
        myTree.add(40, "carlos");
        myTree.add(65, "cecilia");
        MyLinkedList actual = myTree.getValuesLeftOpenInterval(30, 70);

        assertEquals( 6, actual.size());
        assertTrue(actual.contains("javier"));
        assertTrue(actual.contains("maria"));
        assertTrue(actual.contains("luis"));
        assertTrue(actual.contains("ana"));
        assertTrue(actual.contains("carlos"));
        assertTrue(actual.contains("cecilia"));
    }

    @Test
    public void getValuesOpenInterval() {
        MyRedBlackBST myTree = new MyRedBlackBST();
        myTree.add(75, "rosario");
        myTree.add(30, "brenda");
        myTree.add(70, "javier");
        // Expected values:
        myTree.add(60, "maria");
        myTree.add(50, "luis");
        myTree.add(55, "ana");
        myTree.add(40, "carlos");
        myTree.add(65, "cecilia");
        MyLinkedList actual = myTree.getValuesOpenInterval(30, 70);

        assertEquals( 5, actual.size());
        assertTrue(actual.contains("maria"));
        assertTrue(actual.contains("luis"));
        assertTrue(actual.contains("ana"));
        assertTrue(actual.contains("carlos"));
        assertTrue(actual.contains("cecilia"));
    }

    @Test
    public void getHigherValuesOver() {
        MyRedBlackBST myTree = new MyRedBlackBST();
        myTree.add(75, "rosario");
        myTree.add(30, "brenda");
        myTree.add(70, "javier");
        myTree.add(60, "maria");
        myTree.add(50, "luis");
        myTree.add(55, "ana");
        myTree.add(40, "carlos");
        myTree.add(65, "cecilia");
        // Expected values:
        myTree.add(80, "raul");
        myTree.add(80, "jorge");
        MyLinkedList actual = myTree.getHigherValuesOver(30);

        assertEquals( 2, actual.size());
        assertTrue(actual.contains("raul"));
        assertTrue(actual.contains("jorge"));
    }
}
