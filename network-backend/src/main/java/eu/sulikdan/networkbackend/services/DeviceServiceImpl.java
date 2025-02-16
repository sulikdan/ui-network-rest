package eu.sulikdan.networkbackend.services;

import eu.sulikdan.networkbackend.entities.Device;
import eu.sulikdan.networkbackend.exceptions.DeviceException;
import eu.sulikdan.networkbackend.repositories.DeviceRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class DeviceServiceImpl implements DeviceService {

    DeviceRepository deviceRepository;

    @Override
    public Device createDevice(Device device) {
        if (deviceRepository.existsDeviceByMacAddress(device.getMacAddress())) {
            log.warn("Device with MacAddress {} already exists, cannot save again.", device.getMacAddress());
            throw new DeviceException("Device already exists");
        } else {
            return deviceRepository.save(device);
        }
    }

    @Override
    public List<Device> findAllDevicesSortedByType() {
        return deviceRepository.findAllDevicesByOrderByDeviceTypeAsc();
    }

    @Override
    public Device findDeviceByMacAddress(@NotNull String macAddress) {
        return deviceRepository.findById(macAddress)
                .orElseThrow(() -> new DeviceException("Device with MacAddress " + macAddress + " not found"));
    }
}
