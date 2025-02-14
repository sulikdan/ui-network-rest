package eu.sulikdan.networkbackend.services;

import eu.sulikdan.networkbackend.entities.Device;

import java.util.List;

public interface DeviceService {

    Device createDevice(Device device);

    List<Device> getAllDevicesSortedByType();

    Device getDeviceByMacAddress(String macAddress);

}
