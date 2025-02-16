package eu.sulikdan.networkbackend.services;

import eu.sulikdan.networkbackend.entities.Device;
import eu.sulikdan.networkbackend.entities.DeviceType;
import eu.sulikdan.networkbackend.exceptions.DeviceException;
import eu.sulikdan.networkbackend.repositories.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceServiceImplTest {

    @Mock
    DeviceRepository deviceRepository;

    @InjectMocks
    DeviceServiceImpl deviceService;

    Device device1;

    @BeforeEach
    void setUp() {
        device1 = new Device("D4-2C-cc-6D-6b-d2", DeviceType.SWITCH, null);
    }

    @Test
    void createDevice() {

        when(deviceRepository.existsDeviceByMacAddress(device1.getMacAddress())).thenReturn(false);
        when(deviceRepository.save(device1)).thenReturn(device1);

        deviceService.createDevice(device1);

        verify(deviceRepository, times(1)).existsDeviceByMacAddress(any());
        verify(deviceRepository, times(1)).save(any());
    }


    @Test
    void createDeviceAlreadyExistsException() {
        when(deviceRepository.existsDeviceByMacAddress(device1.getMacAddress())).thenReturn(true);


        DeviceException thrown = assertThrows(
                DeviceException.class,
                () -> deviceService.createDevice(device1)
        );


        assertTrue(thrown.getMessage().contains("Device already exists") );
        verify(deviceRepository, times(1)).existsDeviceByMacAddress(any());
        verify(deviceRepository, times(0)).save(any());
    }
}
