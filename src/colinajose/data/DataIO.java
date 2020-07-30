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
        if(file == null){
            return null;
        } else {
            return file.readEntries();
        }
    }
    public boolean write(String path, MyArrayList<MyHashMap<String, String>> entries){
        ReadWriteFile file = factory.createFile(path);
        if(file == null){
            return false;
        } else {
            return file.writeEntries(entries);
        }
    }
}
