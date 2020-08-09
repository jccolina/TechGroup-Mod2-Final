package colinajose.model.people;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeviceTest {

    @Test
    public void testEquals() {
        String phone1 = "45444444";
        String phone2 = "45444444";
        Person person1 = new Parent("Juan Perez", "3455555LP", 30, "Male");
        Person person2 = new Parent("Juan Perez", "3455555LP", 30, "Male");
        Device device1 = new Device(person1, phone1);
        Device device2 = new Device(person2, phone2);

        assertEquals(device1, device2);
    }

    @Test
    public void testNotEqualsDifferentPhone() {
        String phone1 = "45444444";
        String phone2 = "45444445";
        Person person1 = new Parent("Juan Perez", "3455555LP", 30, "Male");
        Person person2 = new Parent("Juan Perez", "3455555LP", 30, "Male");
        Device device1 = new Device(person1, phone1);
        Device device2 = new Device(person2, phone2);

        assertNotEquals(device1, device2);
    }

    @Test
    public void testNotEqualsDifferentOwner() {
        String phone1 = "45444444";
        String phone2 = "45444445";
        Person person1 = new Parent("Juan Perez", "3455555LP", 30, "Male");
        Person person2 = new Parent("Jose Guzman", "3455555LP", 30, "Male");
        Device device1 = new Device(person1, phone1);
        Device device2 = new Device(person2, phone2);

        assertNotEquals(device1, device2);
    }
}