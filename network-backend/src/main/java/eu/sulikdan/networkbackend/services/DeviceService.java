package eu.sulikdan.networkbackend.services;

import eu.sulikdan.networkbackend.entities.Device;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface DeviceService {

    Device createDevice(@NotNull Device device);

    List<Device> getAllDevicesSortedByType();

    Device getDeviceByMacAddress(@NotBlank String macAddress);

}
