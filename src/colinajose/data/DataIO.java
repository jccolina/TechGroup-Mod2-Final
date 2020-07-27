package colinajose.data;

import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;

public class DataIO {
    private FileFactory factory;

    public DataIO(){
        factory = new FileFactory();
    }

    public MyArrayList<MyHashMap<String, String>> read(String path){
        ReadWriteFile file = factory.createFile(path);
        return file.readEntries();
    }
    public boolean write(String path, MyArrayList<MyHashMap<String, String>> entries){
        ReadWriteFile file = factory.createFile(path);
        return file.writeEntries(entries);
    }
}
