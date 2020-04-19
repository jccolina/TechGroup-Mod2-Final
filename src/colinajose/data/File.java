package colinajose.data;

import datastructures.hashmap.MyHashMap;

abstract public class File extends Data {
    protected String path;

    public File(MyHashMap<String, String> parameters) {
        super(parameters);
        this.path = parameters.get("path");
    }
}
