package colinajose.model.schoolEntities;

import org.junit.Test;

import static org.junit.Assert.*;

public class KardexTest {

    @Test
    public void testEquals() {
        Course course1 = new Course("2nd", "2020", "2A");
        Course course2 = new Course("2nd", "2020", "2A");
        Kardex kardex1 = new Kardex(course1);
        Kardex kardex2 = new Kardex(course2);

        assertEquals(kardex1, kardex2);
    }

    @Test
    public void testNotEquals() {
        Course course1 = new Course("2nd", "2020", "2A");
        Course course2 = new Course("2nd", "2021", "2A");
        Kardex kardex1 = new Kardex(course1);
        Kardex kardex2 = new Kardex(course2);

        assertNotEquals(kardex1, kardex2);
    }

    @Test
    public void testToString() {
        String level = "2nd";
        String year = "2020";
        String group = "2A";
        Course course = new Course(level, year, group);
        Kardex kardex = new Kardex(course);
        String expected = "Grade: " + level + ", Group: " + group + ", Year: " + year;
        String actual = kardex.toString();

        assertEquals(expected, actual);
    }
}