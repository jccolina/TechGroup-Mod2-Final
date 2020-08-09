package colinajose.model.people;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void testEquals() {
        Person person1 = new Parent("Juan Perez", "3455555LP", 30, "Male");
        Person person2 = new Parent("Juan Perez", "3455555LP", 30, "Male");

        assertEquals(person1, person2);
    }

    @Test
    public void testNotEqualsDifferentName() {
        Person person1 = new Parent("Rodrigo Rocha", "3455555LP", 30, "Male");
        Person person2 = new Parent("Juan Perez", "3455555LP", 30, "Male");

        assertNotEquals(person1, person2);
    }

    @Test
    public void testNotEqualsDifferentCI() {
        Person person1 = new Parent("Rodrigo Rocha", "3455555CB", 30, "Male");
        Person person2 = new Parent("Rodrigo Rocha", "3455555LP", 30, "Male");

        assertNotEquals(person1, person2);
    }

    @Test
    public void testNotEqualsDifferentAge() {
        Person person1 = new Parent("Rodrigo Rocha", "3455555CB", 31, "Male");
        Person person2 = new Parent("Rodrigo Rocha", "3455555CB", 30, "Male");

        assertNotEquals(person1, person2);
    }

    @Test
    public void testNotEqualsDifferentGender() {
        Person person1 = new Parent("Rodrigo Rocha", "3455555CB", 30, "Female");
        Person person2 = new Parent("Rodrigo Rocha", "3455555CB", 30, "Male");

        assertNotEquals(person1, person2);
    }

    @Test
    public void testToString() {
        String name = "Rodrigo Rocha";
        String ci = "3455555CB";
        Person person = new Parent(name, ci, 30, "Female");
        String expected = name + ", " + "CI: " + ci;
        String actual = person.toString();

        assertEquals(expected, actual);
    }
}