package colinajose.service;

import colinajose.model.people.Contact;
import colinajose.model.people.Device;

public class DeviceService implements Observer{
    private Device device;

    public DeviceService(Device device){
        this.device = device;
    }

    @Override
    public void update() {
    }
}
