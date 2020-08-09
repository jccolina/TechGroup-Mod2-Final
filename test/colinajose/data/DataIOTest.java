package colinajose.data;

import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DataIOTest {

    @Test
    public void testReadCSV() {
        String path = "test\\colinajose\\resources\\grades.csv";
        String nameField = "Full Name";
        String gradeField = "Grade";
        DataIO dataIO = new DataIO();
        MyArrayList<MyHashMap<String, String>> actualEntries = dataIO.read(path);
        MyHashMap<String, String> actualEntry = actualEntries.get(0);
        String expectedName = "Saul Miranda";
        String expectedGrade = "80";
        int expectedSize = 1;

        assertEquals(expectedSize, actualEntries.size());
        assertEquals(expectedName, actualEntry.get(nameField));
        assertEquals(expectedGrade, actualEntry.get(gradeField));

    }

    @Test
    public void testReadJSON() {
        String path = "test\\colinajose\\resources\\grades.json";
        String nameField = "Full Name";
        String gradeField = "Grade";
        DataIO dataIO = new DataIO();
        MyArrayList<MyHashMap<String, String>> actualEntries = dataIO.read(path);
        MyHashMap<String, String> actualEntry = actualEntries.get(0);
        String expectedName = "Saul Miranda";
        String expectedGrade = "80";
        int expectedSize = 1;

        assertEquals(expectedSize, actualEntries.size());
        assertEquals(expectedName, actualEntry.get(nameField));
        assertEquals(expectedGrade, actualEntry.get(gradeField));
    }

    @Test
    public void testWriteCSV() {
        String path = "test\\colinajose\\resources\\writeTest.csv";
        String nameField = "Full Name";
        String gradeField = "Grade";
        String expectedName = "Saul Miranda";
        String expectedGrade = "80";
        String expectedEntry = "\"Grade\",\"Full Name\"\n\"80\",\"Saul Miranda\"";
        DataIO dataIO = new DataIO();
        MyArrayList<MyHashMap<String, String>> entries = new MyArrayList<>();
        MyHashMap<String, String> entry = new MyHashMap<>();
        entry.put(nameField, expectedName);
        entry.put(gradeField, expectedGrade);
        entries.add(entry);
        boolean actualResult = dataIO.write(path, entries);
        String actualEntry = getConcatLinesFromFile(path);

        assertTrue(actualResult);
        assertEquals(expectedEntry, actualEntry);
    }

    @Test
    public void testWriteJSON() {
        String path = "test\\colinajose\\resources\\writeTest.json";
        String nameField = "Full Name";
        String gradeField = "Grade";
        String expectedName = "Saul Miranda";
        String expectedGrade = "80";
        String expectedEntry = "[{\"Full Name\":\"Saul Miranda\",\"Grade\":\"80\"}]";
        DataIO dataIO = new DataIO();
        MyArrayList<MyHashMap<String, String>> entries = new MyArrayList<>();
        MyHashMap<String, String> entry = new MyHashMap<>();
        entry.put(nameField, expectedName);
        entry.put(gradeField, expectedGrade);
        entries.add(entry);
        boolean actualResult = dataIO.write(path, entries);
        String actualEntry = getConcatLinesFromFile(path);

        assertTrue(actualResult);
        assertEquals(expectedEntry, actualEntry);
    }

    private String getConcatLinesFromFile(String path){
        String result = "";
        try{
            File actualFile = new File(path);
            Scanner scannerReader = new Scanner(actualFile);
            while (scannerReader.hasNextLine()){
                result += scannerReader.nextLine() + "\n";
            }
            if(!result.isEmpty()){
                result = result.substring(0, result.length() -1);
            }
            scannerReader.close();
            actualFile.delete();
        }
        catch (Exception exception){
            System.out.println(exception.getStackTrace());;
        }
        return result;
    }
}