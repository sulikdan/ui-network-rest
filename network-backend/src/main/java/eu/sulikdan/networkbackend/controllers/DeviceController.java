package eu.sulikdan.networkbackend.controllers;

import eu.sulikdan.networkbackend.dtos.DeviceDto;
import eu.sulikdan.networkbackend.entities.Device;
import eu.sulikdan.networkbackend.exceptions.DeviceException;
import eu.sulikdan.networkbackend.mappers.DeviceMapper;
import eu.sulikdan.networkbackend.services.DeviceService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/devices")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeviceController {

    DeviceService deviceService;
    DeviceMapper deviceMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    DeviceDto createDevice(@RequestBody @NotNull DeviceDto deviceDto) {
        log.info("Creating device {}", deviceDto);
        Device aDevice;

        if (deviceDto.getUplinkMacAddress() == null || deviceDto.getUplinkMacAddress().isBlank()) {
            aDevice = new Device(deviceDto.getMacAddress(), deviceDto.getDeviceType(), null);
        } else {
            // if uplink is defined
            // TODO with more validation move to separate validationService
            Device parentDevice = deviceService.findById(deviceDto.getMacAddress())
                    .orElseThrow(() -> new DeviceException("No parent device found for uplink " + deviceDto.getUplinkMacAddress()));
            aDevice = new Device(deviceDto.getMacAddress(), deviceDto.getDeviceType(), parentDevice.getUplinkMacAddress());
        }

        return deviceMapper.toDeviceDto(deviceService.createDevice(aDevice));
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<DeviceDto> getAllDevicesSortedByType() {
        log.info("Getting all devices sorted by type");
        return deviceService.findAllDevicesSortedByType()
                .stream()
                .map(deviceMapper::toDeviceDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/{macAddress}")
    @ResponseStatus(HttpStatus.OK)
    DeviceDto getDeviceByMacAddress(@PathVariable @NotBlank String macAddress) {
        log.info("Getting device by mac address {}", macAddress);
        Device foundDevice = deviceService.findById(macAddress)
                .orElseThrow(() -> new DeviceException("Device with MacAddress " + macAddress + " not found"));
        return deviceMapper.toDeviceDto(foundDevice);
    }

}
