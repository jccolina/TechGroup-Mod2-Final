package colinajose.service;

import colinajose.model.people.Device;
import colinajose.model.people.Parent;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ObservableServiceTest {

    @Test
    public void registerObserver() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Device device = createDevice("Roberto Gonzales", "123123123", 40, "Male", "44353202");
        ObservableService observableService = new ObservableService();
        Observer observer = new DeviceService(device);
        observableService.registerObserver(observer);
        observableService.notifyObservers();
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
    public void testRemoveObserver() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Device device = createDevice("Roberto Gonzales", "123123123", 40, "Male", "44353202");
        ObservableService observableService = new ObservableService();
        Observer observer = new DeviceService(device);
        observableService.registerObserver(observer);
        observableService.removeObserver(observer);
        observableService.notifyObservers();
        String expected = "No devices to be notified.\n";
        String actual = output.toString().replace("\r", "");

        assertEquals(expected, actual);
    }

    @Test
    public void testNotifyObserversWithTwoDevices() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Device device1 = createDevice("Roberto Gonzales", "123123123", 40, "Male", "44353202");
        Device device2 = createDevice("Juan Zamorano", "324234234", 45, "Male", "455353202");
        ObservableService observableService = new ObservableService();
        Observer observer1 = new DeviceService(device1);
        Observer observer2 = new DeviceService(device2);
        observableService.registerObserver(observer1);
        observableService.registerObserver(observer2);
        observableService.notifyObservers();
        String expected1 = "Sending notification to following contact: " + "\n"
                + "Phone: "+ device1.getPhoneNumber() + "\n\n"
                + "Dear " + device1.getOwner().getName() + ",\n" +
                "You are receiving this notification since the student in charge of you has not been accomplishing the expectations required for our school.\n" +
                "Therefore we would like to get together in order to discuss about it." + "\n" +
                "Best Regards\n";
        String expected2 = "Sending notification to following contact: " + "\n"
                + "Phone: "+ device2.getPhoneNumber() + "\n\n"
                + "Dear " + device2.getOwner().getName() + ",\n" +
                "You are receiving this notification since the student in charge of you has not been accomplishing the expectations required for our school.\n" +
                "Therefore we would like to get together in order to discuss about it." + "\n" +
                "Best Regards\n";
        String expected = expected1 + expected2;
        String actual = output.toString().replace("\r", "");

        assertEquals(expected, actual);
    }

    private Device createDevice(String name, String ci, int age, String gender, String phone){
        Parent parent = new Parent(name, ci,  age, gender);
        return new Device(parent, phone);
    }
}