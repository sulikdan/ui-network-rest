package eu.sulikdan.networkbackend.controllers;

import eu.sulikdan.networkbackend.dtos.DeviceDto;
import eu.sulikdan.networkbackend.entities.Device;
import eu.sulikdan.networkbackend.exceptions.DeviceException;
import eu.sulikdan.networkbackend.mappers.DeviceMapper;
import eu.sulikdan.networkbackend.repositories.DeviceRepository;
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
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/devices")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeviceController {

    DeviceService deviceService;
    DeviceMapper deviceMapper;

    //TODO move to another validation service, with checking
    DeviceRepository deviceRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    DeviceDto createDevice(@RequestBody @NotNull DeviceDto deviceDto) {
        log.info("Creating device {}", deviceDto);
        Device aDevice = null;

        if (deviceDto.getUplinkMacAddress() == null || deviceDto.getUplinkMacAddress().isBlank()) {
            aDevice = new Device(deviceDto.getMacAddress(), deviceDto.getDeviceType(), null);
        } else {
            // TODO change structure, may not need to find the device itself
            Device parentDevice = deviceRepository.findById(deviceDto.getMacAddress()).orElseThrow(() -> new DeviceException("No parent device found for uplink " + deviceDto.getUplinkMacAddress()));
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
        return deviceMapper.toDeviceDto(deviceService.findDeviceByMacAddress(macAddress));
    }

}
