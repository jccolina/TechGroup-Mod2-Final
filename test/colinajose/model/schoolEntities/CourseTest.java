package colinajose.model.schoolEntities;

import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {

    @Test
    public void testEquals() {
        Course course1 = new Course("2nd", "2020", "2A");
        Course course2 = new Course("2nd", "2020", "2A");

        assertEquals(course1, course2);
    }
    @Test
    public void testNotEquals() {
        Course course1 = new Course("2nd", "2020", "2A");
        Course course2 = new Course("2nd", "2020", "2B");

        assertNotEquals(course1, course2);
    }

    @Test
    public void testToString() {
        String level = "2nd";
        String year = "2020";
        String group = "2A";
        Course course = new Course(level, year, group);
        String expected = "Grade: " + level + ", Group: " + group + ", Year: " + year;
        String actual = course.toString();

        assertEquals(expected, actual);
    }
}