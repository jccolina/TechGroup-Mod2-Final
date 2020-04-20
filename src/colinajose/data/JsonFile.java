package colinajose.data;

import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

public class JsonFile extends File {
    public JsonFile(MyHashMap<String, String> parameters) {
        super(parameters);
    }

    @Override
    public MyArrayList<MyHashMap<String, String>> readEntries() {
        MyArrayList entries = new MyArrayList();
        try {
            JSONTokener tokener = new JSONTokener(new FileReader(path));
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
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
            JSONArray objectsArray = new JSONArray();
            JSONObject object = new JSONObject();
            for (int i = 0; i < entries.size(); i++) {
                MyHashMap<String, String> entry = entries.get(i);
                object.put("Name", entry.get("Name"));
                object.put("Grade", entry.get("Grade"));
                objectsArray.put(object);
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
