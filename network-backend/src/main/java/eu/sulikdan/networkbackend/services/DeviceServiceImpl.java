package eu.sulikdan.networkbackend.services;

import eu.sulikdan.networkbackend.entities.Device;
import eu.sulikdan.networkbackend.exceptions.DeviceException;
import eu.sulikdan.networkbackend.repositories.DeviceRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return deviceRepository.findAllByOrderByDeviceTypeAsc();
    }

    @Override
    public Optional<Device> findById(String macAddress) {
        return deviceRepository.findById(macAddress);
    }
}
