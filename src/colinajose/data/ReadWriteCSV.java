package colinajose.data;

import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class ReadWriteCSV extends ReadWriteFile {

    public ReadWriteCSV(String path){
        super(path);
    }

    @Override
    public MyArrayList<MyHashMap<String, String>> readEntries() {
        MyArrayList<MyHashMap<String, String>> entries = new MyArrayList<>();
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(this.path));
            String[] headers = csvReader.readNext();
            String[] line = csvReader.readNext();
            while (line != null) {
                MyHashMap<String, String> entry = new MyHashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    entry.put(headers[i], line[i]);
                }
                entries.add(entry);
                line = csvReader.readNext();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return entries;
    }

    @Override
    public boolean writeEntries(MyArrayList<MyHashMap<String, String>> entries) {
        try {
            CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(this.path), StandardCharsets.UTF_8),
                    ',',
                    CSVWriter.DEFAULT_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            if(!entries.isEmpty()) {
                MyArrayList<String> keys = entries.get(0).getKeys();
                String[] headers = getHeaders(keys);
                //Write headers in CSV
                writer.writeNext(headers);
                for (int i = 0; i < entries.size(); i++) {
                    MyHashMap<String, String> entry = entries.get(i);
                    String[] line = new String[headers.length];
                    for (int j = 0; j < headers.length; j++) {
                        line[j] = entry.get(headers[j]);
                    }
                    writer.writeNext(line);
                }
            } else {
                writer.writeNext(new String[]{""});
            }
            writer.close();
            return true;
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }

    private String[] getHeaders(MyArrayList<String> keys){
        String[] headers = new String[keys.size()];
        for (int i = 0; i < keys.size(); i++) {
            headers[i] = keys.get(i);
        }
        return headers;
    }
}
