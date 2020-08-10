package colinajose.data;

import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class ReadWriteJSON extends ReadWriteFile {

    public ReadWriteJSON(String path){
        super(path);
    }
    @Override
    public MyArrayList<MyHashMap<String, String>> readEntries() {
        MyArrayList<MyHashMap<String, String>> entries = new MyArrayList<>();
        try {
            JSONTokener tokener = new JSONTokener(new FileReader(this.path));
            JSONArray jsonArray = new JSONArray(tokener);
            for (int i = 0; i < jsonArray.length(); i++) {
                MyHashMap<String, String> entry = new MyHashMap<>();
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    entry.put(key, jsonObject.get(key).toString());
                }
                entries.add(entry);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return entries;
    }

    @Override
    public boolean writeEntries(MyArrayList<MyHashMap<String, String>> entries) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.path));
            JSONArray objectsArray = new JSONArray();
            if(!entries.isEmpty()) {
                for (int i = 0; i < entries.size(); i++) {
                    JSONObject object = new JSONObject();
                    MyHashMap<String, String> entry = entries.get(i);
                    MyArrayList<String> keys = entry.getKeys();
                    for (int j = 0; j < keys.size(); j++) {
                        String key = keys.get(j);
                        object.put(key, entry.get(key));
                    }
                    objectsArray.put(object);
                }
            }
            objectsArray.write(writer);
            writer.close();
            return true;
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }
}
