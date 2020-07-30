package colinajose.data;

import datastructures.arraylist.MyArrayList;
import datastructures.circulardoublylinkedlist.MyCircularDoublyLinkedList;
import datastructures.hashmap.MyEntry;
import datastructures.hashmap.MyHashMap;

public abstract class ReadWriteFile {
    protected String path;

    public ReadWriteFile(String path){
        this.path = path;
    }
    public abstract MyArrayList<MyHashMap<String, String>> readEntries();
    public abstract boolean writeEntries(MyArrayList<MyHashMap<String, String>> entries);
    protected MyArrayList<String> getMapKeys(MyHashMap<String, String> map){
        MyArrayList<String> keys = new MyArrayList<>();
        if(map.size() != 0) {
            for (int i = 0; i < map.getIndexSize(); i++) {
                MyCircularDoublyLinkedList<MyEntry<String, String>> entriesList = map.get(i);
                for (int j = 0; j < entriesList.size(); j++) {
                    MyEntry entry = entriesList.get(j);
                    keys.add((String) entry.getKey());
                }
            }
        }
        return keys;
    }
}
