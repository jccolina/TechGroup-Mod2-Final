package datastructures.hashmap;

import datastructures.arraylist.MyArrayList;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;

public class MyHashMap<K, V> {
    private static final int LIST_SIZE_LIMIT = 3;
    private static final int INIT_ARRAY_SIZE = 10;
    private double loadFactor;

    private MyCircularDoublyLinkedList<MyEntry<K, V>>[] index;
    private int indexSize;

    private int size;

    public MyHashMap() {
        this.init(INIT_ARRAY_SIZE);
        this.loadFactor = 1;
    }

    public MyHashMap(int initArraySize) {
        this.init(initArraySize);
        this.loadFactor = 1;
    }

    public MyHashMap(int initArraySize, double loadFactor) {
        this.init(initArraySize);
        this.loadFactor = loadFactor;
    }

    private void init(int indexSize) {
        this.indexSize = indexSize;
        this.index = new MyCircularDoublyLinkedList[this.indexSize];
        initArrayLists(this.index);
    }

    private void initArrayLists(MyCircularDoublyLinkedList[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = new MyCircularDoublyLinkedList<>();
        }
    }

    private static int getHashCode(Object value) {
        if (value instanceof String) {
            return value.toString().length();
        }

        return value.hashCode();
    }

    public int getIndexSize() {
        return this.indexSize;
    }

    public MyCircularDoublyLinkedList<MyEntry<K, V>> get(int index) {
        if (this.indexSize <= index) {
            throw new ArrayIndexOutOfBoundsException("Nono");
        }
        return this.index[index];
    }

    public V get(K key) {
        int hashKey = getHashCode(key) % this.indexSize;
        MyCircularDoublyLinkedList<MyEntry<K, V>> currentList = this.index[hashKey];
        for (int i = 0; i < currentList.size(); i++) {
            MyEntry<K, V> currentItem = currentList.get(i);
            if (currentItem.getKey().equals(key)) {
                return currentItem.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        int hashKey = getHashCode(key) % this.indexSize;
        MyEntry<K, Object> entry = new MyEntry<>(key, null);

        return contains(this.index, entry, hashKey);
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < this.indexSize; i++) {
            for (int j = 0; j < this.index[i].size(); j++) {
                if (value.equals(this.index[i].get(j).getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean contains(MyCircularDoublyLinkedList[] array, Object newValue, int hashKey) {
        MyCircularDoublyLinkedList currentList = array[hashKey];

        return currentList.contains(newValue);
    }

    public boolean put(K key, V value) {
        int hashKey = getHashCode(key) % this.indexSize;

        MyEntry<K, V> newEntry = new MyEntry<>(key, value);
        boolean result = add(this.index, newEntry, hashKey);

        if (result) {
            MyCircularDoublyLinkedList currentList = this.index[hashKey];
            this.size++;
            if ((double) this.size / this.index.length > loadFactor) {

                rearrangeSet();
            }
        }
        return result;
    }

    private static boolean add(MyCircularDoublyLinkedList[] currentArray, Object newEntry, int hashKey) {
        MyCircularDoublyLinkedList currentList = currentArray[hashKey];
        if (contains(currentArray, newEntry, hashKey)) {
            currentList.remove(newEntry);
        }

        currentList.add(newEntry);

        return true;
    }

    private void rearrangeSet() {
        this.indexSize *= 2;
        MyCircularDoublyLinkedList<MyEntry<K, V>>[] newIndex = new MyCircularDoublyLinkedList[this.indexSize];
        initArrayLists(newIndex);

        for (MyCircularDoublyLinkedList<MyEntry<K, V>> currentList : this.index) {
            for (int i = 0; i < currentList.size(); ++i) {
                MyEntry<K, V> currentItem = currentList.get(i);
                int hashKey = getHashCode(currentItem.getKey()) % newIndex.length;
                add(newIndex, currentItem, hashKey);
            }
        }
        this.index = newIndex;
    }

    public int size() {
        return this.size;
    }

    public boolean remove(K key) {
        int hashKey = getHashCode(key) % this.indexSize;
        MyEntry<K, V> newEntry = new MyEntry<>(key, null);
        return this.index[hashKey].remove(newEntry);
    }

    public MyArrayList<K> getKeys(){
        MyArrayList<K> keys =  new MyArrayList<>();
        if(this.size > 0){
            for (int i = 0; i < this.indexSize; i++) {
                MyCircularDoublyLinkedList<MyEntry<K, V>> entryList = get(i);
                for (int j = 0; j < entryList.size(); j++) {
                    MyEntry<K, V> entry = entryList.get(j);
                    keys.add(entry.getKey());
                }
            }
        }
        return keys;
    }

    public MyArrayList<V> getValues(){
        MyArrayList<V> values =  new MyArrayList<>();
        if(this.size > 0){
            for (int i = 0; i < this.indexSize; i++) {
                MyCircularDoublyLinkedList<MyEntry<K, V>> entryList = get(i);
                for (int j = 0; j < entryList.size(); j++) {
                    MyEntry<K, V> entry = entryList.get(j);
                    values.add(entry.getValue());
                }
            }
        }
        return values;
    }
}
