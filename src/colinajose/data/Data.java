package colinajose.data;

import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;

public abstract class Data {
    protected MyArrayList<String> headers;
    protected MyArrayList<MyHashMap<String, String>> entries;
    protected String dataType;
    protected MyHashMap<String, String> parameters;

    public Data(MyHashMap<String, String> parameters){
        this.parameters = parameters;
    }
    abstract public MyArrayList<MyHashMap<String, String>> readEntries();

    abstract public boolean writeEntries(MyArrayList<MyHashMap<String, String>> entries);

    public MyArrayList<String> getHeaders() {
        return headers;
    }

    public void setHeaders(MyArrayList<String> headers) {
        this.headers = headers;
    }

    public MyArrayList<MyHashMap<String, String>> getEntries() {
        return entries;
    }

    public void setEntries(MyArrayList<MyHashMap<String, String>> entries) {
        this.entries = entries;
    }
}
