package datastructures.hashmap;

import datastructures.arraylist.MyArrayList;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MyHashMapTest {
    @Test
    public void testHashSetCreation() {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>(5);

        assertEquals(5, myHashMap.getIndexSize());
        MyCircularDoublyLinkedList<MyEntry<String, Integer>> test = myHashMap.get(2);
        assertNotNull(myHashMap.get(0));
        assertNotNull(myHashMap.get(1));
        assertNotNull(myHashMap.get(2));
        assertNotNull(myHashMap.get(3));
        assertNotNull(myHashMap.get(4));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testHashSetGetNonExistingIndex() {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>(5);

        assertEquals(5, myHashMap.getIndexSize());
        MyCircularDoublyLinkedList<MyEntry<String, Integer>> test = myHashMap.get(2);
        myHashMap.get(5);
    }

    @Test
    public void testPutHashMapSuccess() {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>(5);

        myHashMap.put("first", 1);
        myHashMap.put("second", 5);
        myHashMap.put("third", 4);
        myHashMap.put("forth", 2);

        assertEquals(4, myHashMap.size());
    }

    @Test
    public void testContainsValue() {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>(5);

        myHashMap.put("first", 10);
        myHashMap.put("second", 5);
        myHashMap.put("third", 4);
        myHashMap.put("forth", 2);

        assertTrue(myHashMap.containsValue(10));
        assertTrue(myHashMap.containsValue(5));
        assertTrue(myHashMap.containsValue(4));
        assertTrue(myHashMap.containsValue(2));
        assertFalse(myHashMap.containsValue(1));
        assertFalse(myHashMap.containsValue(17));
    }

    @Test
    public void testContainsKey() {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>(5);

        myHashMap.put("first", 10);
        myHashMap.put("second", 5);
        myHashMap.put("third", 4);
        myHashMap.put("forth", 2);

        assertTrue(myHashMap.containsKey("first"));
        assertTrue(myHashMap.containsKey("forth"));
        assertFalse(myHashMap.containsKey("1"));
        assertFalse(myHashMap.containsKey("firtz"));
    }

    @Test
    public void testGetValue() {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>(5);

        myHashMap.put("first", 10);
        myHashMap.put("second", 5);
        myHashMap.put("third", 4);
        myHashMap.put("forth", 2);

        assertEquals(new Integer(10), myHashMap.get("first"));
        assertEquals(new Integer(5), myHashMap.get("second"));
        assertEquals(new Integer(4), myHashMap.get("third"));
        assertEquals(new Integer(2), myHashMap.get("forth"));
        assertNotEquals(new Integer(2), myHashMap.get("first"));
    }

    @Test
    public void testRemove() {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>(5);

        myHashMap.put("first", 10);
        myHashMap.put("second", 5);
        myHashMap.put("third", 4);
        myHashMap.put("forth", 2);

        assertTrue(myHashMap.remove("first"));
        assertFalse(myHashMap.containsKey("first"));
        assertFalse(myHashMap.containsValue(new Integer(10)));
        assertTrue(myHashMap.remove("second"));
        assertFalse(myHashMap.containsKey("second"));
        assertFalse(myHashMap.containsValue(new Integer(5)));
        assertFalse(myHashMap.remove("1"));
    }

//    @Test
//    public void testRearrange() {
//        MyHashMap<String, Integer> myHashMap = new MyHashMap<>(3);
//
//        myHashMap.add(3);
//        myHashMap.add(9);
//        myHashMap.add(12);
//
//        assertEquals(3, myHashMap.get(0).size());
//        assertEquals(3, myHashMap.getIndexSize());
//
//        myHashMap.add(15);
//
//        assertEquals(1, myHashMap.get(0).size());
//        assertEquals(3, myHashMap.get(3).size());
//        assertEquals(6, myHashMap.getIndexSize());
//
//        assertTrue(myHashMap.contains(9));
//        assertTrue(myHashMap.contains(15));
//        assertFalse(myHashMap.contains(18));
//    }
    @Test
    public void testRearrangeWithLoadFactor50() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>(6, 0.50);

        myHashMap.put(6, "6");
        myHashMap.put(12, "12");
        myHashMap.put(18, "18");

        assertEquals(3, myHashMap.get(0).size());
        assertEquals(6, myHashMap.getIndexSize());

        myHashMap.put(21, "21");

        assertEquals(1, myHashMap.get(0).size());
        assertEquals(2, myHashMap.get(6).size());
        assertEquals(1, myHashMap.get(9).size());
        assertEquals(12, myHashMap.getIndexSize());

        assertTrue(myHashMap.containsValue("6"));
        assertTrue(myHashMap.containsValue("12"));
        assertFalse(myHashMap.containsValue("29"));
    }
    @Test
    public void testGetKeys() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        myHashMap.put(6, "6");
        myHashMap.put(12, "12");
        MyArrayList<Integer> actual = myHashMap.getKeys();

        assertTrue(actual.contains(6));
        assertTrue(actual.contains(12));
        assertEquals(actual.size(), 2);
    }
    @Test
    public void testGetValues() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        myHashMap.put(6, "6");
        myHashMap.put(12, "12");
        MyArrayList<String> actual = myHashMap.getValues();

        assertTrue(actual.contains("6"));
        assertTrue(actual.contains("12"));
        assertEquals(actual.size(), 2);
    }

}
