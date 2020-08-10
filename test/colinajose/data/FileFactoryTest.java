package colinajose.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileFactoryTest {

    @Test
    public void testCreateFileCSV() {
        String path = "test\\colinajose\\resources\\grades.csv";
        FileFactory fileFactory = new FileFactory();
        ReadWriteFile actualFile = fileFactory.createFile(path);

        assertTrue(actualFile instanceof ReadWriteCSV);
    }
    @Test
    public void testCreateFileJSON() {
        String path = "test\\colinajose\\resources\\grades.json";
        FileFactory fileFactory = new FileFactory();
        ReadWriteFile actualFile = fileFactory.createFile(path);

        assertTrue(actualFile instanceof ReadWriteJSON);
    }
}