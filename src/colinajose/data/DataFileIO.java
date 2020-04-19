package colinajose.data;

import datastructures.hashmap.MyHashMap;

public class DataFileIO extends DataIO {
    @Override
    public Data createData(String dataType, MyHashMap<String, String> parameters) {
        if(dataType.equals("JSON")){
            return new JsonFile(parameters);
        } else if(dataType.equals("CSV")){
            return new CsvFile(parameters);
        } else {
            return null;
        }
    }
}
