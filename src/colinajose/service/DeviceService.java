package colinajose.service;

import colinajose.model.people.Device;

public class DeviceService implements Observer{
    private Device device;

    public DeviceService(Device device){
        this.device = device;
    }

    @Override
    public void sendNotification() {
        System.out.println("Sending notification to following contact: " + "\n"
                +"Phone: "+ this.device.getPhoneNumber() + "\n");
        System.out.println("Dear " + this.device.getOwner().getName() + ",\n" +
                "You are receiving this notification since the student in charge of you has not been accomplishing the expectations required for our school.\n" +
                "Therefore we would like to get together in order to discuss about it." + "\n" +
                "Best Regards");
    }
}
