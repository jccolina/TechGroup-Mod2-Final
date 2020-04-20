package colinajose.data;

import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CsvFile extends File {

    public CsvFile(MyHashMap<String, String> parameters) {
        super(parameters);
    }

    @Override
    public MyArrayList<MyHashMap<String, String>> readEntries() {
        MyArrayList entries = new MyArrayList();
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(path));
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
            CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8),
                    ',',
                    CSVWriter.DEFAULT_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeNext(new String[]{"Name", "Grade"});//headers
            for (int i = 0; i < entries.size(); i++) {
                MyHashMap<String, String> entry = entries.get(i);
                String[] line = {entry.get("Name"), entry.get("Grade")};
                writer.writeNext(line);
            }
            writer.close();
            return true;
        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }
}
