package colinajose.data;

import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;

abstract public class DataIO {
    /*private Data data;
    private String dataType;
    private MyHashMap<String, String> parameters;*/

    public MyArrayList<MyHashMap<String, String>> readData(String dataType, MyHashMap<String, String> parameters){
        Data data = createData(dataType, parameters);
        return data.readEntries();
    }
    public boolean writeData(String dataType, MyHashMap<String, String> parameters, MyArrayList<MyHashMap<String, String>> entries){
        Data data = createData(dataType, parameters);
        return data.writeEntries(entries);
    }

    abstract public Data createData(String dataType, MyHashMap<String, String> parameters);

/*    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public MyHashMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(MyHashMap<String, String> parameters) {
        this.parameters = parameters;
    }*/
}
