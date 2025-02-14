package eu.sulikdan.networkbackend.controllers;

import eu.sulikdan.networkbackend.entities.Device;
import eu.sulikdan.networkbackend.services.DeviceService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("devices")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeviceController {

    DeviceService deviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Device createDevice(@RequestBody @NotNull Device device) {
        log.info("Creating device {}", device);
        return deviceService.createDevice(device);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Device> getAllDevicesSortedByType() {
        log.info("Getting all devices sorted by type");
        return deviceService.getAllDevicesSortedByType();
    }


    @GetMapping("/{macAddress}")
    @ResponseStatus(HttpStatus.OK)
    Device getDeviceByMacAddress(@PathVariable @NotBlank String macAddress) {
        log.info("Getting device by mac address {}", macAddress);
        return deviceService.getDeviceByMacAddress(macAddress);
    }


}
