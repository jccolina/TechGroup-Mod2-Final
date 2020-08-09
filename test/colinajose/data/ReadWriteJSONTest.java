package colinajose.data;

import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;
import org.junit.Test;
import java.io.File;
import java.util.Scanner;
import static org.junit.Assert.*;

public class ReadWriteJSONTest {

    @Test
    public void testReadEntries() {
        String path = "test\\colinajose\\resources\\grades.json";
        String nameField = "Full Name";
        String gradeField = "Grade";
        ReadWriteJSON readWriteJSON = new ReadWriteJSON(path);
        MyArrayList<MyHashMap<String, String>> actualEntries = readWriteJSON.readEntries();
        MyHashMap<String, String> actualEntry = actualEntries.get(0);
        String expectedName = "Saul Miranda";
        String expectedGrade = "80";
        int expectedSize = 1;

        assertEquals(expectedSize, actualEntries.size());
        assertEquals(expectedName, actualEntry.get(nameField));
        assertEquals(expectedGrade, actualEntry.get(gradeField));
    }

    @Test
    public void testWriteEntries() {
        String path = "test\\colinajose\\resources\\writeTest.json";
        String nameField = "Full Name";
        String gradeField = "Grade";
        String expectedName = "Saul Miranda";
        String expectedGrade = "80";
        String expectedEntry = "[{\"Full Name\":\"Saul Miranda\",\"Grade\":\"80\"}]";
        ReadWriteJSON readWriteJSON = new ReadWriteJSON(path);
        MyArrayList<MyHashMap<String, String>> entries = new MyArrayList<>();
        MyHashMap<String, String> entry = new MyHashMap<>();
        entry.put(nameField, expectedName);
        entry.put(gradeField, expectedGrade);
        entries.add(entry);
        boolean actualResult = readWriteJSON.writeEntries(entries);
        String actualEntry = getFirstLineFromFile(path);

        assertTrue(actualResult);
        assertEquals(expectedEntry, actualEntry);
    }

    private String getFirstLineFromFile(String path){
        String result = "";
        try{
            File actualFile = new File(path);
            Scanner scannerReader = new Scanner(actualFile);
            result = scannerReader.nextLine();
            scannerReader.close();
            actualFile.delete();
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());;
        }
        return result;
    }
}