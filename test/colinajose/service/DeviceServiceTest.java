package colinajose.service;

import colinajose.model.people.Device;
import colinajose.model.people.Parent;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DeviceServiceTest {

    @Test
    public void testSendNotification() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Parent parent = new Parent("Roberto Gonzales", "123123123", 40, "Male");
        Device device = new Device(parent, "44353202");
        DeviceService deviceService = new DeviceService(device);
        deviceService.sendNotification();
        String expected = "Sending notification to following contact: " + "\n"
                        + "Phone: "+ device.getPhoneNumber() + "\n\n"
                        + "Dear " + device.getOwner().getName() + ",\n" +
                "You are receiving this notification since the student in charge of you has not been accomplishing the expectations required for our school.\n" +
                "Therefore we would like to get together in order to discuss about it." + "\n" +
                "Best Regards\n";
        String actual = output.toString().replace("\r", "");

        assertEquals(expected, actual);
    }

    @Test
    public void testEquals() {
        Parent parent = new Parent("Roberto Gonzales", "123123123", 40, "Male");
        String phone = "44353202";
        Device device1 = new Device(parent, phone);
        Device device2 = new Device(parent, phone);
        DeviceService deviceService1 = new DeviceService(device1);
        DeviceService deviceService2 = new DeviceService(device2);

        assertEquals(deviceService1, deviceService2);
    }
}