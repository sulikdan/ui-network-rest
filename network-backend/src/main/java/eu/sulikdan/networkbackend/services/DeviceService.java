package eu.sulikdan.networkbackend.services;

import eu.sulikdan.networkbackend.entities.Device;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    Device createDevice(@NotNull Device device);

    List<Device> findAllDevicesSortedByType();

    Optional<Device> findById(@NotBlank String macAddress);

}
