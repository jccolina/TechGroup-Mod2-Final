package colinajose.model.schoolEntities;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubjectTest {

    @Test
    public void testToString() {
        String expectedTopic = "Mathematics";
        Subject subject = new Subject(expectedTopic);
        String actual = subject.toString();

        assertEquals(expectedTopic, actual);
    }
}