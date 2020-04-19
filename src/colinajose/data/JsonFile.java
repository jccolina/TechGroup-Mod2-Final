package colinajose.data;

import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;

public class JsonFile extends File {
    public JsonFile(MyHashMap<String, String> parameters) {
        super(parameters);
    }

    @Override
    public MyArrayList<MyHashMap<String, String>> readEntries() {
        return null;
    }

    @Override
    public boolean writeEntries(MyArrayList<MyHashMap<String, String>> entries) {
        return false;
    }
}
